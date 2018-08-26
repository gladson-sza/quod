package game.phase;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.component.Enemy;
import game.component.Laser;
import game.component.Player;
import game.component.Util;

public class Phase extends JPanel {

	private static final long serialVersionUID = 1L;

	protected static int score;

	protected ImageIcon background;
	protected ImageIcon imgLife;
	
	protected int posLife;
	public ArrayList<Laser> alLaser;
	public ArrayList<Enemy> alEnemy;
	public Image explosion;
	public Player player;
	public int life;
	public boolean side = true;
	public int moveBackground;
	public JButton jbStop;
	
	protected ImageIcon imgText;
	protected ImageIcon imgBack;
  
	public Phase(String backgroundPath, Player player, int lastScore) {

		background = new ImageIcon(backgroundPath);

		life = 3;

		this.player = player;
		alEnemy = new ArrayList<Enemy>();
		alLaser = new ArrayList<Laser>();

		explosion = new ImageIcon("res\\effects\\explosion.gif").getImage();
		imgLife = new ImageIcon("res\\ship\\life.png");
		
		moveBackground = -(Util.DEFAULT_SCREEN_HEIGHT * 9);
		score = lastScore;
		
		// botão pausar
		imgBack = new ImageIcon(" ");
		imgText = new ImageIcon("res\\menu\\stop.jpg");
		
		jbStop = new JButton();
		
		setLayout(null);
		
		jbStop.setBounds(Util.DEFAULT_SCREEN_WIDTH - 50, 5, 35, 35);
		add(jbStop);
		
		jbStop.setText(null);
		jbStop.setIcon(imgText); // texto do botão
		jbStop.setPressedIcon(imgBack); // Imagem ao clicar
		
		// borda
		jbStop.setBorderPainted(false);
		jbStop.setContentAreaFilled(false);
	}

	/*
	 * Essa classe zera todos os atributos
	 */
	public void phaseClear() {
		for (int i = 0; i < alLaser.size(); i++)
			alLaser.remove(i);

		for (int i = 0; i < alEnemy.size(); i++)
			alEnemy.remove(i);
	}

	/*
	 * Essa classe faz as verificações necessárias de colisão e remoção de objetos
	 */
	public void phaseControl(Graphics g) {

		// Movimenta o fundo
		if (moveBackground <= -Util.DEFAULT_SCREEN_HEIGHT)
			moveBackground += 3;
		else
			moveBackground = -(Util.DEFAULT_SCREEN_HEIGHT * 9);

		/* Verificação colisão do Inimigo e do Laser */
		for (int i = 0; i < alLaser.size(); i++) {

			// Verifica se o laser saiu da tela
			if (alLaser.get(i).getY() <= -alLaser.get(i).getHeight())
				alLaser.get(i).setActive(false);

			// Verifica se atingiu algum inimigo ou se saiu da tela
			for (int j = 0; j < alEnemy.size(); j++) {
				if (Util.colision(alLaser.get(i), alEnemy.get(j)) && alEnemy.get(j).isActive()) {
					alLaser.get(i).setActive(false);
					alEnemy.get(j).setActive(false);

					score += 100;
				}

				if (alEnemy.get(j).getY() >= +Util.DEFAULT_SCREEN_HEIGHT) {
					alEnemy.get(j).setActive(false);
				}
					
			}
		}

		// Remove o laser
		for (int i = 0; i < alLaser.size(); i++) {
			if (!alLaser.get(i).isActive()) {
				alLaser.remove(i);
			}
		}

		// Desenha o Laser
		for (int i = 0; i < alLaser.size(); i++) {
			if (alLaser.get(i).isActive() && player.isActive()) {
				alLaser.get(i).draw(g);
			}

		}

		/* Verificação do estado do Player */
		if (life > 0)
			player.draw(g);
		else if (!player.isExplode()) {
			try {
				AudioInputStream as = AudioSystem.getAudioInputStream(new File("res\\sound\\playerExplosion.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(as);
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

			player.setExplode(true);

		} else if (player.getCountExplosion() < Util.EXPLOSION_TIME) {
			g.drawImage(explosion, player.getX(), player.getY(), player.getWidth(), player.getHeight(), this);
			player.countExplosionUp();
			player.setActive(false);
			life = 0;
		} else {
			Util.PLAYING = false;
			phaseClear();
			life = 0;
		}

		/* Verificação de Inimigos na Tela */
		for (int i = 0; i < alEnemy.size(); i++) {
			// Verifica se atingiu o player
			if (Util.colision(player, alEnemy.get(i)) && alEnemy.get(i).isActive()) {
				alEnemy.get(i).setActive(false);
				life--;
			}

			// Verifica se saiu da tela
			if (alEnemy.get(i).getY() >= Util.DEFAULT_SCREEN_HEIGHT) {
				alEnemy.get(i).setActive(false);
				alEnemy.get(i).setExplode(true);
			}

			// Desenha se estiver ativo
			if (alEnemy.get(i).isActive())
				alEnemy.get(i).draw(g);
			else if (!alEnemy.get(i).isExplode()) {
				alEnemy.get(i).setExplode(true);
				alEnemy.get(i).explode();
			} else if (alEnemy.get(i).getCountExplosion() < Util.EXPLOSION_TIME) {
				g.drawImage(explosion, alEnemy.get(i).getX(), alEnemy.get(i).getY(), alEnemy.get(i).getWidth(),
						alEnemy.get(i).getHeight(), this);

				alEnemy.get(i).countExplosionUp();
			} else {
				alEnemy.remove(i);
			}
		}

	}

	@Override
	public void paintComponent(Graphics g) {

		// Cor padrão da fonte
		g.setColor(Color.WHITE);

		// Desenha o background e define as cores da fonte
		Image imageBackground = background.getImage();
		g.drawImage(imageBackground, 0, moveBackground, getWidth(), Util.DEFAULT_SCREEN_HEIGHT * 10, this);

		phaseControl(g);

		// Desenha o status do Laser
		Image laserStatus = new ImageIcon(Util.LASER_CHARGE[Util.SHOOT_COUNT]).getImage();
		g.drawImage(laserStatus, 0, 60, 50, 80, null);

		// Pontuação
		g.drawString("Pontos: " + score, 20, 20);

		// Vida do Player
		Image img = imgLife.getImage();
		
		posLife = 15;
		
		for(int i = 0; i < life; i++) {
			g.drawImage(img, posLife, 34, 20, 20, this);
			posLife += 25;
		}

	}

	public void addKeyListiner() {
		// TODO Auto-generated method stub
		
	}

}
