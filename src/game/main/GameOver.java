package game.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameOver extends JPanel {

	private static final long serialVersionUID = 1L;
	protected ImageIcon imgBack;
	protected ImageIcon jbText;
	protected ImageIcon imgOver;

	public GameOver() {

		imgBack = new ImageIcon("res\\menu\\BackGroundLoading.gif");
		imgOver = new ImageIcon("res\\gameOver\\gameOver.png");

	}

	protected void paintComponent(Graphics g) {
		Image img = imgBack.getImage();

		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		img = imgOver.getImage();
		g.drawImage(img, 25, -50, getWidth() - 50, getHeight() - 50, this);

	}
}
