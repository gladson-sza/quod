/**
 * @author Gladson Souza de Araújo
 * @author Paulo Victor Ribeiro da Silva
 * 
 */

package game.main;

import game.component.Laser;
import game.component.Player;
import game.component.Util;
import game.phase.Phase;
import game.phase.Stage01;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class QuodGame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	public Phase phase;
	public boolean[] keyPress;
	

	/*
	 * Construtor
	 */
	public QuodGame() {
		mainScreen();
	}

	/*
	 * Configurações da Tela
	 */
	public void mainScreen() {

		setTitle("Quod - The Game");
		setSize(Util.DEFAULT_SCREEN_WIDTH, Util.DEFAULT_SCREEN_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		keyPress = new boolean[5];
		phase = new Stage01("res\\background\\galaxy_background01.jpg", new Player(20), 0);
		
		add(phase);
		addKeyListener(this);

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}

	/*
	 * Método Principal
	 */
	public static void main(String[] args) {
		new QuodGame().gameStart();
	}

	private void gameStart() {
		
		while (true) {
			repaint();
		}
		
	}

	/* Teclado */
	public void setKey(int keyCode, boolean status) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent key) {

		switch (key.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (phase.player.getX() > 0)
				phase.player.moveLeft();
			break;
		case KeyEvent.VK_A:
			if (phase.player.getX() > 0)
				
				phase.player.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			if (phase.player.getX() + phase.player.getWidth() < Util.DEFAULT_SCREEN_WIDTH)
				phase.player.moveRight();
			break;
		case KeyEvent.VK_D:
			if (phase.player.getX() + phase.player.getWidth() < Util.DEFAULT_SCREEN_WIDTH)
				phase.player.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			// Cria um laser cada vez que a tecla de espaço é acionada
			phase.alLaser.add(new Laser(phase.player.getX() + 25, phase.player.getY() + 5, 
					Util.SPEED_HIGH, Util.SPEED_HIGH, true));
			break;
		default:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent key) {
		setKey(key.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent key) {
		setKey(key.getKeyCode(), true);
	}

}
