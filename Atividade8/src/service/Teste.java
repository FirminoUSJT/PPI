package service;



import java.io.File;
public class Teste {

	public static void main(String[] args) {
		
	
//File arquivo = new File("C:\\Users\\ferna\\OneDrive\\00. Documentos\\S�o judas\\7� Semestre\\PPI\\Laborat�rio\\Atividade8\\WebContent\\log");
		File arquivo = new File(".\\WebContent\\log");
		if(arquivo.isFile()){
			System.out.println("Arquivo");
			} else if(arquivo.isDirectory()){
			String[] arquivos = arquivo.list();
			System.out.println("Diret�rio");
			for(String a : arquivos) {
				System.out.println(a);
			}
			}
		
		
	

	}

}
