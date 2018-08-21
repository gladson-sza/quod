/**
 * @author Gladson Souza de Araújo
 * @author Paulo Victor Ribeiro da Silva
 * 
 */

package game.main;

import game.component.Enemy;
import game.component.Laser;
import game.component.Player;
import game.component.Util;
import game.phase.Phase;
import game.phase.Stage01;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;

public class QuodGame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	public Phase phase;
	public boolean[] keyControl;
	private int shootCount = 5;
	private int enemyCount = 45;

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

		keyControl = new boolean[3];
		phase = new Stage01("res\\background\\galaxy_background01.jpg", new Player(), 0);

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
		QuodGame qg = new QuodGame();
		while (Util.PLAYING) {
			qg.gameStart();
		}

		System.exit(0);
	}

	/*
	 * GameLopp
	 */
	private void gameStart() {

		if (enemyCount > new Random().nextInt(15) + 20) {
			phase.alEnemy.add(new Enemy(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH - Util.ENEMY_WIDTH)));
			enemyCount = 0;
		}

		gameControl();
		repaint();

		// Executa o laço a cada 45ms e incremeta o contador de disparos
		try {
			Thread.sleep(45);
			enemyCount++;
			shootCount++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/* Teclado */
	public void setKey(int key, boolean status) {
		switch (key) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			keyControl[0] = status;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			keyControl[1] = status;
			break;
		case KeyEvent.VK_SPACE:
			keyControl[2] = status;
			break;
		}
	}

	/*
	 * Faz a verificação do teclado
	 */
	private void gameControl() {

		// Esqurda
		if (keyControl[0] && phase.player.getX() > 0) {
			phase.player.moveLeft();
		}

		// Direita
		if (keyControl[1] && (phase.player.getX() + phase.player.getWidth() < phase.getWidth())) {
			phase.player.moveRight();
		}

		// Disparos
		if (keyControl[2] && shootCount > 7) {
			phase.alLaser.add(new Laser(phase.player.getX() + 25, phase.player.getY() + 5, Util.SPEED_HIGH,
					Util.SPEED_HIGH, true));

			shootCount = 0;
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		setKey(key, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		setKey(key, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
