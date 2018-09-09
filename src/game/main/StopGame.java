package game.main;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;

import game.component.Util;
import game.sound.Sound;

public class StopGame extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	protected Stop stop;
	protected Phase phase;
	protected QuodGame qg;
	protected boolean comeback;

	public StopGame(Phase phase) {

		this.phase = phase;

		stop = new Stop(phase);
		comeback = false;

		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\logo\\DG.png"));
		setTitle("Quod - The Game");
		setSize(Util.DEFAULT_SCREEN_WIDTH - 15, Util.DEFAULT_SCREEN_HEIGHT / 2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		add(stop);

		stop.jbStart.addActionListener(this);
		stop.jbClose.addActionListener(this);
		stop.jbRestart.addActionListener(this);
		stop.jbSound.addActionListener(this);
		this.addKeyListener(this);
		this.requestFocus();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stop.jbStart) {

			phase.timerEnemy.restart();
			phase.addKeyListener(this);
			phase.requestFocus();

			Util.STOP = false;
			this.setVisible(false);
			stop.setVisible(false);

			comeback = true;
		}

		if (e.getSource() == stop.jbRestart) {

			Util.STOP = false;
			Phase.setScore(-1);

			this.setVisible(false);
			Util.STOP = false;
			phase.restartGame();
		}

		if (e.getSource() == stop.jbClose) {

			phase.addKeyListener(this);
			phase.requestFocus();

			Util.STOP = false;
			this.setVisible(false);
		}

		if (e.getSource() == stop.jbSound) {
			if (Util.STATUS_SOUND) {
				Util.STATUS_SOUND = false;
				Util.SOUND_PHASE.stop();
			} else {
				Util.STATUS_SOUND = true;
				Util.SOUND_PHASE = new Sound(new File("res\\sound\\phaseTheme.mp3"));
				Util.SOUND_PHASE.start();
			}

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			phase.timerEnemy.restart();
			phase.addKeyListener(this);
			phase.requestFocus();

			Util.STOP = false;
			this.setVisible(false);
			stop.setVisible(false);

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
