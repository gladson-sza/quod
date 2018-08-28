/**
 * @author Gladson Souza de AraÃºjo
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
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class QuodGame extends JFrame implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	public boolean status = false;
	
	public Settings settings;
	public Control control;
	public Phase phase;
	public MainMenuScreen menu;
	public Loading loading;
	public GameOver over;
	public static QuodGame qg;
	public Phase phaseAgain;

	public boolean[] keyControl;
	private int enemyCount = 45;

	/*
	 * Construtor
	 */
	public QuodGame() {
		
		settings = new Settings();
		loading = new Loading();
		over = new GameOver();
		phase = new Stage01("res\\background\\galaxy_background01.jpg", new Player(), 0);
		menu = new MainMenuScreen();
		control = new Control();
		
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
		
		mainMenu(false);
	}
	
	/*
	 *	Método de chamada do menu, cria todos os botoes.
	 */
	public void mainMenu(boolean aNew) {
		
		/*
		 * Para voltar para o menu, deve-se instanciar todos os botoes (ture)
		 */
		if(aNew == true) {
			settings = new Settings();
			loading = new Loading();
			over = new GameOver();
			phase = new Stage01("res\\background\\galaxy_background01.jpg", new Player(), 0);
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
		
		keyControl = new boolean[4];
		
		this.add(this.menu);
		menu.requestFocus();
	}

	/*
	 * MÃ©todo Principal
	 */
	public static void main(String[] args) {
		qg = new QuodGame();
		qg.gameStart();
	}

	/*
	 * GameLopp
	 */
	private void gameStart() {

		while (Util.PLAYING) {

			if (!Util.STOP) {
				if (status == true) {
					if (enemyCount > new Random().nextInt(15) + 20) {
						phase.alEnemy
								.add(new Enemy(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH - Util.ENEMY_WIDTH)));
						enemyCount = 0;
					}
				}

				gameControl();
				repaint();

				// Leitura do botÃ£o pause
				phase.jbStop.addActionListener(this);

				// contador de inimigos e disparos
				enemyCount++;
				if (Util.SHOOT_COUNT < 10)
					Util.SHOOT_COUNT++;

			}

			// Tempo de atualização da tela
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// abrindo tela de fim de jogo
		phase.setVisible(false);
		this.add(this.over);
		over.requestFocus();

		// botï¿½o de finalizar
		over.jbFinish.addActionListener(this);
		over.jbTrayAgain.addActionListener(this);
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
		case KeyEvent.VK_ESCAPE:
			keyControl[3] = status;
			break;
		}
	}

	/*
	 * Faz a verificaÃ§Ã£o do teclado
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

		// botï¿½o menu jogar
		if (e.getSource() == menu.jbPlay) {
			
			try {
				AudioInputStream as = AudioSystem.getAudioInputStream(new File("res\\sound\\phaseTheme.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(as);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			// tira o menu
			menu.setVisible(false);
			// começa o jogo
			this.add(this.phase);
			phase.requestFocus();
			status = true;
		}

		// menu botaoo sair
		if (e.getSource() == menu.jbBack) {
			System.exit(0);
		}
		
		// menu botão controle
		if(e.getSource() == menu.jbControl) {
			
			menu.setVisible(false);
			this.add(this.control);
			control.requestFocus();	
		}
		
		// controles botao voltar
		if(e.getSource() == control.jbComeBack) {
	
			control.setVisible(false);
			mainMenu(true);		
			
		}

		// Fase botao pausar
		if (e.getSource() == phase.jbStop) {
			if (Util.STOP == true) {
				Util.STOP = false;
				phase.addKeyListener(this);
				phase.requestFocus();
			} else {
				Util.STOP = true;
			}
		}
		
		// botao ajustes
		if(e.getSource() == menu.jbSettings) {
			menu.setVisible(false);
			this.add(this.settings);
			settings.requestFocus();		
			
		}
		
		// botao ajustes voltar
		if(e.getSource() == settings.jbComeBack) {
			settings.setVisible(false);
			this.add(this.menu);
			menu.requestFocus();
			
			mainMenu(true);
		}
		// botao volume em ajustes
		if(e.getSource() == settings.jbVolume) {
			if(settings.status == true) 
		
				settings.status = false;// para mudo
			else
				settings.status = true;// para som
		}
		// Recomeçar
		if (e.getSource() == over.jbTrayAgain) {

		}

		// Finalizar
		if (e.getSource() == over.jbFinish) {
			System.exit(0);
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
