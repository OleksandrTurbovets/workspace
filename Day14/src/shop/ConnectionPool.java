package shop;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class ConnectionPool {

    private static final String URL = "jdbc:mysql://localhost:3306/shopDb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static ConnectionPool connectionPool;
    private ComboPooledDataSource cpds;

    public ConnectionPool() {
        cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(URL);
        cpds.setUser(USERNAME);
        cpds.setPassword(PASSWORD);

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);

    }

    public static ConnectionPool getInstance(){
        if (connectionPool == null){
            connectionPool = new ConnectionPool();
            return connectionPool;
        } else {
            return connectionPool;
        }
    }

    public Connection getConnection() throws Exception{
        return this.cpds.getConnection();
    }
}
