package game.phase;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.component.Player;

public class Phase extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected static int score;
	
	protected ImageIcon background;
	public Player player;
	
	public Phase(String backgroundPath, Player player, int lastScore) {

		background = new ImageIcon(backgroundPath);
		this.player = player;
		
		score = lastScore;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		
		Image imageBackground = background.getImage();
		g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), this);
		
		player.draw(g);
		player.gun.draw(g);
		g.drawString("Pontos: " + score, 20, 20);
		
	}
	
}
