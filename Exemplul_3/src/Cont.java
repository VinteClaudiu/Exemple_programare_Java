package multithreading_2;

public class Cont {
	private int sold = 0;
	private boolean inOperare = false;
	
	public Cont(int sold) {
		this.sold = sold;
	}
	
	public boolean isInOperare() {
		return inOperare;
	}
	
	public void blocheazaOperare() {
		inOperare = true;
	}
	
	public void elibereazaOperare() {
		inOperare = false;
	}
	
	public int getSold() {
		return sold;
	}
	
	public void depunere(int suma) {
		sold += suma;
	}
	
	public void retragere(int suma) {
		if(suma < sold) {
			sold -= suma;
		} else {
			sold = 0;
		}
	}
}
