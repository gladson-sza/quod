package game.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import game.component.Util;

public class JFGameOver extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected GameOver over;
	protected Phase phase;

	@SuppressWarnings("deprecation")
	public JFGameOver() {

		// musica
		Util.SOUND_PHASE.stop();

		setUndecorated(true);
		setSize(Util.DEFAULT_SCREEN_WIDTH, Util.DEFAULT_SCREEN_HEIGHT);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		over = new GameOver(phase);
		add(over);

		// leitura
		over.jbFinish.addActionListener(this);
		over.jbTryAgain.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Recomeçar
		if (e.getSource() == over.jbTryAgain) {
			this.setVisible(false);
		}

		// Finalizar
		if (e.getSource() == over.jbFinish) {
			System.exit(0);
		}

	}
}
