import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.TException;
import java.util.Scanner;

public class HolaCliente {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws TException {
		TSocket transporte = new TSocket("localhost", 9090);
		TBinaryProtocol protocolo = new TBinaryProtocol(transporte);
		ServicioCalculadora.Client cliente = new ServicioCalculadora.Client(protocolo);
		transporte.open();
		// String cadena = cliente.hola_func();
		System.out.println("Bienvenido");
		int opcion;
		Boolean var = true;
		double numero1 = 0;
		double numero2 = 0;
		while(var) {
			System.out.println("¿Que operacion quieres realizar?");
			System.out.println("1.Suma\n2.Multiplicar\n3.Restar\n4.Dividir\n5.Salir\n");
			opcion = sc.nextInt();
			if (opcion != 5) { 
				System.out.println("Ingresa dos numeros");
				numero1 = sc.nextDouble();
				numero2 = sc.nextDouble();
			}
			double resultado = 0;
			switch(opcion){
				case 1:
					 resultado = cliente.suma(numero1,numero2);
				break;
				case 2:
					resultado = cliente.multiplicar(numero1,numero2);
				break;
				case 3:
					resultado = cliente.restar(numero1,numero2);
				break;
				case 4:
				resultado = cliente.dividir(numero1,numero2);
				break;
				case 5:
				var = false;
				break;
				default:
					System.out.println("Por favor selecciona una opción que se encuentre en el menú");

			}
			if(opcion<5 && opcion>0){
				System.out.println("\nEl resultado es: " + resultado + "\n");
			}
		}
		transporte.close();
	}
}
