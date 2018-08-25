package game.component;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Laser extends GameObject implements Runnable {

	private ImageIcon laser;
	Thread t;

	/*
	 * Construtor
	 */
	public Laser(int x, int y, int speedX, int speedY, boolean active) {
		super(x, y, 47, 30, speedX, speedY, active);

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
		
		try {
			AudioInputStream as = AudioSystem.getAudioInputStream(new File("res\\sound\\shoot.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(as);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (getY() >= -47) {
			try {
				Thread.sleep(45);
				moveUp();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}
