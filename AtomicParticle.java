package Particles;

import java.util.Objects;

public class AtomicParticle extends Particle implements Cloneable, MoveableParticle {
	String name;
	double mass;
	double speed;
	
	public AtomicParticle() {
		super();
		this.name = "default";
		this.mass = 0.0;
		this.speed = 0.0;
	}

	public AtomicParticle(int x, int y, String name, double mass, double speed) {
		super(x, y);
		this.name = name;
		this.mass = mass;
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public AtomicParticle clone() throws CloneNotSupportedException {
		AtomicParticle c = (AtomicParticle)super.clone();
		
		return c;
	}
	
	public String toString() {
		
		return super.toString() + "," +
				this.name + "," +
				this.mass + "," +
				this.speed;
	}
	
	public AtomicParticle myClone() {
		AtomicParticle c = new AtomicParticle();
		
		c.setX(this.getX());
		c.setY(this.getY());
		c.name = this.name;
		c.mass = this.mass;
		c.speed = this.speed;
		
		return c;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (!(o instanceof AtomicParticle))
			return false;
		
		AtomicParticle a = (AtomicParticle)o;
		
		return super.equals(o) && (this.name.equals(a.name)) &&
				(this.mass == a.mass) &&
				(this.speed == a.speed);
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(super.hashCode(), name, mass, speed);
	}
}
