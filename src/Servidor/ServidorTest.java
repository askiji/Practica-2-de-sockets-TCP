package Servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorTest implements Runnable{
		private DataInputStream in;
		private DataOutputStream out;
		private ServerSocket socket;
		private Socket socket_cli;
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
			try {
				start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			recibir();
			estado++;
		}
		else {
			try {
				
				enviar();
				estado++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
