package game.phase;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import game.component.GameObject;

public class Phase extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected static int score;
	protected GameObject player;
	
	public Phase() {
		player = new GameObject(20, 40, 30, 30, 20, 20, true, 1, 1, 1);
		score = 0;

	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		player.draw(g);
		g.drawString("Pontos: " + score, 20, 20);
		
	}
	
}
