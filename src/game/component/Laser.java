package game.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Laser extends GameObject implements Runnable {

	private Thread t;
	private ImageIcon laser;

	/*
	 * Construtor
	 */
	public Laser(int x, int y, int speedX, int speedY, boolean active) {
		super(x, y, Util.NONE, Util.NONE, speedX, speedY, active);

		laser = new ImageIcon("res\\effects\\laser.png");

		t = new Thread(this);
		t.start();

	}

	@Override
	public void draw(Graphics g) {
		Image imageLaser = laser.getImage();
		g.drawImage(imageLaser, getX(), getY() + 5, 47, 30, null);
	}

	@Override
	public void run() {

		while (this.getY() < Util.DEFAULT_SCREEN_HEIGHT) {
			try {
				Thread.sleep(60);
				this.moveUp();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
