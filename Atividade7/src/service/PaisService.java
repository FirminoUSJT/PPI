package service;

import java.util.ArrayList;

import dao.PaisDAO;
import model.Pais;

public class PaisService {
	
	PaisDAO dao = new PaisDAO();
	
	public int criar(Pais Pais) {
		return dao.criar(Pais);
	}
	
	public void atualizar(Pais pais) {
		 dao.atualizar(pais);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Pais carregar(int id){
		 return dao.carregar(id);
	}
	
	public void menorArea(Pais pais) {
		dao.menorArea(pais);
	}
	
	public long maiorPopulacao(long populacao) {
		return dao.maiorPopulacao(populacao);
	}
	
	
	public Pais[] vet3Paises() {
		return dao.vet3Paises();
		
	}
	public ArrayList<Pais> listarPaises() {
		return dao.listarTodos();
		
	}
	
	public ArrayList<Pais> listarPaises(String chave) {
		return dao.listarTodos(chave);
		
	}


}