package game.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.component.Util;

public class MainMenuScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected ImageIcon imgSettings;
	protected ImageIcon imgText;
	protected ImageIcon imgBack;
	protected ImageIcon imageIcon;
	protected ImageIcon imgControl;
	protected ImageIcon logo;
	protected ImageIcon backText;
	protected ImageIcon backMenu;
	
	protected JButton jbPlay;
	protected JButton jbBack; 
	protected JButton jbControl;
	protected JButton jbSettings;

	protected int x = Util.DEFAULT_SCREEN_WIDTH/2 - 80;
	protected int y = Util.DEFAULT_SCREEN_HEIGHT/2 - 30;
	protected int space = 60;
	/*
	 * Construtor
	 */
	protected MainMenuScreen() {

		// imagem de fundo
		imageIcon = new ImageIcon("res\\menu\\backGroundLoading.gif");
		imgBack = new ImageIcon(" ");
		imgText = new ImageIcon("res\\button\\jogar.png");
		logo = new ImageIcon("res\\menu\\QuodMenu.pn");
		backText = new ImageIcon("res\\button\\sair.png");
		imgControl = new ImageIcon("res\\button\\controles.png");
		imgSettings = new ImageIcon("res\\button\\ajustes.png");
		backMenu = new ImageIcon("res\\background\\menuback.jpg");
		
		// Tipo de conf. da tela 
		setLayout(null);
		
		// configuração dos botões
		jbPlay = new JButton();
		jbBack = new JButton();
		jbControl = new JButton();
		jbSettings = new JButton();
		
		jbPlay.setBounds(x, y, 300, 40);
		jbControl.setBounds(x - 45, y += space, 300, 40);
		jbSettings.setBounds(x - 12, y += space, 300, 40);
		jbBack.setBounds(x + 22,y += space, 300, 40);
		
		add(jbPlay);
		add(jbBack);
		add(jbControl);
		add(jbSettings);
		
		// Botao jogar
		jbPlay.setText(null);
		jbPlay.setIcon(imgText); // texto do botão
		jbPlay.setPressedIcon(imgBack); // Imagem ao clicar
		jbPlay.setBorderPainted(false);
		jbPlay.setContentAreaFilled(false);
		
		// Botao sair
		jbBack.setText(null);
		jbBack.setIcon(backText); // texto do botão
		jbBack.setPressedIcon(imgBack); // Imagem ao clicar
		jbBack.setBorderPainted(false);
		jbBack.setContentAreaFilled(false);
		
		// Botao controles
		jbControl.setText(null);
		jbControl.setIcon(imgControl); // texto do botão
		jbControl.setPressedIcon(imgBack); // Imagem ao clicar
		jbControl.setBorderPainted(false);
		jbControl.setContentAreaFilled(false);
		
		// Botao conf
		jbSettings.setText(null);
		jbSettings.setIcon(imgSettings); // texto do botão
		jbSettings.setPressedIcon(imgBack); // Imagem ao clicar
		jbSettings.setBorderPainted(false);
		jbSettings.setContentAreaFilled(false);

	}

	@Override
	public void paintComponent(Graphics g) {
		
		Image img = imageIcon.getImage();
		//g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		
		Image imageBackground = Util.backgroundMenu.getImage();
		g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), this);
		
		// logo
		img = Util.quodGame.getImage();
		g.drawImage(img, 25, 40, 500, 300, this);

	}
}