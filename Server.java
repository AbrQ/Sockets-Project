import java.net.*;
import java.io.*;
import java.security.*;
import javax.crypto.*;
import java.awt.Desktop;
import java.io.File;
import java.io.Console;

public class Server{
	public static void main(String args[]) throws Exception{

		//SOCKET DECLARATION
		ServerSocket serverSocket = null;
		Socket socket = null;
		//CLIENT'S PETITION
		String petition = "";
		//SERVER'S ANSWER
		String answer = "";
		//IMAGE NAME
		String file = "";
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
				System.out.println("THE CLIENT MESSAGE IS :"+petition);
				
				//CONDITIONAL CASES WHERE THE PETITION'S CLIENT WILL BE THE CARD TO SHOW
				
				//PWD
				if(petition.equals("pwd")){ 
					answer = " ABRIEF INFORMATION ABOUT pwd COMMAND WAS CREATED";
					System.out.println(answer);
					dos.writeUTF(answer);
					file = "pwd.jpg";
					openimage(file);

				}

				//LS
				if(petition.equals("ls")){ 
					answer = " ABRIEF INFORMATION ABOUT ls COMMAND WAS CREATED";
					System.out.println(answer);
					dos.writeUTF(answer);
					file = "ls.jpg";
					openimage(file);

				}

				//MKDIR
				if(petition.equals("mkdir")){ 
					answer = " ABRIEF INFORMATION ABOUT mkdir COMMAND WAS CREATED";
					System.out.println(answer);
					dos.writeUTF(answer);
					file = "mkdir.jpg";
					openimage(file); 

				}

				//SUDO
				if(petition.equals("sudo su")){ 
					System.out.println("WAITING FOR THE PASSWORD...");
					answer = "PASSWORD: ";
					dos.writeUTF(answer);
					petition = dis.readUTF();
					if(petition.equals("pass1234")){ 
						answer = " ABRIEF INFORMATION ABOUT sudo su COMMAND WAS CREATED";
						System.out.println(answer);
						dos.writeUTF(answer);
						file = "sudo.jpg";
						openimage(file);
					}
					else{
						answer = " INCORRECT PASSWORD";
						System.out.println(answer);
						dos.writeUTF(answer);
					}
					//Runtime.getRuntime().exec("sudo su"); 

					//Console terminal = System.console();
					//if (terminal==null ) {
					//System.err.println("No puedo obtener la consola.");
					//}
					//terminal=Runtime.getRuntime().exec("sudo su"); 


				}

				//KILL
				if(petition.equals("kill")){ 
					answer = " ABRIEF INFORMATION ABOUT kill COMMAND WAS CREATED";
					System.out.println(answer);
					dos.writeUTF(answer);
					file = "kill.jpg";
					openimage(file);

				}

				//PS
				if(petition.equals("ps")){ 
					answer = " ABRIEF INFORMATION ABOUT ps COMMAND WAS CREATED";
					System.out.println(answer);
					dos.writeUTF(answer);
					file = "ps.jpg";
					openimage(file);

				}

				if((!petition.equals("pwd"))&&(!petition.equals("ls"))&&(!petition.equals("mkir"))&&(!petition.equals("sudo su"))&&(!petition.equals("kill"))&&(!petition.equals("ps"))){
					answer = "COMMAND NOT FOUND";
					System.out.println("COMMAND NOT FOUND");
					dos.writeUTF(answer);
				}

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
//IN ORDER TO OPEN AN IMAGE
	public static void openimage(String file){

					     try {

					            File objectfile = new File (file);
					            Desktop.getDesktop().open(objectfile);

					     }catch (IOException ex) {

					            System.out.println(ex);
					}
	}




}







