package shop.Derby;

import shop.Customer;
import shop.Product;
import shop.Total;
import shop.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BestBirdsEver {
    private List<Product> product;
    private List<Transaction> transaction;
    private int transactionId = 1;
    private Total total;

    public BestBirdsEver(){
        product = new ArrayList<>();
        transaction = new ArrayList<>();
        total = new Total();
    }

    public void addProduct(Product p){
     product.add(p);
    }

    public List<Product> getProduct(){
        return new ArrayList<>(product);
    }

    public void printProductInfo(){
        for (Product p : product){
            System.out.println(p.toString());
        }
    }

    public List<Transaction> getTransaction() {
        return new ArrayList<>(transaction);
    }

    public void addTransaction(Transaction t) {
        transaction.add(t);
    }

    public void sell(Product p, Customer c, int count) {
        System.out.println("id: " + transactionId + " | " + getTime() + " | " + count + " " + p + " | " + c.getName() +
                " | " + p.getPrice() + " | " + count * p.getPrice());
        Transaction t = new Transaction();
        t.setId(transactionId);
        t.setDate(getTime());
        t.setCustomer(c);
        t.setProduct(p);
        t.setCount(count);
        t.setPrice(p.getPrice());
        t.setAmount(count * p.getPrice());
        addTransaction(t);
        setTotal();

        transactionId++;
    }

    private String getTime() {
        Date dealTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM.yy HH:mm:ss");
        return sdf.format(dealTime);
    }


    public Total getTotal() {
        return total;
    }

    public void setTotal() {
        double totalAmount = 0;
        for (Transaction t : getTransaction()){
            totalAmount += t.getAmount();
        }

        total.set(totalAmount);
    }
}
