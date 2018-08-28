package game.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class EnemyLaser extends Laser {
	
	private Image laser;
	
	/*
	 * Construtor
	 */
	public EnemyLaser(int x, int y, int width, int height, boolean active) {
		super(x, y, width, height, active);
		
		laser = new ImageIcon("res\\effects\\laserEnemy.png").getImage();
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(laser, getX(), getY(), getWidth(), getHeight(), null);
	}
	
	@Override
	public void run() {
		while (getY() >= -getHeight()) {
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
