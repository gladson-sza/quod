package game.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import game.component.Util;

public class StopGame extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	protected Stop stop;
	protected QuodGame qg;

	public StopGame(Phase phase) {

		stop = new Stop(phase);

		setUndecorated(true);
		setSize(Util.DEFAULT_SCREEN_WIDTH - 15, Util.DEFAULT_SCREEN_HEIGHT / 2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		add(stop);

		stop.jbStart.addActionListener(this);
		stop.jbClose.addActionListener(this);
		stop.jbRestart.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stop.jbStart) {
			Util.STOP = false;
			this.setVisible(false);
		}

		if (e.getSource() == stop.jbRestart) {
			Util.STOP = false;
			Phase.setScore(-1);
			this.setVisible(false);
		}

		if (e.getSource() == stop.jbClose) {
			Util.STOP = false;
			this.setVisible(false);
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
