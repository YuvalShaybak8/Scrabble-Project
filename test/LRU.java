package test;

import java.util.*;

public class LRU implements CacheReplacementPolicy 
{
    private LinkedList<String> list = new LinkedList<String>();

    public String remove() 
    {
        String last = list.getLast();
        return last;
    }

    public void add(String word) 
    {
        int index_list = list.indexOf(word);
        if (index_list != -1) 
        {
            String Wordexist = list.remove(index_list);
            list.addFirst(Wordexist);
        } 
        else 
        {
            list.addFirst(word);
        }
    }
}
