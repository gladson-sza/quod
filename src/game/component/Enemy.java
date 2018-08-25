package game.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy extends GameObject {

	protected ImageIcon ship;
	protected int position;
	protected int countExplosion;
	
	public boolean stop = false;
	/*
	 * Construtor
	 */
	public Enemy(int enemyPosition) {

		super(enemyPosition, 0, Util.ENEMY_WIDTH, Util.ENEMY_HEIGHT, Util.SPEED_SLOW, Util.SPEED_SLOW, true);

		setExplode(false);
		countExplosion = 0;

	}

	public void countExplosionUp() {
		countExplosion++;
	}

	public int getCountExplosion() {
		return countExplosion;
	}

	@Override
	public void draw(Graphics g) {
		if(stop == false) {
			moveDown();
		}

		moveDown();

		// Altera a imagem no array
		if (position == 0)
			ship = new ImageIcon(Util.ENEMY_IMAGES[position++]);
		else if (position == 1)
			ship = new ImageIcon(Util.ENEMY_IMAGES[position--]);

		Image imageShip = ship.getImage();
		g.drawImage(imageShip, getX(), getY(), getWidth(), getHeight(), null);
	}

}
