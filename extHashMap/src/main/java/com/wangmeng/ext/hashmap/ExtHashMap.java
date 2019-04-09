package com.wangmeng.ext.hashmap;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/9
 * TIME 10:18
 * Description no Description
 **/
public class ExtHashMap<K,V> implements  ExtMap<K,V>{

    Node<K,V>[] table = null;

    int size;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static  int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    // aka 16

    @Override
    public V put(K key, V value) {
        if(table == null){
            table = new Node[DEFAULT_INITIAL_CAPACITY];
        }
        if(size > DEFAULT_INITIAL_CAPACITY*DEFAULT_LOAD_FACTOR){
            reSize();
        }
        int index = getIndex(key, DEFAULT_INITIAL_CAPACITY);
        Node<K,V> node = table[index];
        if(node == null){
            node = new Node<>(key,value,null);
            size++;
            //return node.getValue();
        }else{
            Node<K,V> newNode = node;
            while (newNode != null){
                if(newNode.getKey().equals(key) || newNode.getKey() == key){
                    //node.value = value;
                    return newNode.setValue(value);
                }else{
                    //在最后的节点之前添加
                    if(newNode.next == null){
                        //头插法
                        node = new Node<K,V>(key,value,node);
                        size++;
                    }

                }
                newNode = newNode.next;;
            }

        }
        table[index] = node;
        return null;
    }

   public void print(){
        for(int i=0;i<table.length;i++){
            Node<K,V> node = table[i];
            System.out.print("小标位置["+i+"]");
            while (node != null){
                System.out.print("[key:"+node.getKey()+",value:"+node.getValue()+"]");
                node = node.next;
//                if(node.next != null){
//                    node = node.next;
//                }else{
//                    node = null;
//                }
            }
            System.out.println();
        }
    }

    public int getIndex(K k,int length){
        int hashCode = k.hashCode();
        System.out.println("K:"+k+",    hashCode="+hashCode);
        int index = hashCode % length;
        return index;
    }

    private void reSize(){
        Node<K,V>[] newTable = new Node[DEFAULT_INITIAL_CAPACITY<<1];
        for(int i=0;i<table.length;i++){
            Node<K,V> oldNode =  table[i];
            while (oldNode != null){
                //清理原来的数据，防止以后新的table还能查询到老的数据
                table[i] = null;
                K oldK = oldNode.getKey();
                int index = getIndex(oldK,newTable.length);
                if(oldK.equals("22号")|| oldK.equals("66号")){
                    System.out.println("日志记录");
                }
                Node<K,V> oldNext = oldNode.next;
                oldNode.next = newTable[index];
                newTable[index] = oldNode;
                oldNode = oldNext;
            }
        }
        table = newTable;
        DEFAULT_INITIAL_CAPACITY = newTable.length;
        newTable = null;
    }

    @Override
    public V get(K k) {
        Node<K,V> node = getNode(table[getIndex(k,DEFAULT_INITIAL_CAPACITY)],k);
        return node == null ? null : node.getValue();
    }

    public Node<K,V> getNode(Node<K,V> node,K k){
        while (node!= null){
            if(node.getKey().equals(k)){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Node<K,V> implements Entry<K,V>{

        private K key;
        private V value;
        private Node<K,V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value   ;
        }

        @Override
        public V setValue(V value) {
            //设置新的值返回老的值
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
