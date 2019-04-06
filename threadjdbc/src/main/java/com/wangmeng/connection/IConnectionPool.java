package com.wangmeng.connection;

import java.sql.Connection;

public interface IConnectionPool {

    public Connection getConnection();

    public void releaseConnection(Connection connection);
}
