import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client{
	public static void main(String args[]){
		//DECLARATION
		 Socket socket = null;
		 String petition;
		 String answer = null;
		 try
			 {
			 System.out.println("I'VE CONNECTED ME TO THE PORT 8000");
			 socket = new Socket(args[0],8000);

			//LEER DE TECLADO
			 System.out.println("WRITE A MESSAGE: ");
			 Scanner myPetition = new Scanner(System.in);
			 petition = myPetition.nextLine();

			 System.out.println("I'M SENDING MY PETITION: " + petition);
			 DataOutputStream dos = new DataOutputStream( socket.getOutputStream());
			 dos.writeUTF(petition);
			 DataInputStream dis = new DataInputStream( socket.getInputStream() );
			 answer = dis.readUTF();
			 System.out.println("THE SERVER'S MESSAGE IS: " + answer);
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