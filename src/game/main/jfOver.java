package game.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import game.component.Util;

public class jfOver extends JFrame implements ActionListener{
	
	protected Phase phase;
	protected GameOver over;
	
	public jfOver(Phase phase) {
		
		this.phase = phase;
		over = new GameOver(phase);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\logo\\DG.png"));
		setTitle("Quod - The Game");
		setSize(Util.DEFAULT_SCREEN_WIDTH - 15, Util.DEFAULT_SCREEN_HEIGHT / 2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		add(over);
		
		over.jbRestart.addActionListener(this);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == over.jbRestart) {
			this.setVisible(false);
			phase.restartGame();
		}
		
	}
	
	
}
