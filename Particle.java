package Particles;

import java.util.Objects;
import java.util.Random;

public class Particle implements Cloneable, MoveableParticle {
	private int x;
	private int y;
	private static Random rand = new Random();
	private static final int originX = 0;
	private static final int originY = 0;
	
	public Particle(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Particle() {
		this.x = originX;
		this.y = originY;
	}
	
	public void initParticle(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}
	
	public Particle clone() throws CloneNotSupportedException {
		Particle c = (Particle)super.clone();
		
		return c;
	}
	
	public Particle myClone() {
		Particle c = new Particle();
		
		c.x = this.x;
		c.y = this.y;
		
		return c;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if (!(o instanceof Particle))
			return false;
		
		Particle p = (Particle)o;
		
		return (this.x == p.x) && (this.y == p.y);
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(x, y);
	}
	
	public void move(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	public void freeMove() {
		this.x += rand.nextInt(50) - 20;
		this.y += rand.nextInt(30) - 10;
	}
	
}
