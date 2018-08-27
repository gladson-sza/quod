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
	protected ImageIcon backText;
	
	protected JButton jbPlay;
	protected JButton jbBack; 


	/*
	 * Construtor
	 */
	protected MainMenuScreen() {

		// imagem de fundo
		imageIcon = new ImageIcon("res\\menu\\backGroundLoading.gif");
		imgBack = new ImageIcon(" ");
		imgText = new ImageIcon("res\\menu\\Play.png");
		logo = new ImageIcon("res\\menu\\QuodMenu.png");
		backText = new ImageIcon("res\\menu\\sair.png");

		
		// Tipo de conf. da tela 
		setLayout(null);
		
		// configuração dos botões
		jbPlay = new JButton();
		jbBack = new JButton();
		
		jbBack.setBounds(Util.DEFAULT_SCREEN_WIDTH/2 - 100, Util.DEFAULT_SCREEN_HEIGHT/2 + 100, 200, 60);
		jbPlay.setBounds(Util.DEFAULT_SCREEN_WIDTH/2 - 100, Util.DEFAULT_SCREEN_HEIGHT/2 + 10, 200, 60);
		
		add(jbPlay);
		add(jbBack);

		
		// Texto
		jbPlay.setText(null);
		jbPlay.setIcon(imgText); // texto do botão
		jbPlay.setPressedIcon(imgBack); // Imagem ao clicar
		
		jbBack.setText(null);
		jbBack.setIcon(backText); // texto do botão
		jbBack.setPressedIcon(imgBack); // Imagem ao clicar

		// borda
		jbPlay.setBorderPainted(false);
		jbPlay.setContentAreaFilled(false);

		jbBack.setBorderPainted(false);
		jbBack.setContentAreaFilled(false);
		

	}

	@Override
	public void paintComponent(Graphics g) {
		
		Image img = imageIcon.getImage();
		//g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		
		Image imageBackground = Util.background.getImage();
		g.drawImage(imageBackground, 0, 0, getWidth(), Util.DEFAULT_SCREEN_HEIGHT * 10, this);
		
		// logo
		img = logo.getImage();
		g.drawImage(img, 20, 1, 500, 300, this);

	}
}