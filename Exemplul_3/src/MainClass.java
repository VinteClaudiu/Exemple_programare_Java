package multithreading_2;

import java.util.concurrent.Semaphore;

public class MainClass {
	public static void main(String args[]) throws InterruptedException 
	{
	   Cont cont = new Cont(100);
	   
		// creating a Semaphore object
	   // with number of permits 1
	   Semaphore sem = new Semaphore(1, true);
	    
	   // creating two threads with name A and B
	   // Note that thread A will increment the count
	   // and thread B will decrement the count
	   OperatorBancar t1 = new OperatorBancar(cont, sem, "A", 10, 5);
	   OperatorBancar t2 = new OperatorBancar(cont, sem, "B", 20, 10);
	   OperatorBancar t3 = new OperatorBancar(cont, sem, "C", 5, 1);
	   
	   // start threads
	   t1.start();
	   t2.start();
	   t3.start();
	    
	   // asteapta de firele de executie copil sa isi termine executia
	   t1.join();
	   t2.join();
	   t3.join();
	    
	   // count will always remain 0 after
	   // both threads will complete their execution
	   System.out.println("Sold final: " + cont.getSold());
	}
}