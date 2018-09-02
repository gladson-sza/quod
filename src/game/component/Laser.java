package game.component;

import game.sound.Sound;

import java.io.File;

public abstract class Laser extends GameObject implements Runnable {

	/*
	 * Construtor
	 */
	public Laser(int x, int y, int width, int height, boolean active) {
		super(x, y, width, height, Util.NONE, Util.SPEED_HIGH, active);

		new Sound(new File("res\\sound\\shoot.mp3")).start();
		new Thread(this).start();
	}

}
