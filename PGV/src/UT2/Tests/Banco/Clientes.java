package UT2.Tests.Banco;

public class Clientes extends Thread {
	private Cuenta cu;

	public Clientes(String nombre, Cuenta cu) {
		setName(nombre);
		this.cu = cu;
	}

	public void run() {
		// Cada cliente saca 5 veces dinero del cajero
		// Una cantidad de efectivo aleatoria, si hay saldo

		for (int i = 0; i < 5; i++) {
			//double aleatorio = (double) (Math.random() * 600) + 1;
			int aleatorio=300;
			//synchronized (cu) {	//Monitor sincroniza OBJ
				/*if (currentThread().getName().equals("Jes�s")) {
					cu.setSaldo(500);	//Jes�s es prod y cons
				}*/
				if (!cu.Retirar(aleatorio)) {
					System.out.println(currentThread().getName()
							+ " no ha podido sacar dinero");
				}
			//}

			/*try {
				sleep((int) Math.random() * 100);
			} catch (InterruptedException e) {
			}*/

		}
	}

}
