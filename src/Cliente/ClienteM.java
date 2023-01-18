package Cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClienteM {
	private static Socket socket; 
	public static void main(String argv[] , String argv2[]) {
		

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
	

		byte[] mensaje_bytes = new byte[256];

		String mensajeIn = "";
				try {
			socket = new Socket("127.0.0.1", 7000);
			DataInputStream inI = new DataInputStream(socket.getInputStream());

			Thread t1 = new Thread();
			t1.start();

			
			do {
				mensajeIn = inI.readUTF();
				System.out.println(mensajeIn.toString());
				System.out.println("Escribe mensage a enviar");

			} while (true);
			//System.out.println("Comunicacion finalizada");
		}

		catch (Exception e) {
			System.err.println("Error del main");
			//System.exit(1);

		}

	}
	//@Override
	public void run() {
		try {
			System.out.println("ESTA EN EL RUN");
			Scanner sc = new Scanner(System.in);
			String mensajeOut = "";
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			do {
				System.out.println("Escribe mensage a enviar");
				mensajeOut = sc.nextLine();
				System.out.println("mensaje enviado");                                                                                                                                                                                               
				out.writeUTF(mensajeOut);

			} while (!mensajeOut.equalsIgnoreCase("fin"));
			System.out.println("Comunicacion finalizada");
		}

		catch (Exception e) {
			System.err.println("Error de run");
			//System.exit(1);

		}
	}
}