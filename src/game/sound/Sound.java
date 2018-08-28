package game.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Sound extends Thread {

	private FileInputStream fis;
	private BufferedInputStream bis;
	private Player player;

	/*
	 * Contrutor
	 */
	public Sound(File mp3) {

		try {
			fis = new FileInputStream(mp3);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		// Executa pelo menos uma vez
		try {
			player.play();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
