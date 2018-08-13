package game.component;

public class Enemy extends GameObject {

	/*
	 * Construtor
	 */
	public Enemy(int x, int y, int width, int height, int speedX, int speedY,
			boolean active, int hp, int typeLaser) {
		super(x, y, width, height, speedX, speedY, active, hp);
	}

}
