package game.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.component.Util;

public class Player extends GameObject {

	protected ImageIcon ship;
	
	/*
	 * Construtor
	 */
	public Player() {
		
		super(Util.PLAYER_POSITION_X, Util.PLAYER_POSITION_Y, 
				Util.PLAYER_WIDTH, Util.PLAYER_HEIGHT, 
				Util.SPEED_MEDIUM, Util.SPEED_MEDIUM, true);
		
		ship = new ImageIcon("res\\ship\\playerShip.png");
		
	}
	
	@Override
	public void draw(Graphics g) {

		Image imageShip = ship.getImage();
		g.drawImage(imageShip, getX(), getY(), getWidth(), getHeight(), null);
	}

}
