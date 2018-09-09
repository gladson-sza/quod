package game.main;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import game.component.Util;

public class JFOver extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	protected Phase phase;
	protected GameOver over;
	protected YouWin win;

	public JFOver(Phase phase, boolean is) {

		this.phase = phase;

		win = new YouWin(phase);
		over = new GameOver(phase);

		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\logo\\DG.png"));
		setTitle("Quod - The Game");
		setSize(Util.DEFAULT_SCREEN_WIDTH - 15, Util.DEFAULT_SCREEN_HEIGHT / 2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		if (is) {
			add(over);
			over.jbRestart.addActionListener(this);
		} else {
			add(win);
			win.jbRestart.addActionListener(this);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == over.jbRestart) {
			this.setVisible(false);
			phase.restartGame();
		}

		if (e.getSource() == win.jbRestart) {
			this.setVisible(false);
			phase.restartGame();
		}

	}

}
