package Servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor implements Runnable{
		private static DataInputStream in;
		private static DataOutputStream out;
		private static ServerSocket socket;
		private static  Socket socket_cli;
		public static int estado = 0;
	
	public void start() throws IOException {
		System.out.println("Esperando a conectar");
		socket = new ServerSocket(7000);
		socket_cli = socket.accept();
		System.out.println("Conectado");
		in = new DataInputStream(socket_cli.getInputStream());
		out = new DataOutputStream(socket_cli.getOutputStream());
		System.out.println("Ha conectado con " + socket_cli.getRemoteSocketAddress().toString());
	}
	public void recibir() {
		do {
			try {
				System.out.println(in.readUTF());
				enviar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}
	public void enviar() throws IOException {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Envia mensage a ");
			out.writeUTF(sc.next());

		} while (true);		
	}
	public void terminar(int direccion) throws IOException {
		socket.close();
		System.out.println("Servidor cerrado");
	}
	@Override
	public void run() {
		
		if(estado%2==1) {
			
			recibir();
			estado++;
		}
		else {
			try {
				start();
				enviar();
				estado++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
