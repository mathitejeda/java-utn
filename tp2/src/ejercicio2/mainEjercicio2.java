package ejercicio2;

public class mainEjercicio2 {

	public static void main(String[] args) {
 
		//ProductoFresco
		System.out.println("Producto Fresco");
		ProductoFresco ProdFresco1 = new ProductoFresco("19/08/2023", "234", "01/01/2023", "Argentina");
		System.out.println(ProdFresco1.toString());
		System.out.println();

		//ProductoRefrigerado
		System.out.println("Producto Refrigerado");
		ProductoRefrigerado ProdRefri1 = new ProductoRefrigerado("20/08/2023", "836", 319);
		System.out.println(ProdRefri1.toString());
		System.out.println();
		
		//ProductoCongelado
		System.out.println("Producto Congelado");
		ProductoCongelado ProdCong1 = new ProductoCongelado("18/08/2023", "512", 15);	
		System.out.println(ProdCong1.toString());	
		
	}

}
