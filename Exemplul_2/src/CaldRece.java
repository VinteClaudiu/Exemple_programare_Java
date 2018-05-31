package multithreading_1;

public class CaldRece implements Runnable {
	private String mesaj;
	private int timpPauza;
	
	public CaldRece(String mesaj, int timpPauza) {
		super();
		this.mesaj = mesaj;
		this.timpPauza = timpPauza;
	}
	
	@Override
	public String toString() {
		
		return mesaj;
	}
	
	@Override
	public void run() {
		for(;;) {
			System.out.println(this.toString());
			try {
				Thread.currentThread().sleep(timpPauza);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Runnable r1 = new CaldRece("CALD", 500);
		Runnable r2 = new CaldRece("rece", 2000);
		
		new Thread(r1).start();
		new Thread(r2).start();
		
	}
}
