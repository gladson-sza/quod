/**
 * @author Gladson Souza de Araujo
 * @author Paulo Victor Ribeiro da Silva
 * 
 */

package game.main;

import game.component.Util;
import game.sound.Sound;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;

public class QuodGame extends JFrame implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	public boolean status = false;
	public boolean[] keyControl;

	public static QuodGame qg;

	public Settings settings;
	public Control control;
	public Phase phase;
	public MainMenuScreen menu;
	public Loading loading;
	public GameOver over;
	public Phase phaseAgain;
	public Stop stop;
	public StopGame sg;

	/*
	 * Construtor
	 */
	public QuodGame() {

		settings = new Settings();
		loading = new Loading();
		phase = new Phase("res\\background\\galaxy_background01.jpg");
		menu = new MainMenuScreen();
		control = new Control();

		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\logo\\DG.png"));
		setTitle("Quod - The Game");
		setSize(Util.DEFAULT_SCREEN_WIDTH, Util.DEFAULT_SCREEN_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		// adiciona a tela de carregando
		add(loading);

		loading.jbPress.addActionListener(this);

		mainMenu(false);

	}

	/*
	 * Método de chamada do menu, cria todos os botoes.
	 */
	public void mainMenu(boolean aNew) {

		/*
		 * Para voltar para o menu, deve-se instanciar todos os botoes (true)
		 */
		if (aNew == true) {
			settings = new Settings();
			loading = new Loading();
			phase = new Phase("res\\background\\galaxy_background01.jpg");
			menu = new MainMenuScreen();
			control = new Control();
		}

		phase.addKeyListener(this);
		phase.setFocusable(true);

		// Leitura dos botoes do menu
		menu.jbPlay.addActionListener(this);
		menu.jbBack.addActionListener(this);
		menu.jbControl.addActionListener(this);
		menu.jbControl.addActionListener(this);
		menu.jbSettings.addActionListener(this);
		control.jbComeBack.addActionListener(this);
		settings.jbComeBack.addActionListener(this);
		settings.jbVolume.addActionListener(this);
		settings.jbEffects.addActionListener(this);
		phase.jbStop.addActionListener(this);

		keyControl = new boolean[4];

		if (aNew == true) {
			this.add(this.menu);
			menu.requestFocus();
		}
	}

	/*
	 * Metodo Principal
	 */
	public static void main(String[] args) {
		qg = new QuodGame();
		qg.gameStart();
	}

	/*
	 * Verifica se o player ainda esta vivo
	 */
	@SuppressWarnings("deprecation")
	public void isAlive() {

		if (phase.life == 0) {

			Util.SOUND_PHASE.stop();
			Util.STOP = true;

			phase.timerEnemy.stop();
			phase.phaseClear();

			new JFOver(phase, true);
		}

	}

	/*
	 * Verifica se o boss esta vivo
	 */

	@SuppressWarnings("deprecation")
	public void bossIsAlive() {
		if (phase.bossDie) {
			Util.SOUND_PHASE.stop();
			Util.STOP = true;

			phase.timerEnemy.stop();
			phase.phaseClear();

			new JFOver(phase, false); // false para VOCE VENCEU
		}
	}

	/*
	 * GameLopp
	 */
	private void gameStart() {

		while (Util.PLAYING) {

			if (!Util.STOP) {

				gameControl();
				repaint();
				// verifica se o player ainda esta vivo
				isAlive();

				// verifica se o boss ainda esta vivo
				bossIsAlive();

				// contador disparos

				if (Util.SHOOT_COUNT < 10)
					Util.SHOOT_COUNT++;

			}

			// Tempo de atualizacao da tela
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// abrindo tela de fim de jogo

	}

	/* Teclado */

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

	/*
	 * Muda o estado da tecla pressionada
	 */
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
		case KeyEvent.VK_ESCAPE:
			keyControl[3] = status;
			break;
		}
	}

	/*
	 * Faz a verificacao do teclado
	 */
	private void gameControl() {
		// Esqurda
		if (keyControl[0]) {
			phase.player.moveLeft();
		}

		// Direita
		if (keyControl[1]) {
			phase.player.moveRight();
		}

		// Disparos
		if (keyControl[2] && Util.SHOOT_COUNT > 9) {
			phase.player.setShoot(true);

			if (Util.STATUS_EFFECTS) {
				new Sound(new File("res\\sound\\shoot.mp3")).start();
			}

			Util.SHOOT_COUNT = 0;
		}

		if (keyControl[3]) {
			stop();
		}

	}

	/*
	 * pausar
	 */
	public void stop() {

		if (Util.STOP) {
			Util.STOP = false;

			phase.timerEnemy.restart();
			phase.addKeyListener(this);
			phase.requestFocus();

		} else {

			Util.STOP = true;

			phase.timerEnemy.stop();

			this.sg = new StopGame(phase);

			keyControl[3] = false;

		}
	}

	/* Botoes */

	@Override
	public void actionPerformed(ActionEvent e) {

		// iniciar
		if (e.getSource() == loading.jbPress) {
			loading.setVisible(false);
			this.add(this.menu);
			menu.requestFocus();
		}

		// Jogar botao
		if (e.getSource() == menu.jbPlay) {

			menu.setVisible(false);
			phase.restartGame();
			add(phase);
			phase.requestFocus();

		}

		// Sair botao
		if (e.getSource() == menu.jbBack) {
			System.exit(0);
		}

		// Controle botao

		if (e.getSource() == menu.jbControl) {

			menu.setVisible(false);
			this.add(this.control);
			control.requestFocus();
		}

		// Controles / botao voltar
		if (e.getSource() == control.jbComeBack) {

			control.setVisible(false);
			mainMenu(true);

		}

		// fase / botao pausar
		if (e.getSource() == phase.jbStop) {
			stop();
		}

		// botao ajustes
		if (e.getSource() == menu.jbSettings) {
			menu.setVisible(false);
			this.add(this.settings);
			settings.requestFocus();

		}

		// Ajustes / botao de voltar
		if (e.getSource() == settings.jbComeBack) {
			settings.setVisible(false);
			this.add(this.menu);
			menu.requestFocus();

			mainMenu(true);
		}

		// Ajustes / botao de musica
		if (e.getSource() == settings.jbVolume) {
			if (Util.STATUS_SOUND)
				Util.STATUS_SOUND = false; // mudo
			else
				Util.STATUS_SOUND = true; // som
		}

		// Ajustes / Botao efeitos especiais
		if (e.getSource() == settings.jbEffects) {
			if (Util.STATUS_EFFECTS)
				Util.STATUS_EFFECTS = false; // mudo

			else
				Util.STATUS_EFFECTS = true; // som
		}

	}

}
