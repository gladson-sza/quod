package game.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Laser extends GameObject implements Runnable {
	
	private Thread t;
	private int shootPosition;
	private ImageIcon laser;
	
	/*
	 * Construtor
	 */
	public Laser(int x, int y, int width, int height, int speedX, int speedY, boolean active, int hp, int typeLaser) {
		super(x, y, width, height, speedX, speedY, active, hp, typeLaser);
		
		laser = new ImageIcon("res\\hud\\laser.png");
		
		t = new Thread(this);
		
	}
	
	/*
	 * Classe responsável por obter a localização
	 * da fonte do disparo do projétil
	 */
	public void shoot() {
		shootPosition = getX() - 9;
		this.setActive(true);
		t.start();
	}

	@Override
	public void draw(Graphics g) {
		Image imageLaser = laser.getImage();
		g.drawImage(imageLaser, shootPosition, getY() + 5, 47, 40, null);
	}

	@Override
	public void run() {
		
		while (this.getY() < Util.DEFAULT_SCREEN_HEIGHT) {
			try {
				Thread.sleep(100);
				this.moveUp();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
