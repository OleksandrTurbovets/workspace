package day6.HwFrame5;

public abstract class Goods implements Suger{

	private String name;
	private String ingredients;
	private double price;
	
	public Goods(int pieces){
	}

	public Goods(String name, String ingredients, double price){
		this.name = name;
		this.ingredients = ingredients;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public double getPrice() {
		return price;
	}

	
}
