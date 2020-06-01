package serverandclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Server extends Thread {
	private static ArrayList<BufferedWriter>clientes;           
	private static ServerSocket server; 
	private String name;
	private Socket con;
	private InputStream input_s;  
	private InputStreamReader input_r;  
	private BufferedReader buffer_r;
	
	public Server(Socket con){
		   this.con = con;
		   try {
		         input_s  = con.getInputStream();
		         input_r = new InputStreamReader(input_s);
		          buffer_r = new BufferedReader(input_r);
		   } catch (IOException e) {
		          e.printStackTrace();
		   }                          
		}

	public void run(){
        
		  try{
		                                      
		    String msg;
		    OutputStream ou =  this.con.getOutputStream();
		    Writer ouw = new OutputStreamWriter(ou);
		    BufferedWriter bfw = new BufferedWriter(ouw); 
		    clientes.add(bfw);
		    name = msg = buffer_r.readLine();
		               
		    while(!"Sair".equalsIgnoreCase(msg) && msg != null)
		      {           
		       msg = buffer_r.readLine();
		       sendToAll(bfw, msg);
		       System.out.println(msg);                                              
		       }
		                                      
		   }catch (Exception e) {
		     e.printStackTrace();
		    
		   }                       
		}
	
	public void sendToAll(BufferedWriter bwSaida, String msg) throws  IOException 
	{
	  BufferedWriter bwS;
	    
	  for(BufferedWriter bw : clientes){
	   bwS = (BufferedWriter)bw;
	   if(!(bwSaida == bwS)){
	     bw.write(name + " -> " + msg+"\r\n");
	     bw.flush(); 
	   }
	  }          
	}
	
	public static void main(String []args) {
	    
		  try{
		   
		    JLabel lblMessage = new JLabel("Host Port:");
		    JTextField txtPorta = new JTextField("1672");
		    Object[] texts = {lblMessage, txtPorta };  
		    JOptionPane.showMessageDialog(null, texts);
		    server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
		    clientes = new ArrayList<BufferedWriter>();
		    JOptionPane.showMessageDialog(null,"Active Host on: "+         
		    txtPorta.getText());
		    
		     while(true){
		       System.out.println("Connecting, Please Wait");
		       Socket con = server.accept();
		       System.out.println("Connected");
		       Thread t = new Server(con);
		        t.start();   
		    }
		                              
		  }catch (Exception e) {
		    
		    e.printStackTrace();
		  }                       
		 }// Fim do método main                      
}