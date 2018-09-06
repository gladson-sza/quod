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
import game.phase.Phase;

public class Stop extends JPanel{
	
	protected ImageIcon back;
	protected ImageIcon imgStart;
	protected ImageIcon imgRestart;
	protected ImageIcon imgLife;
	
	protected JButton jbStart;
	protected JButton jbRestart;
	
	protected Phase phase;
	
	private int space;
	
	
	public Stop() {
		back = new ImageIcon("res\\background\\backStop.png");
		imgStart = new ImageIcon("res\\button\\Start.png");
		imgRestart = new ImageIcon("res\\button\\restart.png");
		imgLife = new ImageIcon("res\\ship\\life.png");
		
		setLayout(null);
		
		// despausar
		jbStart = new JButton();
		jbStart.setBounds(Util.DEFAULT_SCREEN_WIDTH /2 - 36, 240, 80, 80);
		jbStart.setText(null);
		jbStart.setIcon(imgStart); // texto do botão
		jbStart.setPressedIcon(imgStart); // Imagem ao clicar
		jbStart.setBorderPainted(false);
		jbStart.setContentAreaFilled(false);
		
		// recomecar
		jbRestart = new JButton();
		jbRestart.setBounds(Util.DEFAULT_SCREEN_WIDTH /2 - 90, 265, 50, 50);
		jbRestart.setText(null);
		jbRestart.setIcon(imgRestart); // texto do botão
		jbRestart.setPressedIcon(imgRestart); // Imagem ao clicar
		jbRestart.setBorderPainted(false);
		jbRestart.setContentAreaFilled(false);
		
		add(jbStart);
		add(jbRestart);
		
		// configuracao do ponto
		if(phase.score < 100)
			space = 20;
		else
			if(phase.score > 99 && phase.score < 1000)
				space = 50;
			else
				space = 70;
	}
	
	protected void paintComponent(Graphics g) {
		Image img;
		
		img = back.getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		
		
		
		Graphics2D g2d = (Graphics2D) g;
        //g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Showcard Gothic", Font.PLAIN, 60));
        g2d.setColor(Color.yellow);
        g2d.drawString(" " + phase.score, getWidth() /2 - space, getHeight()/2);
	}
}
