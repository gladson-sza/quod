package game.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.component.Util;

public class Settings extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	// variaveis para contruir o botao voltar
	protected JButton jbComeBack;
	protected ImageIcon comeBackText;
	protected ImageIcon imgback;
	
	// variaveis para contruir o botao voltar
	protected JButton jbVolume;
	protected ImageIcon mold;
	
	protected ImageIcon imgVolumeTrue;
	protected ImageIcon imgVolumeFalse;
	
	// auxiliares
	private static int widthHeight = 24; // nao pode mudar esses valores (tamanho do volume)
	private static int spaceX = 310, spaceY = 9; //nao pode mudar esses valores (posicao da volume)
	private int x = 100, y = 125; // posicao do botao volume, pode alterar
	
	//logo
	protected ImageIcon imgSettings;
	protected boolean status = true;
	
	public Settings() {
		
		// conf
		imgSettings = new ImageIcon("res\\menu\\ajustesLogo.png");
		
		// Som e mudo
		imgVolumeTrue = new ImageIcon("res\\menu\\som.png");
		imgVolumeFalse = new ImageIcon("res\\menu\\mudo.png");
		
		comeBackText = new ImageIcon("res\\menu\\voltar.png");
		imgback = new ImageIcon(" ");
	
		/*
		 * Configuracao do botao voltar
		 */
		jbComeBack = new JButton();
		jbComeBack.setBounds(10, 10, 35, 35);
		
		setLayout(null);
		
		jbComeBack.setText(null);
		jbComeBack.setIcon(comeBackText); // texto do botão
		jbComeBack.setPressedIcon(imgback); // Imagem ao clicar
		jbComeBack.setBorderPainted(false);
		jbComeBack.setContentAreaFilled(false);
		
		add(jbComeBack);
		
		/*
		 *  Configuracao do botao de volume
		 */
		
		mold = new ImageIcon("res\\menu\\moldeVolume.png");
		
		jbVolume = new JButton();
		jbVolume.setBounds(x, y, 350, 45);
		
		jbVolume.setText(null);
		jbVolume.setIcon(mold); // texto do botão
		jbVolume.setPressedIcon(mold); // Imagem ao clicar
		jbVolume.setBorderPainted(false);
		jbVolume.setContentAreaFilled(false);
		
		add(jbVolume);
				
	}
	
	protected void paintComponent(Graphics g) {
		
		Image img = Util.background.getImage();
		g.drawImage(img, 0, 0, getWidth(), Util.DEFAULT_SCREEN_HEIGHT * 10, this);
		
		img = imgSettings.getImage();
		g.drawImage(img, 180, 10, 200, 45, this);
		
		if(status == true) {
			Image img1 = imgVolumeTrue.getImage();
			g.drawImage(img1, x + spaceX, y + spaceY, widthHeight, widthHeight, this);
		}else {
			img = imgVolumeFalse.getImage();
			g.drawImage(img, x + spaceX, y + spaceY, widthHeight, widthHeight, this);
		}
		
	}
}
