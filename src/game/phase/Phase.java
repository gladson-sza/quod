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
	public Enemy enemy;
	public Player player;

	public Phase(String backgroundPath, Player player, int lastScore) {

		background = new ImageIcon(backgroundPath);
		this.player = player;
		
		enemy = new Enemy();
		
		alLaser = new ArrayList<Laser>();
		
		score = lastScore;
	}

	@Override
	public void paintComponent(Graphics g) {
		
		Image imageBackground = background.getImage();
		g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), this);
		
		for (Laser l : alLaser) {
			if (!Util.colision(l, enemy))	
				l.draw(g);
		}
			
		
		enemy.draw(g);
		player.draw(g);
		
		g.setColor(Color.WHITE);
		g.drawString("Pontos: " + score, 20, 20);

	}

}
