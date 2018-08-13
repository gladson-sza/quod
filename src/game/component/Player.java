package game.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.component.Util;

public class Player extends GameObject {

	public Laser laser;
	protected ImageIcon ship;
	
	/*
	 * Construtor
	 */
	public Player(int hp, int typeLaser) {
		
		super(Util.PLAYER_POSITION_X, Util.PLAYER_POSITION_Y, 
				Util.PLAYER_WIDTH, Util.PLAYER_HEIGHT, 
				Util.SPEED_MEDIUM, Util.SPEED_MEDIUM, true, hp, typeLaser);
		
		laser = new Laser(this.getX() , this.getY(), 
				30, 30,
				Util.SPEED_HIGH, Util.SPEED_HIGH,
				false, hp, typeLaser);
		
		ship = new ImageIcon("res\\hud\\playerShip.png");
	}
	
	@Override
	public void draw(Graphics g) {

		Image imageShip = ship.getImage();
		g.drawImage(imageShip, getX(), getY(), getWidth(), getHeight(), null);
	}

}
