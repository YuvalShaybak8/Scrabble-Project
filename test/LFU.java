package test;


public class LFU implements CacheReplacementPolicy{

    private String[] cache;
    private int[] freq;
    private int size;
    private int count;
    
    public LFU(int size){
        this.size = size;
        this.cache = new String[size];
        this.freq = new int[size];
        this.count = 0;
    }
    
    public void add(String word){
        if(count < size){
            cache[count] = word;
            freq[count] = 1;
            count++;
        }else{
            int min = freq[0];
            int index = 0;
            for(int i = 0; i < size; i++){
                if(freq[i] < min){
                    min = freq[i];
                    index = i;
                }
            }
            cache[index] = word;
            freq[index] = 1;
        }
    }
    
    public String remove(){
        String word = cache[0];
        for(int i = 0; i < size - 1; i++){
            cache[i] = cache[i + 1];
            freq[i] = freq[i + 1];
        }
        count--;
        return word;
    }
    
    public String toString(){
        String s = "";
        for(int i = 0; i < count; i++){
            s += cache[i] + " ";
        }
        return s;
    }

}
