package game.component;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy extends GameObject {

	protected ImageIcon ship;
	protected static int ENEMY_POSITION = new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH - Util.ENEMY_WIDTH);;
	
	/*
	 * Construtor
	 */
	public Enemy() {
		super(ENEMY_POSITION, 0, Util.ENEMY_WIDTH, Util.ENEMY_HEIGHT, 
				Util.SPEED_SLOW, Util.SPEED_SLOW, true);
		
		ship = new ImageIcon("res\\ship\\enemyShip.png");
		
	}

	@Override
	public void draw(Graphics g) {

		Image imageShip = ship.getImage();
		g.drawImage(imageShip, getX(), getY(), getWidth(), getHeight(), null);
	}
}
