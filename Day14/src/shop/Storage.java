package shop;

import java.sql.*;
import java.util.Calendar;

public class Storage implements IStorage {
    private ConnectionPool connectionPool;

    public Storage() {
        try {
            connectionPool = new ConnectionPool();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void sell(int productIndex, Customer customer, int count) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO transaction VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, getTransactionId());
            preparedStatement.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.setInt(3, getCustomerId(customer));
            preparedStatement.setInt(4, productIndex + 1);
            preparedStatement.setInt(5, count);

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private int getTransactionId() {
        int transactionId;
        transactionId = getNextId("transaction");
        return transactionId;
    }

    private int getNextId(String table) {
        int id = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM " + table);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            id = resultSet.getInt("MAX(id)") + 1;


            return id;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    private int getCustomerId(Customer customer) {
        int customerId = getNextId("customers");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO customers VALUES (?, ?)");
            preparedStatement.setInt(1, customerId);
            preparedStatement.setString(2, customer.getName());
            preparedStatement.execute();

            return customerId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customerId;
    }

    @Override
    public void deletePurchase(int number) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM transaction WHERE id=" + number);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void addNewProduct(int id, String name, double price) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO products VALUES (?, ?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, price);
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
