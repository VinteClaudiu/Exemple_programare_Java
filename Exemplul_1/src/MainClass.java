package Particles;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;

/** acesta este un comentariu pentru documentatare */
public class MainClass {
	public static void main(String[] args) {
		final int NO_OF_PARTICLES = 5;
		
		Particle p1 = new Particle();
		
		Particle[] vp = new Particle[NO_OF_PARTICLES];
		
		for (int i=0; i<vp.length; i++) {
			// vp[i] = new Particle(i,i);
			vp[i] = new Particle();
			vp[i].initParticle(i, i);
			System.out.println(vp[i].toString());
		}
		
		p1 = vp[1];
		vp[1].freeMove();
		System.out.println("shallow copy");
		System.out.println(p1.toString());
		System.out.println(vp[1].toString());
		
		try {
			p1 = vp[1].clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vp[1].freeMove();
		System.out.println("deep copy");
		System.out.println(p1.toString());
		System.out.println(vp[1].toString());
		
		AtomicParticle ap1 = new AtomicParticle(0, 0, "electron", 2.4, 120000.0);
		

		try {
			vp[0] = ap1.clone();
			vp[1] = vp[0];
			vp[2] = p1;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		
		//vp[0] = ap1;
		//vp[0] = ap1.clone();
		//ap1.freeMove();
		System.out.println(ap1.toString());
		for (int i=0; i<vp.length; i++) {
			System.out.println(vp[i].toString());
		}
		
		if (vp[0].equals(vp[1]))
			System.out.println("Sunt egale");
		else
			System.out.println("NU sunt egale"); 
		System.out.println(vp[0].hashCode() + "," +
				vp[1].hashCode());
		
		if (vp[0].equals(vp[2]))
			System.out.println("Sunt egale");
		else
			System.out.println("NU sunt egale"); 
		System.out.println(vp[0].hashCode() + "," +
				vp[2].hashCode());
		
		FileWriter outFile = null;
		
		try {
			outFile = new FileWriter("Particule.csv", true);
			for (int i=0; i<vp.length; i++) {
				outFile.write(vp[i].toString());
				outFile.write("\r\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				outFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ArrayDeque<Particle> inDeque = new ArrayDeque<Particle>();
		
		FileReader inFile = null;
		
		try {
			inFile = new FileReader("Particule.csv");
			Scanner fileScan = new Scanner(inFile);
			
			while(fileScan.hasNextLine()) {
				String line = fileScan.nextLine();
				
				Scanner lineScan = new Scanner(line);
				lineScan.useDelimiter(",");
				
				int x = lineScan.nextInt();
				int y = Integer.parseInt(lineScan.next());
				String name = null;
				double mass = 0.0;
				double speed = 0.0;
				
				if (lineScan.hasNext()) {
					name = lineScan.next();
					mass = Double.parseDouble(lineScan.next());
					speed = Double.parseDouble(lineScan.next());
				}
				Particle p = null;
				
				if (name != null)
					p = new AtomicParticle(x, y, name, mass, speed);
				else
					p = new Particle(x,y);
				
				inDeque.offerLast(p);		
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				inFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		while(!inDeque.isEmpty()) {
			System.out.println(inDeque.pollFirst().toString());
		}
		
	}
}
