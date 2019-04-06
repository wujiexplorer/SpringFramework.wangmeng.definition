package com.wangmeng.connection;

import java.sql.Connection;
import java.util.List;
import java.util.Vector;

public class Test001 {

    private List<Connection> freeConnection = new Vector<Connection>();

    private List<Connection> activeConnection = new Vector<Connection>();

    public static void main(String[] args) {
        ThreadConnection threadConnection = new ThreadConnection();
        for(int i=0;i<3;i++){
           Thread thread = new Thread(threadConnection,"线程i:"+i);
           thread.start();
        }
    }
}
class ThreadConnection implements Runnable{

    public void run() {
        for(int i=0;i<10;i++){
            Connection connection =ConnectionPoolManager.getConnection();
            System.out.println(Thread.currentThread().getName()+",connection："+connection);
            ConnectionPoolManager.releaseConnection(connection);
        }
    }
}
