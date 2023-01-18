package Cliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTest implements Runnable{

	private  DataOutputStream out ;
	private  DataInputStream in ;
	private  ServerSocket socket;
	private  Socket socket_cli;
	public static int estado = 0;
	
	
	public void start() throws IOException {
		System.out.println("Serever" + estado);
		socket_cli = new Socket("127.0.0.1", 7000);
		in = new DataInputStream(socket_cli.getInputStream());
		out = new DataOutputStream(socket_cli.getOutputStream());
	}

	public void recibir() {
		do {
			try {
				System.out.println("Mensage recivido");
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
		System.out.println("Conexion cerrada");
	}
	@Override
	public void run() {
		if(estado%2==1) {
			;
			recibir();
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
