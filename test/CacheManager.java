package test;

import java.util.HashSet;

public class CacheManager {
 
    int size;
    CacheReplacementPolicy policy;
    HashSet<String> set = new HashSet<String>();
    int counter;

    public CacheManager(int size, CacheReplacementPolicy policy) {
        this.size = size;
        this.policy = policy;
        this.counter = 0;
    }

    public void add(String word) 
    {
        if (query(word)) 
            policy.add(word);
        else 
        {
            if (counter < size) 
            {
                set.add(word);
                policy.add(word);
                counter++;
            } 
            else 
            {
                String removeW = policy.remove();
                set.remove(removeW);
                set.add(word);
                policy.add(word);
            }
        }
    }

    public boolean query(String word) 
    {
        if (set.contains(word)) 
            return true;
        
        else 
            return false;

    }
}
