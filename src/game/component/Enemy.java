package game.component;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Enemy extends GameObject implements Runnable {

	protected ImageIcon ship;
	protected int position;
	protected int countExplosion;
	
	public boolean explosionSound = true;
	public boolean stop = false;

	/*
	 * Construtor
	 */
	public Enemy(int enemyPosition) {

		super(enemyPosition, 0, Util.ENEMY_WIDTH, Util.ENEMY_HEIGHT, Util.SPEED_SLOW, Util.SPEED_SLOW, true);

		setExplode(false);
		countExplosion = 0;

		new Thread(this).start();
	}

	public void countExplosionUp() {
		countExplosion++;
	}

	public int getCountExplosion() {
		return countExplosion;
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

		while (getY() <= Util.DEFAULT_SCREEN_HEIGHT) {
			try {
				Thread.sleep(30);
				moveDown();

				if (isExplode() && explosionSound) {
					try {
						AudioInputStream as = AudioSystem
								.getAudioInputStream(new File("res\\sound\\enemyExplosion.wav"));
						Clip clip = AudioSystem.getClip();
						clip.open(as);
						clip.start();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					explosionSound = false;
				}
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
