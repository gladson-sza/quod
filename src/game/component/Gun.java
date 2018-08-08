package game.component;

public class Gun extends GameObject {

	/*
	 * Construtor
	 */
	public Gun(int x, int y, int width, int height, int speedX, int speedY, boolean active, int hp, int typeLaser) {
		super(x, y, width, height, speedX, speedY, active, hp, typeLaser);
	}
	
	public void shoot() {
		moveUp();
	}
	
}
