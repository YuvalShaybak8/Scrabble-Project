package test;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.ServerSocket;

public class MyServer  {
    int port;
    boolean stop;
    ClientHandler clientHandler;
    public MyServer(int port, ClientHandler clientHandler) {
        this.port = port;
        this.clientHandler = clientHandler;
    }
    public void start() {
        stop = false;
        new Thread(this::startServer).start();
    }
    private void startServer(){
        try{
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(1000);
            while(!stop) {
                try{
                    Socket client = server.accept();
                    clientHandler.handleClient(client.getInputStream(),client.getOutputStream());
                    client.close();
                } catch (SocketTimeoutException e) {}
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        stop = true;
    }
}
