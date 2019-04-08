package com.wangmeng.arraylist;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/8
 * TIME 0:07
 * Description no Description
 **/
/**
*定义泛型，添加的删除的元素都是泛型
 **/
public interface ExtList<E> {

    public void add(E e);

    public int getSize();

    public E get(int index);
}
