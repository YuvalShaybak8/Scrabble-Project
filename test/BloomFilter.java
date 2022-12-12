package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.BitSet;

public class BloomFilter 
{
    private int size;
    BitSet bitS = new BitSet();
    String [] hashArr;

    public BloomFilter(int size, String ...args){
        this.size = size;
        int counter_args = args.length;
        this.hashArr = new String[counter_args];

        for (int i = 0; i < counter_args; i++) 
        {
            hashArr[i] = args[i];
        }
    }

    public String toString() 
    {
        String res = "";
        for (int i = 0; i < bitS.length(); i++) 
        {
            if (bitS.get(i)) 
                res += "1";
            else
                res += "0";    
        }
        return res;
    }

    public boolean contains(String word) 
    {
        for (String hash : hashArr) 
        {
            try 
            {
                MessageDigest messageD = MessageDigest.getInstance(hash);
                byte[] bytes = messageD.digest(word.getBytes());
                BigInteger bigI = new BigInteger(bytes);
                int ind = bigI.abs().intValue();
                if (ind < 0) 
                    ind = -ind;

                if (!bitS.get(ind % size)) 
                    return false;
            } 
            catch (Exception e) 
            {
                System.out.println(e);
            }
        }
        return true;
    }

    public void add(String word)
    {
        for (String hash: hashArr)
        {
            try {
                MessageDigest messageD = MessageDigest.getInstance(hash);
                byte [] bytes = messageD.digest(word.getBytes());
                BigInteger bigI = new BigInteger(bytes);
                int ind = bigI.abs().intValue();
                if(ind < 0)
                    ind = -ind;

                bitS.set(ind % size);
            } 
            catch (Exception e) 
            {
                System.out.println(e);
            }
        }
    }
}
