package game.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import game.component.Player;
import game.component.Util;
import game.phase.Phase;
import game.phase.Stage01;

public class JFGameOver extends JFrame implements ActionListener{
	
	protected GameOver over;
	protected Phase phase;
	
	public JFGameOver(){
		
		//musica
		Util.SOUND_PHASE.stop();
		
		setUndecorated(true);
		setSize(Util.DEFAULT_SCREEN_WIDTH, Util.DEFAULT_SCREEN_HEIGHT);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		over = new GameOver();
		add(over);
		
		// leitura
		over.jbFinish.addActionListener(this);
		over.jbTrayAgain.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// Recomeçar
		if (e.getSource() == over.jbTrayAgain) {
			
			this.setVisible(false);
			
		}

		// Finalizar
		if (e.getSource() == over.jbFinish) {
			System.exit(0);
		}
		
	}
}
