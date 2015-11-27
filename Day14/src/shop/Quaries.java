package shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Quaries implements IQuaries {

    private Total total;

    public Quaries() {
        total = new Total();
    }

    @Override
    public List<Product> getListOfProducts() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM products");
            resultSet = preparedStatement.executeQuery();

            List<Product> tempListOfProducts = new ArrayList<>();

            while (resultSet.next()) {
                Product tempProduct = new Product();
                tempProduct.setId(resultSet.getInt("id"));
                tempProduct.setName(resultSet.getString("name"));
                tempProduct.setPrice(resultSet.getDouble("price"));
                tempListOfProducts.add(tempProduct);

            }

            return tempListOfProducts;

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

        return null;
    }

    @Override
    public List<Transaction> getListOfTransactions() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM transaction");
            resultSet = preparedStatement.executeQuery();

            List<Transaction> tempListOfTransactions = new ArrayList<>();

            while (resultSet.next()) {
                Transaction tempTransaction = new Transaction();
                tempTransaction.setId(resultSet.getInt("id"));
                tempTransaction.setDate(resultSet.getString("data"));
                tempTransaction.setCustomer(getCustomerByIndex(resultSet.getInt("fk_customer_id")));
                tempTransaction.setProduct(getProductByIndex(resultSet.getInt("fk_product_id")));
                tempTransaction.setCount(resultSet.getInt("count"));
                tempTransaction.setPrice(getProductByIndex(resultSet.getInt("fk_product_id")).getPrice());
                tempTransaction.setAmount(resultSet.getInt("count") * getProductByIndex(resultSet.getInt("fk_product_id")).getPrice());
                tempListOfTransactions.add(tempTransaction);
            }

            setTotal(tempListOfTransactions);

            return tempListOfTransactions;
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

        return null;
    }

    private Customer getCustomerByIndex(int index) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE id=" + index);
            resultSet = preparedStatement.executeQuery();

            Customer tempCustomer = new Customer();

            resultSet.next();

            tempCustomer.setName(resultSet.getString("name"));

            return tempCustomer;
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
        return null;
    }

    private Product getProductByIndex(int index) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE id=" + index);
            resultSet = preparedStatement.executeQuery();

            Product tempProduct = new Product();
            resultSet.next();

            tempProduct.setName(resultSet.getString("name"));
            tempProduct.setPrice(resultSet.getDouble("price"));


            return tempProduct;
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
        return null;
    }

    public Total getTotal() {
        return total;
    }

    private void setTotal(List<Transaction> transactions) {
        double totalAmount = 0;
        for (Transaction t : transactions) {
            totalAmount += t.getAmount();
        }

        total.set(totalAmount);
    }
}
