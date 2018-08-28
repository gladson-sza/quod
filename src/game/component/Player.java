package game.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.component.Util;

public class Player extends Ship {

	protected ImageIcon ship;

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

		// Verifica se pode disparar
		if (shoot) {
			alLaser.add(new Laser(getX() + 25, getY() + 5, Util.SPEED_HIGH, Util.SPEED_HIGH, true, 0));

			setShoot(false);
		}

		Image imageShip = ship.getImage();
		g.drawImage(imageShip, getX(), getY(), getWidth(), getHeight(), null);
	}

}
