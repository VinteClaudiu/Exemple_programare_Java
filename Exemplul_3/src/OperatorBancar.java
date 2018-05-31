package multithreading_2;

import java.util.concurrent.Semaphore;


class OperatorBancar extends Thread {
	private Semaphore sem;
	private String threadName;
	private Cont cont;
	private int timpProcesare;
	private int suma;
	
	 public OperatorBancar(Cont cont, Semaphore sem, String threadName, 
			 int timpProcesare, int suma) 
	 {
	     super(threadName);
	     this.cont = cont;
	     this.sem = sem;
	     this.threadName = threadName;
	     this.timpProcesare = timpProcesare;
	     this.suma = suma;
	 }

	 @Override
	 public void run() {
	     
		 while (cont.getSold() > 0) {
//	         System.out.println("Starting " + threadName);
	         try
	         {
	             // First, get a permit.
//	             System.out.println(threadName + " is waiting for a permit.");
	          
	             // acquiring the lock
	             sem.acquire();
	             
//	             System.out.println(threadName + " gets a permit.");
	             
	             Thread.currentThread().sleep(timpProcesare);
	             
	             System.out.println("Sold inainte de retragere " + threadName + " " 
	            		 + cont.getSold());
	            	 
	             cont.retragere(suma);
	             
	             System.out.println("Sold dupa retragere " + threadName + " " 
	            		 + cont.getSold());
	          
	         } catch (InterruptedException exc) {
	                 System.out.println(exc);
	             }
	      
             // Release the permit.
//	         System.out.println(threadName + " releases the permit.");
             sem.release();
		 }
		 
		 Thread.currentThread().interrupt();
	 }
}

