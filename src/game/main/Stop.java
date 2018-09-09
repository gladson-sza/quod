package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.component.Util;
import game.sound.Sound;

public class Stop extends JPanel {

	private static final long serialVersionUID = 1L;

	protected ImageIcon back;
	protected ImageIcon imgStart;
	protected ImageIcon imgRestart;
	protected ImageIcon imgLife;

	protected ImageIcon imgClose;
	protected ImageIcon logo;
	protected ImageIcon not;
	protected ImageIcon soundTrue;
	protected ImageIcon soundFalse;

	protected JButton jbStart;
	protected JButton jbRestart;
	protected JButton jbClose;
	protected JButton jbSound;

	private Phase phase;
	private int space;
	private int posLife;

	public Stop(Phase phase) {

		this.phase = phase;

		soundFalse = new ImageIcon("res\\button\\somFalseStop.png");
		soundTrue = new ImageIcon("res\\button\\somTrueStop.png");
		not = new ImageIcon(" ");
		logo = new ImageIcon("res\\logo\\QuodGameStop.png");
		back = new ImageIcon("res\\background\\backgroundStop.png");

		imgStart = new ImageIcon("res\\button\\Start.png");
		imgRestart = new ImageIcon("res\\button\\restart.png");
		imgLife = new ImageIcon("res\\ship\\life.png");
		imgClose = new ImageIcon("res\\button\\close.png");

		setLayout(null);

		// despausar
		jbStart = new JButton();
		jbStart.setBounds(Util.DEFAULT_SCREEN_WIDTH / 2 - 36, 220, 80, 80);
		jbStart.setText(null);
		jbStart.setIcon(imgStart); // texto do botão
		jbStart.setPressedIcon(imgStart); // Imagem ao clicar
		jbStart.setBorderPainted(false);
		jbStart.setContentAreaFilled(false);
		
		jbStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (Util.STATUS_EFFECTS)
					new Sound(new File("res\\sound\\buttonBelow.mp3")).start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});

		// som
		jbSound = new JButton();
		jbSound.setBounds(Util.DEFAULT_SCREEN_WIDTH / 2 + 45, 246, 50, 50);
		jbSound.setText(null);
		jbSound.setIcon(not); // texto do botão
		jbSound.setPressedIcon(not); // Imagem ao clicar
		jbSound.setBorderPainted(false);
		jbSound.setContentAreaFilled(false);
		
		jbSound.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (Util.STATUS_EFFECTS)
					new Sound(new File("res\\sound\\buttonBelow.mp3")).start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});

		// recomecar
		jbRestart = new JButton();
		jbRestart.setBounds(Util.DEFAULT_SCREEN_WIDTH / 2 - 90, 245, 50, 50);
		jbRestart.setText(null); // tira o texto
		jbRestart.setIcon(imgRestart); // imagem do botao
		jbRestart.setPressedIcon(imgRestart); // Imagem ao clicar
		jbRestart.setBorderPainted(false); // tira a borda
		jbRestart.setContentAreaFilled(false); // tira o preenchimento
		
		jbRestart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (Util.STATUS_EFFECTS)
					new Sound(new File("res\\sound\\buttonBelow.mp3")).start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});

		// X
		jbClose = new JButton();
		jbClose.setBounds(Util.DEFAULT_SCREEN_WIDTH - 65, 5, 50, 50);
		jbClose.setText(null);
		jbClose.setIcon(imgClose); // texto do bot�o
		jbClose.setPressedIcon(imgClose); // Imagem ao clicar
		jbClose.setBorderPainted(false);
		jbClose.setContentAreaFilled(false);
		
		jbClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (Util.STATUS_EFFECTS)
					new Sound(new File("res\\sound\\buttonBelow.mp3")).start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});

		add(jbStart);
		add(jbRestart);
		add(jbClose);
		add(jbSound);

		// configuracao do ponto
		if (phase.getScore() < 100)
			space = 20;
		else if (phase.getScore() > 99 && phase.getScore() < 1000)
			space = 50;
		else
			space = 70;
	}

	protected void paintComponent(Graphics g) {
		Image img;

		img = back.getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);

		img = logo.getImage();
		g.drawImage(img, 5, 1, 500, 100, null);

		// sound
		if (Util.STATUS_SOUND) {
			img = soundTrue.getImage();
			g.drawImage(img, Util.DEFAULT_SCREEN_WIDTH / 2 + 45, 246, 50, 50, null);
		} else {
			img = soundFalse.getImage();
			g.drawImage(img, Util.DEFAULT_SCREEN_WIDTH / 2 + 45, 246, 50, 50, null);
		}

		// vida
		posLife = 280;

		img = imgLife.getImage();
		for (int i = 0; i < phase.life; i++) {
			g.drawImage(img, posLife, 165, 35, 35, this);
			posLife += 35;
		}

		Graphics2D g2d = (Graphics2D) g;

		g2d.setFont(new Font("Showcard Gothic", Font.PLAIN, 60));
		g2d.setColor(Color.yellow);
		g2d.drawString(" " + phase.getScore(), getWidth() / 2 - space, getHeight() / 2);
	}
}
