package shop;

public interface IStorage {

    public void sell(int productIndex, Customer customer, int count);

    public void deletePurchase(int number);

    public void addNewProduct(int id, String name, double price);
}
