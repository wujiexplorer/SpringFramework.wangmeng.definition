package com.wangmeng.ext.hashmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/8
 * TIME 19:11
 * Description no Description
 **/
public class ExtArraylistHashMap<Key,Value> {

    private List<Entry<Key,Value>> tables = new ArrayList<>();

    private int size;

    public void put(Key key,Value value){
        Entry<Key, Value> entry = getEntry(key);
        if(entry != null){
            //覆盖比删除再新增效率要高
            entry.value = value;
        }else{
            Entry newEntry = new Entry(key,value);
            tables.add(newEntry);
        }
    }

    public Value get(Key key){
        Entry<Key, Value> entry = getEntry(key);
        return entry==null?null:entry.value;
    }

    private Entry<Key,Value> getEntry(Key key){
        for(Entry<Key,Value> entry : tables){
            if(entry.key.equals(key)){
                return entry;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ExtArraylistHashMap<String, String> extArraylistHashMap = new ExtArraylistHashMap<>();
        extArraylistHashMap.put("a","aaaaaa");
        extArraylistHashMap.put("b","bbbbbbb");
        extArraylistHashMap.put("a","vvvvvv");
        System.out.println(extArraylistHashMap.get("a"));
    }
}
class Entry<Key,Value>{
    Key key;
    Value value;

    public Entry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
