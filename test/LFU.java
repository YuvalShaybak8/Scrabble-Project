package test;

import java.util.LinkedHashMap;

public class LFU implements CacheReplacementPolicy
{
    private LinkedHashMap<String, Integer> map;

    public LFU() 
    {
        this.map = new LinkedHashMap<String, Integer>();
    }

    public String remove() 
    {
        int min = Integer.MAX_VALUE;
        String minWord = "";
        for (String word : map.keySet()) 
        {
            if (map.get(word) <= min) 
            {
                min = map.get(word);
                minWord = word;
            }
        }
        return minWord;
    }

    public void add(String word) 
    {
        if(map.containsKey(word))
        {
            map.put(word, map.get(word) + 1);
        } 
        else 
        {
            map.put(word, 1);
        }
    }
}
