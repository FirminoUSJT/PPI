package Service;

import Modelo.Pais;
import Service.PaisService;
public class Teste {

	public static void main(String[] args) {
		
		PaisService envia= new PaisService();
		Pais criaPais = new Pais(0,"Deutschland", 83000000, 357051);
		envia.criar(criaPais);
		System.out.println("Pais " + criaPais + " Criado!");
		
		



	}

}
