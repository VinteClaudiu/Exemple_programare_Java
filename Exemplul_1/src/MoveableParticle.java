package Particles;

public interface MoveableParticle {
	public void move(int newX, int newY);
	public void freeMove();
	
	public static double distance(Particle a, Particle b) {
		
		return Math.sqrt(Math.pow((a.getX() - b.getX()), 2) +
				Math.pow(a.getY() - b.getY(), 2));
	}
}
