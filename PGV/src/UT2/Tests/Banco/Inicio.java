package UT2.Tests.Banco;


public class Inicio {

	public static void main(String[] args) {
		
		/*Simulación
		 * con UNA sola cuenta familiar, los miembros de la familia sacan dinero mientras puedan ;-)
		 * Inicialmente una cantidad fija hasta comprender funcionamiento
		 * SOLO sacan dinero
		 * Luego se propone cantidades aleatorias
		 * y el padre y la madre,además de poder sacar, son los que hacen ingresos cada cierto tiempo
		 * 
		 * PRUEBA las diferentes situaciones
		 */
		
		String[] listClientes={"Padre Pepe","Madre María" };

		Cuenta cuenta = new Cuenta();
		cuenta.setSaldo(1000);
		
		for(int i=0; i<listClientes.length; i++){
			Clientes cliente=new Clientes(listClientes[i],cuenta);
			cliente.start();
		}
		
		
	}

}
