package test;

import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.InputStream;
import java.io.OutputStream;

public class BookScrabbleHandler implements ClientHandler {
    PrintWriter out;
    Scanner in;
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        out=new PrintWriter(outToClient);
        in=new Scanner(inFromclient);
        String[] splits = in.next().split(",");
        String check = splits[0];
        String[] array = new String[splits.length-1];
        System.arraycopy(splits, 1, array, 0, splits.length-1);
        if(check.equals("Q"))
        {
            DictionaryManager dictionaryM = new DictionaryManager();
            if(dictionaryM.query(array))
                out.println("true");
            else
                out.println("false");

        }
        else
        {
            DictionaryManager dictionaryM = new DictionaryManager();
            if(dictionaryM.challenge(array))
                out.println("true");
            else
                out.println("false");
        }
        out.flush();
    }
    @Override
    public void close() {
        in.close();
        out.close();
    }
}
