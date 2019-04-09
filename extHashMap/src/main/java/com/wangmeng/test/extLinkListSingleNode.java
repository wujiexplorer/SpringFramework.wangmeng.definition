package com.wangmeng.test;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/8
 * TIME 19:56
 * Description no Description
 **/
public class extLinkListSingleNode<E> {

    private Node<E> first;

    private Node<E> last;
    /**
     * 包装类需要初始化，基本类型不需要
     **/
    private Integer size=0;

    public Node<E> add(E e){
        Node<E> node = new Node<>();
        node.item = e;
        if(first == null){
            first = node;
        }else{
            last.next = node;
            //first.next = node;
        }
        last=node;
        size++;
        return node;
    }

    public Node<E> getNode(int index){
        Node<E> node = null;
        node = first;
        for(int i=0;i<index;i++){
            node =node.next;
        }
        return node;
    }

    public void remove(int index){
        Node<E> node = getNode(index);
        Node<E> newNode = node.next;
        Node<E> tempNode = first;
        for(int i=0;i<index-1;i++){
            tempNode = tempNode.next;
        }
        //单链表拿到前后节点就可以删除
        tempNode.next = newNode;
        node.next = null;
        size--;
    }

    private class Node<E>{
        E item;
        Node<E> next;

        public Node(){

        }
    }

    public static void main(String[] args) {
        extLinkListSingleNode<String> extLinkListSingleNode = new extLinkListSingleNode<>();
        extLinkListSingleNode.add("a");
        extLinkListSingleNode.add("b");
        extLinkListSingleNode.add("c");
        System.out.println(extLinkListSingleNode.first.item);
        extLinkListSingleNode.remove(1);
        System.out.println(extLinkListSingleNode.first.item);
        System.out.println(extLinkListSingleNode.first.next.item);
    }
}


