package shop;

public class Total {
    private double amount;
    private double amountWithDiscount;
    private double discount;

    public Total(){
    }

    public void set(double amount) {
        if(amount >= 500 && amount < 1000){
            this.amount = amount;
            discount = amount*0.05;
            amountWithDiscount= amount - discount;
        } else if (amount >= 1000){
            this.amount = amount;
            discount = amount*0.1;
            amountWithDiscount= amount - discount;
        } else {
            this.amount = amount;
            amountWithDiscount = amount;
            discount = 0.0;
        }
    }

    public double getAmount() {
        return amount;
    }

    public double getAmountWithDiscount() {
        return amountWithDiscount;
    }

    public double getDiscount() {
        return discount;
    }
}
