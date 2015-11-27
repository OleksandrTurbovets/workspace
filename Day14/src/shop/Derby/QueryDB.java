package shop.Derby;

import shop.Derby.CreateDB;

import java.sql.*;

public class QueryDB {
    public  static final String SQL_STATEMENT = "select * from products";
//    public  static final String SQL_STATEMENT2 = "SELECT * FROM INFORMATION_SCHEMA.TABLES";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(CreateDB.JDBC_URL);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_STATEMENT);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println(columnCount);
        while (resultSet.next()){
           System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));

        }
    }
}
