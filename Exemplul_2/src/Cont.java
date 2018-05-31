package multithreading_1;

public class Cont {
	private int sold = 0;
	private boolean inOperare = false;
	
	public Cont(int sold) {
		this.sold = sold;
	}
	
	public boolean isInOperare() {
		return inOperare;
	}
	
	public synchronized void blocheazaOperare() {
//		synchronized (this) {
			inOperare = true;
//		}
	}
	
	public synchronized void elibereazaOperare() {
		inOperare = false;
	}
	
	public int getSold() {
		return sold;
	}
	
	public synchronized int depunere(int suma) {
		sold += suma;
		
		return sold;
	}
	
	public synchronized int retragere(int suma) {
		if(suma < sold) {
			sold -= suma;
		} else {
			sold = 0;
		}
		
		return sold;
	}
	
	public String toString() {
		
		return String.valueOf(sold);
	}
}
