package game.main;

import java.awt.Graphics;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.component.Util;

public class MainMenuScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected ImageIcon imgText;
	protected ImageIcon imgBack;
	protected ImageIcon imageIcon;
	protected ImageIcon logo;
	
	protected JButton jbPlay;

	/*
	 * Construtor
	 */
	protected MainMenuScreen() {

		// imagem de fundo
		imageIcon = new ImageIcon("res\\menu\\backGroundLoading.gif");
		imgBack = new ImageIcon(" ");
		imgText = new ImageIcon("res\\menu\\Play.png");
		logo = new ImageIcon("res\\menu\\Quod.png");
		
		// Tipo de conf. da tela 
		setLayout(null);
		
		// configuração dos botões
		jbPlay = new JButton();
		jbPlay.setBounds(Util.DEFAULT_SCREEN_WIDTH/2 - 100, Util.DEFAULT_SCREEN_HEIGHT/2 - 50, 200, 60);
		add(jbPlay);
		
		// Texto
		jbPlay.setText(null);
		jbPlay.setIcon(imgText); // texto do botão
		jbPlay.setPressedIcon(imgBack); // Imagem ao clicar
		
		// borda
		jbPlay.setBorderPainted(false);
		jbPlay.setContentAreaFilled(false);
		
		
	}

	@Override
	public void paintComponent(Graphics g) {
		Image img = imageIcon.getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		
		img = logo.getImage();
		g.drawImage(img, 130, 5, 300, 250, this);
	}
}