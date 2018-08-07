package game.phase;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.component.Player;
import game.component.Util;

public class Phase extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected static int score;
	
	protected ImageIcon background;
	protected Player player;
	
	public Phase() {
		background = new ImageIcon("res\\\\background\\\\galaxy_background01.jpg");
		
		player = new Player(20, 40, 30, 30, Util.SPEED_MEDIUM, Util.SPEED_MEDIUM, true, 20, 20, 20);
		score = 0;

	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		Image imageBackground = background.getImage();
		
		g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), this);
		player.draw(g);
		g.drawString("Pontos: " + score, 20, 20);
		
	}
	
}
