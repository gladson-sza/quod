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

	public Phase(String backgroundPath, Player player, int lastScore) {

		background = new ImageIcon(backgroundPath);

		life = 1;

		this.player = player;
		alEnemy = new ArrayList<Enemy>();
		alLaser = new ArrayList<Laser>();

		explosion = new ImageIcon("res\\effects\\explosion.gif").getImage();

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
		/* Verificação do Laser */
		for (int i = 0; i < alLaser.size(); i++) {

			// Verifica se saiu da tela
			if (alLaser.get(i).getY() <= -alLaser.get(i).getHeight())
				alLaser.get(i).setActive(false);

			// Verifica se atingiu algum inimigo
			for (int j = 0; j < alEnemy.size(); j++) {
				if (Util.colision(alLaser.get(i), alEnemy.get(j))) {
					alLaser.get(i).setActive(false);
					alEnemy.get(j).setActive(false);
				}
			}

			// Desenha se estiver ativo
			if (alLaser.get(i).isActive())
				alLaser.get(i).draw(g);

		}

		// Remove o laser
		for (int i = 0; i < alLaser.size(); i++) {
			if (!alLaser.get(i).isActive())
				alLaser.remove(i);
		}

		/* Verificação de Inimigos na Tela */
		for (int i = 0; i < alEnemy.size(); i++) {

			// Verifica se colidiu com o player
			if (Util.colision(player, alEnemy.get(i))) {
				alEnemy.get(i).setActive(false);
				life--;
			}

			// Desenha se estiver ativo
			if (alEnemy.get(i).isActive())
				alEnemy.get(i).draw(g);
			else if (alEnemy.get(i).getCountExplosion() < Util.EXPLOSION_TIME) {
				g.drawImage(explosion, alEnemy.get(i).getX(), alEnemy.get(i).getY(), alEnemy.get(i).getWidth(),
						alEnemy.get(i).getHeight(), this);
				alEnemy.get(i).countExplosionUp();
			} else {
				alEnemy.remove(i);
			}
		}

		/* Verificação do estado do Player */
		if (life > 0)
			player.draw(g);
		else if (player.getCountExplosion() < Util.EXPLOSION_TIME) {
			g.drawImage(explosion, player.getX(), player.getY(), player.getWidth(), player.getHeight(), this);
			player.countExplosionUp();
			life = 0;
		} else {
			Util.PLAYING = false;
			phaseClear();
			life = 0;
		}

	}

	@Override
	public void paintComponent(Graphics g) {

		// Desenha o background e define as cores da fonte
		Image imageBackground = background.getImage();
		g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), this);
		g.setColor(Color.WHITE);

		phaseControl(g);

		// Pontuação
		g.drawString("Pontos: " + score, 20, 20);

		// Vida do Player
		g.drawString("Vida: " + life, 20, 40);

	}

}
