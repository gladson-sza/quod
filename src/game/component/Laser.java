package game.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Laser extends GameObject {

	private ImageIcon laser;

	/*
	 * Construtor
	 */
	public Laser(int x, int y, int speedX, int speedY, boolean active) {
		super(x, y, Util.NONE, Util.NONE, speedX, speedY, active);

		laser = new ImageIcon("res\\effects\\laser.png");

	}

	@Override
	public void draw(Graphics g) {

		moveUp();

		Image imageLaser = laser.getImage();
		g.drawImage(imageLaser, getX(), getY() + 5, 47, 30, null);
	}

}
