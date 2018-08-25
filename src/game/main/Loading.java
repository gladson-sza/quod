package game.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Loading extends JPanel {

	private static final long serialVersionUID = 1L;
	protected ImageIcon logo;
	protected ImageIcon loading;
	protected ImageIcon backGround;

	public Loading() {

		backGround = new ImageIcon("res\\menu\\backGroundLoading.gif");
		loading = new ImageIcon("res\\menu\\loading.gif");
		logo = new ImageIcon("res\\menu\\Quod.png");

	}

	@Override
	public void paintComponent(Graphics g) {

		// Tela de Fundo
		Image img = backGround.getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		// logo
		img = logo.getImage();
		g.drawImage(img, 130, 5, 300, 250, this);

		// gif carregando
		img = loading.getImage();
		g.drawImage(img, 70, 400, 420, 100, this);
	}
}
