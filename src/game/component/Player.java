package game.component;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	/*
	 * Construtor
	 */
	public Player(int x, int y, int width, int height, int speedX, int speedY,
			boolean active, int hp, int damage, int typeAmmo) {
		super(x, y, width, height, speedX, speedY, active, hp, damage, typeAmmo);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(20, 60, getWidth(), getHeight());
	}

}
