package service;



import java.io.File;
public class Teste {

	public static void main(String[] args) {
		
	
//File arquivo = new File("C:\\Users\\ferna\\OneDrive\\00. Documentos\\São judas\\7º Semestre\\PPI\\Laboratório\\Atividade8\\WebContent\\log");
		File arquivo = new File(".\\WebContent\\log");
		if(arquivo.isFile()){
			System.out.println("Arquivo");
			} else if(arquivo.isDirectory()){
			String[] arquivos = arquivo.list();
			System.out.println("Diretório");
			for(String a : arquivos) {
				System.out.println(a);
			}
			}
		
		
	

	}

}
