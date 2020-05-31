package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import service.PaisService;

/**
 * Servlet implementation class LoginPaisController
 */
@WebServlet("/login.do")
public class LoginPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String pUsuario = request.getParameter("usuario");
		String pSenha =request.getParameter("senha");
		String pAcao = request.getParameter("acao");
		
		PaisService ps = new PaisService();
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		
		Usuario user = new Usuario();
		user = ps.logar(pUsuario);
		
		if(pSenha.equals(user.getSenha())) {
			
			request.setAttribute("user", user);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			
			//File arquivo = new File(".\\WebContent\\log", "LogUsers.txt");
			File arquivo = new File("C:\\Users\\ferna\\OneDrive\\00. Documentos\\São judas\\7º Semestre\\PPI\\Laboratório\\Atividade8\\WebContent\\log","LoginUsuarioLog.txt");
			FileOutputStream outFileStream = new FileOutputStream(arquivo);
			PrintWriter outStream = new PrintWriter(outFileStream);
			outStream.println("O usuário "+"" + pUsuario +""+ " entrou!"+ "\n");
			outStream.println(getDateTime());
			outStream.close();
			
			
			
		}
		else {
			File arquivo = new File("C:\\Users\\ferna\\OneDrive\\00. Documentos\\São judas\\7º Semestre\\PPI\\Laboratório\\Atividade8\\WebContent\\log","LoginInvalidoLog.txt");
			FileOutputStream outFileStream = new FileOutputStream(arquivo);
			PrintWriter outStream = new PrintWriter(outFileStream);
			outStream.println("O usuário "+""+ pUsuario +" "+" está tentando acessar. Sem entrada de dados Válida"+ "\n");
			outStream.println("Login e/ou senha inválida, por favor tente novamente!");
			outStream.println(getDateTime());
			outStream.close();
			response.sendRedirect("login.jsp");  
		}
			
		
	}
	private String getDateTime(){ 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		Date date = new Date(); 
		return dateFormat.format(date); 
	}
	
}
