package game.component;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Enemy extends Ship implements Runnable {

	protected ImageIcon ship;

	protected int goDirection;
	public boolean stop = false;
	private int shootTime = 0;

	/*
	 * Construtor
	 */
	public Enemy(int enemyPosition) {

		super(enemyPosition, 0, Util.ENEMY_WIDTH, Util.ENEMY_HEIGHT, Util.SPEED_SLOW, Util.SPEED_SLOW, true);

		goDirection = new Random().nextInt(2);
		new Thread(this).start();
	}

	public void explode() {
		try {
			AudioInputStream as = AudioSystem.getAudioInputStream(new File("res\\sound\\enemyExplosion.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(as);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {

		// Altera a imagem no array
		if (position == 0)
			ship = new ImageIcon(Util.ENEMY_IMAGES[position++]);
		else if (position == 1)
			ship = new ImageIcon(Util.ENEMY_IMAGES[position--]);

		Image imageShip = ship.getImage();
		g.drawImage(imageShip, getX(), getY(), getWidth(), getHeight(), null);
	}

	@Override
	public void run() {

		// Desce até ser atingido
		while (getY() <= Util.DEFAULT_SCREEN_HEIGHT) {
			try {
				Thread.sleep(45);

				if (!Util.STOP) {
					moveDown();

					/*
					 * if (goDirection == 1) { moveRight();
					 * 
					 * if (getX() + getWidth() > Util.DEFAULT_SCREEN_WIDTH) goDirection = 0;
					 * 
					 * }
					 * 
					 * if (goDirection == 0) { moveLeft();
					 * 
					 * if (getX() < 0) goDirection = 1; }
					 */

					/*
					 * if (shootTime > 15) { alLaser.add(new Laser(getX() + 25, getY() + 5,
					 * Util.SPEED_HIGH, Util.SPEED_HIGH, true, 1)); shootTime = 0; }
					 */

					shootTime++;

				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
