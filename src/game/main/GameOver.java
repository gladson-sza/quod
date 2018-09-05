package game.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.component.Util;

public class GameOver extends JPanel {

	private static final long serialVersionUID = 1L;
	protected ImageIcon imgBack;
	protected ImageIcon jbText;
	protected ImageIcon imgOver;
	protected ImageIcon imgFinish;

	protected JButton jbTrayAgain;
	protected JButton jbFinish;

	protected Phase phase;

	public GameOver() {

		imgBack = new ImageIcon(" ");
		imgOver = new ImageIcon("res\\gameOver\\gameOver.png");
		jbText = new ImageIcon("res\\gameOver\\playAgain.png");
		imgFinish = new ImageIcon("res\\gameOver\\finalizar.png");

		jbTrayAgain = new JButton();
		jbFinish = new JButton();

		setLayout(null);

		jbFinish.setBounds(Util.DEFAULT_SCREEN_WIDTH / 2 - 95, Util.DEFAULT_SCREEN_HEIGHT / 2, 200, 60);
		jbTrayAgain.setBounds(Util.DEFAULT_SCREEN_WIDTH / 2 - 95, Util.DEFAULT_SCREEN_HEIGHT / 2 + 100, 200, 60);

		// add(jbTrayAgain);
		add(jbFinish);

		// Bot�o tentar novamente

		jbTrayAgain.setIcon(jbText); // texto do botão
		jbTrayAgain.setPressedIcon(imgBack); // Imagem ao clicar

		// borda
		jbTrayAgain.setBorderPainted(false);
		jbTrayAgain.setContentAreaFilled(false);

		jbTrayAgain.setBorderPainted(false);
		jbTrayAgain.setContentAreaFilled(false);

		// bot�o finalizar

		jbFinish.setIcon(imgFinish); // texto do botão
		jbFinish.setPressedIcon(imgBack); // Imagem ao clicar

		// borda
		jbFinish.setBorderPainted(false);
		jbFinish.setContentAreaFilled(false);

		jbFinish.setBorderPainted(false);
		jbFinish.setContentAreaFilled(false);

	}

	protected void paintComponent(Graphics g) {
		Image img;

		Image imageBackground = Util.background.getImage();
		g.drawImage(imageBackground, 0, 0, getWidth(), Util.DEFAULT_SCREEN_HEIGHT * 10, this);

		img = imgOver.getImage();
		g.drawImage(img, 25, 5, 500, 300, this);

	}
}
