package com.wangmeng.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;

public class ConnectionPool implements IConnectionPool {

    private List<Connection> freeConnection = new Vector<Connection>();

    private List<Connection> activeConnection = new Vector<Connection>();

    private int countConne = 0;

    private DbBean dbBean;

    private void init(){
        if(dbBean == null){
            return;//最好抛异常
        }
        for(int i=0;i<dbBean.getInitConnections();i++){
            Connection newConnection = newConnection();
            if(newConnection != null){
                freeConnection.add(newConnection);
            }
        }
    }

    public synchronized Connection newConnection(){
        try{
            Class.forName(dbBean.getDriverName());
            Connection connection = DriverManager.getConnection(dbBean.getUrl(),dbBean.getUserName(),dbBean.getPassword());
            countConne++;
            return connection;
        }catch (Exception e){
            e.getMessage();
            return null;
        }
    }
    public ConnectionPool(DbBean dbBean){
        this.dbBean = dbBean;
        init();
    }

    public synchronized Connection getConnection() {//countConne线程不安全
        try{
            Connection connection = null;
            if(countConne < dbBean.getMaxActiveConnections()){
                if(freeConnection.size() > 0){
                    connection = freeConnection.remove(0);
                }else{
                    connection = newConnection();
                }
                boolean available = isAvailable(connection);
                if(available){
                    activeConnection.add(connection);
                }else {
                    countConne --;
                    connection = getConnection();
                }
            }else{
                wait(dbBean.getConnTimeOut());
                connection = getConnection();//递归，重试
            }
            return connection;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean isAvailable(Connection connection){
        try{
            if(connection == null || connection.isClosed()){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public synchronized void releaseConnection(Connection connection) {
        try{
            if(isAvailable(connection)){
                if(freeConnection.size() < dbBean.getMaxConnections()){
                        freeConnection.add(connection);
                }else{
                    connection.close();
                    countConne --;
                }
                activeConnection.remove(connection);

                notifyAll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
