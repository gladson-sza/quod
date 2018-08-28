package game.component;

import java.util.ArrayList;

public abstract class Ship extends GameObject {

	public ArrayList<Laser> alLaser;
	protected boolean shoot;
	protected int position;
	protected int countExplosion;

	/*
	 * Construtor
	 */
	public Ship(int x, int y, int width, int height, int speedX, int speedY, boolean active) {
		super(x, y, width, height, speedX, speedY, active);
		
		position = 0;
		countExplosion = 0;
		shoot = false;
		setExplode(false);
		alLaser = new ArrayList<Laser>();
	}

	/*
	 * Efetua ou não o disparo
	 */
	public void setShoot(boolean status) {
		shoot = status;
	}

	/*
	 * Contagem de explosão
	 */
	public void countExplosionUp() {
		countExplosion++;
	}

	/*
	 * Captura a contagem de explosão
	 */
	public int getCountExplosion() {
		return countExplosion;
	}
}
