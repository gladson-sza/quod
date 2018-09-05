package game.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PlayerLaser extends Laser {

	private Image laser;

	/*
	 * Construtor
	 */
	public PlayerLaser(int x, int y, int width, int height, boolean active) {
		super(x, y, width, height, active);

		laser = new ImageIcon("res\\effects\\laserPlayer.png").getImage();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(laser, getX(), getY(), getWidth(), getHeight(), null);
	}

	@Override
	public void run() {
		while (getY() <= Util.DEFAULT_SCREEN_HEIGHT + getHeight()) {
			try {
				Thread.sleep(45);
				if (!Util.STOP)
					moveUp();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
}
