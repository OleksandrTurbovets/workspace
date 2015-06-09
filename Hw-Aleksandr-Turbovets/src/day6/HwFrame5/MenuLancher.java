package day6.HwFrame5;

public class MenuLancher {

	public static void main(String[] args) {
		Goods goods[] = new Goods[10];
		goods[0] = new Espresso("Espresso", "Water, coffe", 20);
		goods[1] = new Espresso();
		goods[2] = new Espresso(1);
		
		System.out.println("Name		" + "Ingredients		" + "	Price");
		for (Goods g : goods){
			if (g != null){
				System.out.println(g.getName() + "	" + g.getIngredients() + "		" + g.getPrice());	
			}
			
		}
		
	}

}
