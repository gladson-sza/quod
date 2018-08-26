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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;

public class QuodGame extends JFrame implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	public boolean status = false;

	public Phase phase;
	public MainMenuScreen menu;
	public Loading loading;
	public Enemy enemy;
	public GameOver over;

	public boolean[] keyControl;
	private int enemyCount = 45;

	/*
	 * Construtor
	 */
	public QuodGame() {

		over = new GameOver();
		phase = new Stage01("res\\background\\galaxy_background01.jpg", new Player(), 0);
		menu = new MainMenuScreen();
		loading = new Loading();

		setTitle("Quod - The Game");
		setSize(Util.DEFAULT_SCREEN_WIDTH, Util.DEFAULT_SCREEN_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		// adiciona a tela de carregando
		add(loading);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// fecha o JPanel da Tela de carregando e abre o menu
		loading.setVisible(false);
		this.add(this.menu);
		menu.requestFocus();

		phase.addKeyListener(this);
		phase.setFocusable(true);
		menu.jbPlay.addActionListener(this);
		keyControl = new boolean[3];

	}

	/*
	 * Método Principal
	 */
	public static void main(String[] args) {		

		// Inicia o Jogo
		QuodGame qg = new QuodGame();
		qg.gameStart();

		System.exit(0);
	}

	/*
	 * GameLopp
	 */
	private void gameStart() {
		
		while (Util.PLAYING) {

			if (status == true) {
				if (enemyCount > new Random().nextInt(15) + 20) {
					phase.alEnemy.add(new Enemy(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH - Util.ENEMY_WIDTH)));
					enemyCount = 0;
				}
			}

			gameControl();
			repaint();

			// Leitura do botão pause
			phase.jbStop.addActionListener(this);

			// Executa o laço a cada 45ms e incremeta o contador de disparos
			try {
				Thread.sleep(45);
				enemyCount++;
				if (Util.SHOOT_COUNT < 10)
					Util.SHOOT_COUNT++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		phase.setVisible(false);
		this.add(this.over);
		over.requestFocus();
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
		if (keyControl[2] && Util.SHOOT_COUNT > 9) {
			phase.alLaser.add(new Laser(phase.player.getX() + 25, phase.player.getY() + 5, Util.SPEED_HIGH,
					Util.SPEED_HIGH, true));

			Util.SHOOT_COUNT = 0;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == menu.jbPlay) {
			menu.setVisible(false);
			this.add(this.phase);
			phase.requestFocus();
			status = true;
		}

		// Fazer o botão de pause
		if (e.getSource() == phase.jbStop) {

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
