import java.net.*;
import java.io.*;
import java.security.*;
import javax.crypto.*;

public class Server{
	public static void main(String args[]) throws Exception{

		//SOCKET DECLARATION
		ServerSocket serverSocket = null;
		Socket socket = null;
		//CLIENT'S PETITION
		String petition = "";
		//SERVER'S ANSWER
		String answer = "";
		//KEY GENERATION FOR COMMUNICATION BETWEEN CLIENT AND SERVER
		System.out.println("THE SERVER IS GENERATING THE KEY...");
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56);
		Key password = keyGen.generateKey();
		System.out.println( "SERVER'S KEY: " + password );
 		System.out.println( "THE KEY HAS GENERATED!" );

 		//WRITE IN A .SER DOCUMENT THE KEY
 		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("password.ser"));
  		oos.writeObject(password);
    	oos.close();

    	//SECTION WHERE THE SERVER WILL LISTEN TO POSSIBLE CLIENTS
    	try
		  {
		   System.out.println("LISTENING BY PORT: 8000");
		   serverSocket = new ServerSocket(8000); 
		  }
		  catch(IOException e)
		  {
		   System.out.println("java.io.IOException generada");
		   e.printStackTrace();
		  }
		  System.out.println("WAITING FOR CLIENTS...");

		  //LOOP FOR CONECTIONS
		  while(true)
			  {
			   try
			   {
			    socket = serverSocket.accept();
			    System.out.println("CONNECTED: " + socket.getInetAddress().getHostName());
			    DataInputStream dis = new DataInputStream( socket.getInputStream() );
				DataOutputStream dos = new DataOutputStream( socket.getOutputStream() );
				//THE SERVER NEED READ THE CLIENT'S PETITION
				do{ 
				petition = dis.readUTF();

				//CONDITIONAL CASES WHERE THE PETITION'S CLIENT WILL BE THE CARD TO SHOW
				
				//SUDO GEDIT WITH SUDO PASS
				if(petition.equals("pwd")){ 
					System.out.println("THE CLIENT MESSAGE IS :"+petition);
					//answer = "pwd";
					//System.out.println("THE SERVER'S ANSWER WAS: "+answer+" IN EXECUTION");
					//dos.writeUTF(answer);
					 try{
				 	Runtime.getRuntime().exec("sudo gedit");
				 }
				 catch(IOException ioe){
				 	System.out.println("An Exception");
				 }
				}
				//System.out.println("WRITE AN ANSWER: ");
				//BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
				//answer = br.readLine();
				//System.out.println("THE SERVER'S ANSWER WAS: "+answer);
				//dos.writeUTF(answer);
				}
				while(!petition.equals("close"));
					dos.close();
					dis.close();
					socket = null;
				}
				
		
				catch(IOException e){
					System.out.println("java.io.IOException generated");
					e.printStackTrace();
				
			}		
		}
	}
}
	









