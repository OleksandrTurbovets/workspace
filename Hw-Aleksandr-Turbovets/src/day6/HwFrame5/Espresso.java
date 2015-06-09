package day6.HwFrame5;

public class Espresso extends Goods{
	
	public Espresso(){
		super("Espresso", "Water, coffe", 20);
	}
	
	public Espresso(int pieces){
		super("Espresso", "Water, coffe, suger", 20 + pieces);
	}

	public Espresso(String name, String ingredients, double price) {
		super(name, ingredients, price);
		
	}

	@Override
	public boolean addSuger(int pieces) {
		// TODO Auto-generated method stub
		return false;
	}

}
