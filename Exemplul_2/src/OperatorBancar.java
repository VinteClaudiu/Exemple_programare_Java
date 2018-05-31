package multithreading_1;

public class OperatorBancar extends Thread {
	private Cont cont;
	private String nume;
	private int timpProcesare;
	private int suma;
	
	public OperatorBancar(Cont cont, String nume, int timpProcesare, int suma) {
		super();
		this.cont = cont;
		this.nume = nume;
		this.timpProcesare = timpProcesare;
		this.suma = suma;
	}
	
	private synchronized void operareCont() {
		if(cont.isInOperare()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		cont.blocheazaOperare();
		System.out.println("Sold inainte de operare " + nume + " " + cont.toString());
		
		//cont.depunere(suma);
		cont.retragere(suma);
		System.out.println("Sold dupa operare " + nume + " " + cont.toString());
		
		cont.elibereazaOperare();
		notifyAll();		
	}
	
	@Override
	public void run() {
//		while(true) {
		while(cont.getSold() > 0) {
			try {
				Thread.currentThread().sleep(timpProcesare);
				
				operareCont();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Thread.currentThread().interrupt();
	}
	
	public static void main(String[] args) {
		final int noOfThreads = Integer.parseInt(args[0]);
		Cont cont = new Cont(1000);
		
		Thread[] operatori = new OperatorBancar[noOfThreads];
		
		for(int i=0; i<noOfThreads; i++) {
			operatori[i] = new OperatorBancar(cont, "O"+(i+1), 10*(i+1), 10*(i+1));
			operatori[i].start();
		}
		
		for (int i=0; i<noOfThreads; i++)
			try {
				operatori[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
