package game.enemy;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import game.component.Ship;
import game.component.Util;

public abstract class Enemy extends Ship implements Runnable {

	protected ImageIcon ship;
	
	/*
	 * Construtor
	 */
	public Enemy(int enemyPosition) {
		super(enemyPosition, -Util.ENEMY_HEIGHT, Util.ENEMY_WIDTH, Util.ENEMY_HEIGHT, Util.SPEED_SLOW, Util.SPEED_SLOW,
				true);
		
		ship = new ImageIcon("res\\ship\\EnemyShip\\enemyShip.gif");

		new Thread(this).start();
	}

	public void explode() {

		if (Util.STATUS_EFFECTS) {
			try {
				AudioInputStream as = AudioSystem.getAudioInputStream(new File("res\\sound\\enemyExplosion.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(as);
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		Image imageShip = ship.getImage();
		g.drawImage(imageShip, getX(), getY(), getWidth(), getHeight(), null);
	}

}
