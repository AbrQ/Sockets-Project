import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.security.*;
import javax.crypto.*;
import java.io.Console;

public class Client{
	public static void main(String args[]) throws Exception{
		//DECLARATION
		 Socket socket = null;
		 String petition ="";
		 String answer = null;
		 String specialKey = "";
		 try
			 {
			 //NEW CODE FOR MATCHING PASSWORDS
			 ObjectInputStream ois = new ObjectInputStream(new FileInputStream("password.ser"));
			 Key password = (Key)ois.readObject();
			 ois.close();
			 System.out.println("CLIENT'S KEY: "+password);

			 System.out.println("I'VE CONNECTED ME TO THE PORT 8000");
			 socket = new Socket(args[0],8000);
			 
			 DataOutputStream dos = new DataOutputStream( socket.getOutputStream());
			 DataInputStream dis = new DataInputStream( socket.getInputStream() );
			 do{ 
			//TEXT ENTRY
			 System.out.println("WRITE A MESSAGE: ");
			 Scanner myPetition = new Scanner(System.in);
			 petition = myPetition.nextLine();

			 System.out.println("I'M SENDING MY PETITION: " + petition);
			 
			 dos.writeUTF(petition);
			 answer = dis.readUTF();
			
			if(answer.equals("PASSWORD: ")){
			 	System.out.println(answer);
			 	Scanner pass = new Scanner(System.in);
				petition = pass.nextLine();	
				dos.writeUTF(petition);
				
			 }
			 else{ 
			 	System.out.println("THE SERVER'S MESSAGE IS: " + answer);
			 }
			 
			 }
			 while(!petition.equals("close"));
			 dos.close();
			 dis.close();
			 socket.close();
 			}
		 catch(IOException e)
			 {
			 System.out.println("java.io.IOException generated");
			 e.printStackTrace();
			 }
		}
	}	









