package com.wangmeng.arraylist;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/9
 * TIME 8:54
 * Description no Description
 **/
public class ExtSystem {

    public static native void arraycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length);

    public static native long nanoTime();

    public static native long currentTimeMillis();

}
