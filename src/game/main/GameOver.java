package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.component.Util;

public class GameOver extends JPanel{
	
	protected Phase phase;
	protected ImageIcon background;
	protected ImageIcon gameOver;
	protected ImageIcon imgRestart;
	
	protected JButton jbRestart;
	
	private int space;
	
	public GameOver(Phase phase) {
		
		this.phase = phase;
		imgRestart = new ImageIcon("res\\button\\restart80.png");
		background = new ImageIcon("res\\background\\backgroundOver.png");
		gameOver = new ImageIcon("res\\logo\\gameOver.png");
		
		setLayout(null);
		
		jbRestart = new JButton();
		jbRestart.setBounds(Util.DEFAULT_SCREEN_WIDTH / 2 - 36, 215, 80, 80);
		jbRestart.setText(null);
		jbRestart.setIcon(imgRestart); // texto do botão
		jbRestart.setPressedIcon(imgRestart); // Imagem ao clicar
		jbRestart.setBorderPainted(false);
		jbRestart.setContentAreaFilled(false);
		add(jbRestart);
		
		if (phase.getScore() < 100)
			space = 20;
		else if (phase.getScore() > 99 && phase.getScore() < 1000)
			space = 50;
		else
			space = 70;
		
	
	}
	
	protected void paintComponent(Graphics g) {
		Image img;
		
		img = background.getImage();	
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		
		img = gameOver.getImage();
		g.drawImage(img, 20, 0, 500, 100, null);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Showcard Gothic", Font.PLAIN, 60));
		g2d.setColor(Color.WHITE);
		g2d.drawString(" " + phase.getScore(), getWidth() / 2 - space, getHeight() / 2 + 20);
	}
	
}
