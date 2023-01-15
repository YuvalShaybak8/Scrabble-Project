package test;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {
    HashMap<String, Dictionary> map;

    DictionaryManager() {
        map = new HashMap<>();
    }

    private static DictionaryManager newDictionary = null;

    private void addBook(String... args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (!map.containsKey(args[i]))
                map.put(args[i], new Dictionary(args[i]));
        }
    }

    public boolean query(String... args) {
        addBook(args);
        boolean flag = false;
        String word = args[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            if (map.get(args[i]).query(word))
                flag = true;
        }
        return flag;
    }

    public boolean challenge(String... args) {
        addBook(args);
        boolean flag = false;
        String word = args[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            if (map.get(args[i]).challenge(word))
                flag = true;
        }
        return flag;
    }

    public static DictionaryManager get() {
        if (newDictionary == null)
            newDictionary = new DictionaryManager();
        return newDictionary;
    }

    public int getSize() {
        return map.size();
    }
}