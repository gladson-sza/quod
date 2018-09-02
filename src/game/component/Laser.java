package game.component;


public abstract class Laser extends GameObject implements Runnable {

	/*
	 * Construtor
	 */
	public Laser(int x, int y, int width, int height, boolean active) {
		super(x, y, width, height, Util.NONE, Util.SPEED_HIGH, active);

		new Thread(this).start();
	}

}
