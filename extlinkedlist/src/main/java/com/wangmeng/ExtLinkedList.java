package com.wangmeng;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/8
 * TIME 11:15
 * Description no Description
 **/
public class ExtLinkedList<E> {

    private int size;

    private Node first;

    private Node last;

    public void add(E e){
        Node node = new Node();
        // node.prev = null;
        node.object = e;
        /// node.next = null;
        if(first == null){
            first = node;
           /// last = node;
        }else{
            node.prev = last;
            last.next = node;
        }
        last = node;
        size++;
    }

    public Object get(int index)throws Exception{
      return getNode(index).object;
    }

    public Node getNode(int index)throws Exception{
        checkElementIndex(index);
        Node node = null;
        if(first != null){
            node = first;
            for(int i=0;i<index;i++){
                node = node.next;
            }
        }else{
            throw new Exception("该链表没有节点！");
        }
        return node;
    }

    public void remove(int index)throws Exception{
        checkElementIndex(index);
        Node oldNode = getNode(index);
        if(oldNode != null){
            Node oldNextNode = oldNode.next;
            Node oldPrevNode = oldNode.prev;
            if(oldPrevNode != null) {
                oldPrevNode.next = oldNextNode;
                oldNode.prev = null;
            }else {
                first = oldNextNode;
            }
            if(oldNextNode != null) {
                oldNextNode.prev = oldPrevNode;
                oldNode.next = null;
            }else {
                last = oldPrevNode;
            }
            oldNode.object = null;
            size--;
        }
    }

    public void add(int index,E e)throws Exception{
        if(size == index){
            add(e);
            return;
        }
        checkElementIndex(index);
        Node oldNode = getNode(index);
        if(oldNode != null){
            Node oldPrevNode = oldNode.prev;
            Node newNode = new Node();
            newNode.object = e;
            if(oldPrevNode == null){
                first = newNode;
            }else{
                newNode.prev = oldPrevNode;
                oldPrevNode.next = newNode;
            }
            newNode.next = oldNode;
            oldNode.prev = newNode;
            size++;
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("查询越界啦！");
        }
    }


    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public int getSize(){
        return size;
    }

    private class Node{

        private Object object;

        private Node prev;

        private Node next;
    }

    public static void main(String[] args) throws Exception{
        ExtLinkedList<String> extLinkedList = new ExtLinkedList<String>();
        extLinkedList.add("a");
        extLinkedList.add("b");
        extLinkedList.add("c");
        extLinkedList.add("d");
       // System.out.println("remove before:"+extLinkedList.get(2));
//        System.out.println(extLinkedList.first.object);
//        System.out.println(extLinkedList.first.next.object);
//        System.out.println(extLinkedList.first.next.next.object);
       // extLinkedList.remove(3);
        //System.out.println("remove after:"+extLinkedList.get(2));
        //System.out.println(extLinkedList.get(2));
        extLinkedList.add(4,"f");
        for(int i=0;i<extLinkedList.size;i++){
            System.out.println(extLinkedList.get(i));
        }
    }
}
