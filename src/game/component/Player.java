package game.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.component.Util;

public class Player extends GameObject {

	protected ImageIcon ship;
	protected int position = 0;

	/*
	 * Construtor
	 */
	public Player() {

		super(Util.PLAYER_POSITION_X, Util.PLAYER_POSITION_Y, Util.PLAYER_WIDTH, Util.PLAYER_HEIGHT, Util.SPEED_MEDIUM,
				Util.SPEED_MEDIUM, true);
	}

	@Override
	public void draw(Graphics g) {
		// Altera a imagem do array
		if (position == 0)
			ship = new ImageIcon(Util.PLAYER_IMAGES[position++]);
		else if (position == 1)
			ship = new ImageIcon(Util.PLAYER_IMAGES[position--]);

		Image imageShip = ship.getImage();
		g.drawImage(imageShip, getX(), getY(), getWidth(), getHeight(), null);
	}

}
