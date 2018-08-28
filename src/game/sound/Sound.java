package game.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sound extends Thread {
	
	private FileInputStream fis;
	private BufferedInputStream bis;
	private Player player;
	private boolean loop;

	/*
	 * Contrutor
	 */
	public Sound(File mp3, boolean loop) {
		this.loop = loop;
		
		try {
			fis = new FileInputStream(mp3);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/* MusicLoop */
	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	/*
	 * Desliga a música
	 */
	public void close() {
		setLoop(false);
		try {
			fis.close();
			bis.close();
		} catch (IOException e) {
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
