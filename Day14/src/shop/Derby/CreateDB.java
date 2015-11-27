package shop.Derby;

import org.apache.derby.jdbc.EmbeddedDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDB {
//    public static void main(String[] args) throws SQLException {
//        EmbeddedDataSource dataSource = new EmbeddedDataSource();
//        dataSource.setDatabaseName("shopDerbyDB");
//        dataSource.setCreateDatabase("create");
//        Connection connection = dataSource.getConnection();
//        connection.createStatement().execute("CREATE TABLE customers(id INT(11), name VARCHAR(64))");
//        connection.createStatement().execute("INSERT INTO customers VALUES " +
//                                                "('1', 'Steven Segal'), " +
//                                                "('2', 'Jorg Treen')");
//
//        System.out.println("OK");
//    }

    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String JDBC_URL = "jdbc:derby:shopDerbyDB;create=true";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);

//        connection.createStatement().execute("CREATE TABLE products(id INT, name VARCHAR(64), price INT)");
//        connection.createStatement().execute("DROP TABLE products");
        connection.createStatement().execute("INSERT INTO products VALUES " +
                                                "(1, 'Eaagle', 15), " +
                                                "(2, 'Duck', 5), " +
                                                "(3, 'Chiken', 1), " +
                                                "(4, 'Humminbird', 25), " +
                                                "(5, 'Calibri', 50), " +
                                                "(6, 'Penguin', 75), " +
                                                "(7, 'Owl', 30), " +
                                                "(8, 'Dove', 2), " +
                                                "(9, 'Flamingo', 100)");

        System.out.println("OK");
    }


}
