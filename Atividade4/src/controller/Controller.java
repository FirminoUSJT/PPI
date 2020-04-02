package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Pais;
import Service.PaisService;


@WebServlet("/Pais.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String pNome = request.getParameter("Nome");
		String pPopulacao = request.getParameter("Populacao");
		String pArea = request.getParameter("area");
		String acao = request.getParameter("Acao");
		
		//CRIAR O OBJETO DENTRO DA CLASSE PAIS
		Pais pais = new Pais();
		pais.setNome(pNome);
		pais.setPopulacao(Long.parseLong(pPopulacao));
		pais.setArea(Double.parseDouble(pArea));
		
		PaisService tt = new PaisService();
		RequestDispatcher dispatcher = null;


		switch(acao){
		case "Incluir":
			tt.criar(pais);
			pais = tt.carregar(pais.getId());
			request.setAttribute("pais", pais);  
			dispatcher = request.getRequestDispatcher("Pais.jsp");
			break;
			
		case "Listar":
			pPopulacao = "0";
			pArea = "0";
			
			ArrayList<Pais> pais = tt.listarTodos();
			request.setAttribute("pais", pais);
			dispatcher = request.getRequestDispatcher("ListaPais.jsp");
			
			
		}
		
		dispatcher.forward(request, response);

}}
