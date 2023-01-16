import java.io.IOException;

import Cliente.Cliente;
import Servidor.Servidor;

public class Principal {
	public static void  main(String argv[]) {
		System.out.println("Hola mundo");
		Servidor s1 = new Servidor();
		Cliente c1 = new Cliente();
		Cliente c2 = new Cliente();
		Cliente c3 = new Cliente();
		
		Thread ts1 = new Thread(s1);
		ts1.start();
		Thread ts2 = new Thread(s1);
		ts2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		generarThread(c1);
//		generarThread(c2);
//		generarThread(c3);
		System.out.println("asdasdasd");
		
		
		
		
	}
	public static void generarThread(Cliente c) {
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
			t1.start();
			t2.start();
	}
}
