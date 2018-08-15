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
	
	public ArrayList<Integer> laserCount;
	public ArrayList<Laser> alLaser;
	public Image explosion;
	public Enemy enemy;
	public Player player;
	public int life;
	public boolean side = true;

	public Phase(String backgroundPath, Player player, int lastScore) {

		background = new ImageIcon(backgroundPath);
		
		life = 1;
		
		this.player = player;
		enemy = new Enemy();
		alLaser = new ArrayList<Laser>();
		
		explosion = new ImageIcon("res\\effects\\explosion.gif").getImage();
		
		score = lastScore;
	}

	@Override
	public void paintComponent(Graphics g) {
		
		Image imageBackground = background.getImage();
		g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), this);
		g.setColor(Color.WHITE);
		
		for (Laser l : alLaser) {
			// Pinta o laser enquanto não colidir
			if (!Util.colision(l, enemy)) {
				l.draw(g);
			}
			
			// Cria o efeito de explosão quando o laser atingir o inimigo
			if (Util.colision(l, enemy))
				enemy.setActive(false);

		}
		
		// Cria o efeito de explosão se o inimigo atingir o player
		if (Util.colision(player, enemy)) {
			g.drawImage(explosion, enemy.getX(), enemy.getY(),
				enemy.getWidth(), enemy.getHeight(), this);
			
			enemy.setActive(false);
			
			life--;
			
			if (life <= 0)
				player.setActive(false);
			
		}
		
		// Desenha a nave inimiga enquanto ela estiver ativa
		if (enemy.isActive() == true)
			enemy.draw(g);
		else {
			g.drawImage(explosion, enemy.getX(), enemy.getY(),
					enemy.getWidth(), enemy.getHeight(), this);
		}
		
		// Desenha a nave do player enquanto ela estiver ativa
		if (player.isActive())
			player.draw(g);
		else {
			g.drawImage(explosion, player.getX(), player.getY(),
					player.getWidth(), player.getHeight(), this);
		}
		
		
		// Pontuação
		g.drawString("Pontos: " + score, 20, 20);
		
		// Vida do Player
		g.drawString("Vida: " + life, 20, 40);

	}

}
