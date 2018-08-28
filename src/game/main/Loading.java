package game.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import game.component.Util;

public class Loading extends JPanel {

	private static final long serialVersionUID = 1L;
	protected ImageIcon logo;
	protected ImageIcon loading;
	protected ImageIcon backGround;
	protected ImageIcon loadingText;
	protected ImageIcon back;


	public Loading() {

		backGround = new ImageIcon("res\\menu\\backGroundLoading.gif");
		loading = new ImageIcon("res\\menu\\loading1.gif");
		logo = new ImageIcon("res\\menu\\quod.png");
		loadingText = new ImageIcon("res\\menu\\loadingText.png");
		back = new ImageIcon("res\\menu\\back.png");

	}

	@Override
	public void paintComponent(Graphics g) {

		// Tela de Fundo
		Image img = backGround.getImage();
    
		//g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		Image imageBackground = Util.background.getImage();
		g.drawImage(imageBackground, 0, 0, getWidth(), Util.DEFAULT_SCREEN_HEIGHT * 10, this);

		// logo
		img = logo.getImage();
		g.drawImage(img, 15, 5, 500, 300, this);
		
		// gif carregando
		img = back.getImage();
		g.drawImage(img, 10, 450, 525, 40, this);
		
		img = loading.getImage();
		g.drawImage(img, 10, 450, 525, 40, this);
		
		//Texto carregando
		img = loadingText.getImage();
		g.drawImage(img, 140, 411, 300, 40, this);

	}
}
