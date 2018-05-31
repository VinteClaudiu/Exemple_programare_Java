package multithreading_1;

import java.io.FileWriter;
import java.io.IOException;

public class ReceCald extends Thread {
	private String s;
	private int timpPauza;
	private static long index = 0;
	
	public ReceCald(String s, int timpPauza) {
		this.s = s;
		this.timpPauza = timpPauza;
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.println(++index + "," + s);
			FileWriter outFile = null;
			try {
				outFile = new FileWriter("ReceCald.txt", true);
				outFile.write(index + "," + s);
				outFile.write("\r\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					outFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			
			
			try {
				Thread.sleep(timpPauza);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new ReceCald("rece", 9);
		Thread t2 = new ReceCald("CALD", 5);
		
		t1.start();
		t2.start();
	}
	
}
