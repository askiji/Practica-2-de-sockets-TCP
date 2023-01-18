package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorM {

	public static void main(String argv[]) {
		ServerSocket socket;

		boolean fin = false;
		boolean start = true;
		try {
			System.out.println("Esperando a que alguien conecte");
			socket = new ServerSocket(7000);
			Socket socket_cli = socket.accept();
			System.out.println("Ha conectado con " + socket_cli.getRemoteSocketAddress().toString());
			System.out.println("Esperando a recibir mensage");
			DataInputStream in = new DataInputStream(socket_cli.getInputStream());
			DataOutputStream out = new DataOutputStream(socket_cli.getOutputStream());
			String mensajeIn = "";
			String mensajeOut = "";
			do {
				if(start) {
					mensajeOut = "Hola desde el servidor";
					out.writeUTF(mensajeOut);
					start = false;
				}

				else {
					mensajeIn = in.readUTF();
					if (mensajeIn.equalsIgnoreCase("fin")) {
						socket.close();
					}
					System.out.println("Mensange recivido " + mensajeIn);
					mensajeOut = mensajeIn.toUpperCase();
					System.out.println("Se ah contestado el mismo mensaje en mayusculas");
					out.writeUTF(mensajeOut);
				}

			} while (!mensajeIn.equalsIgnoreCase("fin"));
			System.out.println("Comunicación finalizada");
		}

		catch (Exception e) {
			System.err.println(e.getMessage());
			//System.exit(1);
		}
	}
}
