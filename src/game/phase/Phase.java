package game.phase;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.component.Enemy;
import game.component.Laser;
import game.component.Player;
import game.component.Util;

public class Phase extends JPanel {

	private static final long serialVersionUID = 1L;

	protected static int score;

	protected ImageIcon background;

	public ArrayList<Laser> alLaser;
	public ArrayList<Enemy> alEnemy;
	public Image explosion;
	public Player player;
	public int life;
	public boolean side = true;
	public int moveBackground;

	public Phase(String backgroundPath, Player player, int lastScore) {

		background = new ImageIcon(backgroundPath);

		life = 3;

		this.player = player;
		alEnemy = new ArrayList<Enemy>();
		alLaser = new ArrayList<Laser>();

		explosion = new ImageIcon("res\\effects\\explosion.gif").getImage();

		moveBackground = -(Util.DEFAULT_SCREEN_HEIGHT * 5);
		score = lastScore;
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
			moveBackground = -(Util.DEFAULT_SCREEN_HEIGHT * 5);

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
					
					Util.SPEED_SLOW = 3;
					Util.SPEED_MEDIUM = 5;
					Util.SPEED_HIGH = 10;
					
					score += 100;
				}

				if (alEnemy.get(j).getY() >= +Util.DEFAULT_SCREEN_HEIGHT)
					alEnemy.get(j).setActive(false);
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
		else if (player.getCountExplosion() < Util.EXPLOSION_TIME) {
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

			// Desenha se estiver ativo
			if (alEnemy.get(i).isActive()) {
				alEnemy.get(i).draw(g);
			} else if (alEnemy.get(i).getCountExplosion() < Util.EXPLOSION_TIME) {
				g.drawImage(explosion, alEnemy.get(i).getX(), alEnemy.get(i).getY(), alEnemy.get(i).getWidth(),
						alEnemy.get(i).getHeight(), this);

				alEnemy.get(i).moveDown();
				alEnemy.get(i).countExplosionUp();
			} else {
				alEnemy.remove(i);
			}
		}

	}

	@Override
	public void paintComponent(Graphics g) {

		// Desenha o background e define as cores da fonte
		Image imageBackground = background.getImage();
		g.drawImage(imageBackground, 0, moveBackground, getWidth(), Util.DEFAULT_SCREEN_HEIGHT * 6, this);
		g.setColor(Color.WHITE);

		phaseControl(g);

		// Pontuação
		g.drawString("Pontos: " + score, 20, 20);

		// Vida do Player
		g.drawString("Vida: " + life, 20, 40);

	}

}
