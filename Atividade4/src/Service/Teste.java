package Service;

import Modelo.Pais;
import Service.PaisService;
public class Teste {

	public static void main(String[] args) {
		
		PaisService envia= new PaisService();
		Pais criaPais = new Pais(0,"Alemanha", 123, 456);
		envia.criar(criaPais);
		System.out.println("Pais " + criaPais + " Criado!");
		
		



	}

}
