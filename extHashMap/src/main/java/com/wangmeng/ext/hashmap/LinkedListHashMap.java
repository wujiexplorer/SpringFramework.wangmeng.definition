package com.wangmeng.ext.hashmap;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/8
 * TIME 20:42
 * Description no Description
 **/
public class LinkedListHashMap<Key,Value> {

    LinkedList<Entry1<Key,Value>>[] tables = new LinkedList[998];

    public void put(Object key,Object value){
        Entry1<Key, Value> newEntry1 = new Entry1(key, value);
        int hashCode = key.hashCode();
        int hash = hashCode%tables.length;
        //tables[hash] = value;
        LinkedList<Entry1<Key,Value>> entryLinkedList = tables[hash];
        if(entryLinkedList == null){
            entryLinkedList = new LinkedList<Entry1<Key,Value>>();
            entryLinkedList.add(newEntry1);
            tables[hash] = entryLinkedList;
        }else{
            //java.util.ConcurrentModificationException 集合迭代不能随便修改元素
            Iterator<Entry1<Key,Value>> iterator = entryLinkedList.iterator();
            Integer flag = 0;
            while(iterator.hasNext()){
                Entry1 entry1 = iterator.next();
                if(entry1.key.equals(key)) {
                    entry1.value = value;
                    flag = 1;
                }
            }
            if(flag == 0){
                entryLinkedList.add(newEntry1);
            }

//            for(Entry1 entry1 : entryLinkedList){
//                if(entry1.key.equals(key)){
//                    entry1.value = value;
//                }else{
//                    entryLinkedList.add(newEntry1);
//                }
//            }
        }
        System.out.println("hash:"+hash);
    }

    public Object get(Object key){
        int hashCode = key.hashCode();
        int hash = hashCode%tables.length;
        LinkedList<Entry1<Key,Value>> linkedList = tables[hash];
        for(Entry1 entry1 : linkedList){
            if(entry1.key.equals(key)){
                return entry1.value;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedListHashMap<Object, Object> linkedListHashMap = new LinkedListHashMap<>();
//        linkedListHashMap.put("a","aa");
//        linkedListHashMap.put("b","aa");
//        //hash:-531 wangmengwertyui 不正规的单词
//        //java.lang.ArrayIndexOutOfBoundsException: -251
//        linkedListHashMap.put("c","aa");
//        System.out.println(linkedListHashMap.get("c"));
//        System.out.println(15%13);
        linkedListHashMap.put(1,"aaaa");
//        linkedListHashMap.put("b","cccc");
//        linkedListHashMap.put("c","cccc");
        linkedListHashMap.put(999,"bbbb");
        linkedListHashMap.put(1+998*2,"cccc");
        linkedListHashMap.put(1,"cccc");
        System.out.println(linkedListHashMap.get(1));
    }
}

class Entry1<Key,Value>{
    Key key;
    Value value;

    public Entry1(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
