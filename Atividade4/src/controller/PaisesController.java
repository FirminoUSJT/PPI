package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pais;
import service.PaisService;


@WebServlet("/ManterPais.do")
public class PaisesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNome = request.getParameter("Nome");
		String pPopulacao = request.getParameter("Populacao");
		String pArea = request.getParameter("Area");


		Pais Pais = new Pais();
		Pais.setNome(pNome);
		Pais.setPopulacao(Long.parseLong(pPopulacao));
		Pais.setArea(Double.parseDouble(pArea));
		

		PaisService cs = new PaisService();
		cs.criar(Pais);
		Pais = cs.carregar(Pais.getId());
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Cliente Cadastrado</title></head><body>");
		out.println(	"id: "+Pais.getId());
		out.println(	"nome: "+Pais.getNome()+"");
		out.println(	"populacao: "+Pais.getPopulacao()+"");
		out.println(	"area: "+Pais.getArea()+"");
	    out.println("</body></html>");
	}
}