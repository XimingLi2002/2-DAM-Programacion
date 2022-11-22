package UT2.Tests.Banco;

public class Cuenta {
	private double saldo;
	
	public Cuenta(){
		saldo=0;
	}
	
	public double getSaldo(){
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public synchronized boolean Retirar(double valor){
			
			if (valor<= getSaldo()){
				saldo= saldo - valor;
				System.out.println(Thread.currentThread().getName()+" ha sacado "+valor+" y queda "+
						this.getSaldo());
				return true;
			}else{
				return false;
			}
		
	}
	
}
