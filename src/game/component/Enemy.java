package game.component;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import game.phase.Phase;
import game.component.*;

public class Enemy extends GameObject implements Runnable {
	
	protected Thread t;
	protected ImageIcon ship;
	protected static int ENEMY_POSITION = new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH - Util.ENEMY_WIDTH);
	
	private boolean alive;
	private int position = 0;
	private boolean side = true;
	/*
	 * Construtor
	 */
	public Enemy() {
		super(ENEMY_POSITION, 0, Util.ENEMY_WIDTH, Util.ENEMY_HEIGHT, 
				Util.SPEED_SLOW, Util.SPEED_SLOW, true);
		
		
		alive = true;
		
		t = new Thread(this);
		t.start();
	}

	@Override
	public void draw(Graphics g) {
		if(position == 0)
			ship = new ImageIcon(Util.ENEMY_IMAGES[position++]);
		
		else if(position == 1)
			ship = new ImageIcon(Util.ENEMY_IMAGES[position--]);
			
		Image imageShip = ship.getImage();
		g.drawImage(imageShip, getX(), getY(), getWidth(), getHeight(), null);
	}
	
	
	@Override
	public void run() {
		while (true) {
			try {
				if(this.isActive() == false) {
					
;				}
				Thread.sleep(60);
				this.moveDown();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
