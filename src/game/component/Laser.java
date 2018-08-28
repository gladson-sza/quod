package game.component;

import game.sound.Sound;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Laser extends GameObject implements Runnable {

	private ImageIcon laser;
	private int direction;

	/*
	 * Construtor
	 */
	public Laser(int x, int y, int speedX, int speedY, boolean active, int direction) {
		super(x, y, 47, 30, speedX, speedY, active);

		this.direction = direction;
		laser = new ImageIcon("res\\effects\\laser.png");
		new Thread(this).start();

	}

	@Override
	public void draw(Graphics g) {

		Image imageLaser = laser.getImage();
		g.drawImage(imageLaser, getX(), getY() - 30, 47, 30, null);
	}

	@Override
	public void run() {

		new Sound(new File("res\\sound\\shoot.mp3")).start();

		if (direction == 0) {
			while (getY() >= -47) {
				try {
					Thread.sleep(45);
					if (!Util.STOP)
						moveUp();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

		}

		if (direction == 1) {
			while (getY() <= Util.DEFAULT_SCREEN_HEIGHT + 47) {
				try {
					Thread.sleep(45);
					if (!Util.STOP)
						moveDown();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}

			}
		}

	}

}
