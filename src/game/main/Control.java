package game.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.component.Util;

public class Control extends JPanel{
	
	// variaveis para contruir o botao voltar
	protected JButton jbComeBack;
	protected ImageIcon comeBackText;
	protected ImageIcon imgback;
	protected ImageIcon imgControl;
	protected ImageIcon imgLogoControles;
	
	public Control() {
		
		comeBackText = new ImageIcon("res\\menu\\voltar.png");
		imgControl = new ImageIcon("res\\menu\\tecla.png");
		imgback = new ImageIcon(" ");
		imgLogoControles = new ImageIcon("res\\menu\\logoControles.png");
		
		jbComeBack = new JButton();
		jbComeBack.setBounds(10, 10, 35, 35);
		
		setLayout(null);
		
		jbComeBack.setText(null);
		jbComeBack.setIcon(comeBackText); // texto do botão
		jbComeBack.setPressedIcon(imgback); // Imagem ao clicar
		jbComeBack.setBorderPainted(false);
		jbComeBack.setContentAreaFilled(false);
		
		add(jbComeBack);
	}
	
	protected void paintComponent(Graphics g) {
		
		Image img = Util.background.getImage();
		g.drawImage(img, 0, 0, getWidth(), Util.DEFAULT_SCREEN_HEIGHT * 10, this);
		
		img = imgLogoControles.getImage();
		g.drawImage(img, 180, 10, 200, 45, this);
		
		img = imgControl.getImage();
		g.drawImage(img, 0, 15, getWidth(), getHeight(), this);
		
	}
}
