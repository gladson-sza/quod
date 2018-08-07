package game.component;

public class Enemy extends GameObject {

	public Enemy(int x, int y, int width, int height, int speedX, int speedY,
			boolean active, int hp, int damage,int typeAmmo) {
		super(x, y, width, height, speedX, speedY, active, hp, damage, typeAmmo);
	}

}
