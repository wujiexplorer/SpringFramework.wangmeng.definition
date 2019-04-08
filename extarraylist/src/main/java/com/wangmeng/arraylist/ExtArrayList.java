package com.wangmeng.arraylist;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/7
 * TIME 21:29
 * Description no Description
 **/
public class ExtArrayList<E> implements ExtList<E> {

    private Object[] elementData;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    public ExtArrayList(int initialCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException("初始容量不能小于0");
        }
        elementData = new Object[initialCapacity];
    }

    public ExtArrayList(){
        this(DEFAULT_CAPACITY);
    }
    @Override
    public void add(E e){
        ensureExplicitCapacity(size+1);
        elementData[size++] = e;
    }

    public void add(int index,Object object){
        ensureExplicitCapacity(size+1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = object;
        size++;
    }

    private void ensureExplicitCapacity(int minCapacity){
        if(size == elementData.length){
            int oldCapacity = elementData.length;
            //1.5倍
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            // java.lang.ArrayIndexOutOfBoundsException: 1，没有下面的语句
            //防止容量为1时的bug
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData,newCapacity);
//            int newCapacity = 2*size;
//            Object[] newObjects = new Object[newCapacity];
//            for(int i=0;i<elementData.length;i++){
//                newObjects[i] = elementData[i];
//            }
//            elementData = newObjects;
        }
    }

    public E get(int index){
        rangeCheck(index);
        return elementData(index);
    }

    E elementData(int index) {
        //Object可以转化为泛型
        return (E) elementData[index];
    }


    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("越界啦！");
        }
    }

    public Object remove(int index){
        //rangeCheck(index);
        Object object = get(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null;
        return object;
    }

    //删除重复的元素，删除第一个
    public Boolean remove(Object object){
        for(int i=0;i<size;i++){
            Object value = elementData[i];
            if(value.equals(object)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int getSize(){
        return size;
    }
}
