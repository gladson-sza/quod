package game.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.component.Util;

public class Settings extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	protected ImageIcon logo;
	
	// variaveis para contruir o botao voltar
	protected JButton jbComeBack;
	protected ImageIcon comeBackText;
	protected ImageIcon imgback;
	
	// variaveis para contruir o botao volume
	protected JButton jbVolume;
	protected ImageIcon mold;
	
	protected ImageIcon imgVolumeTrue;
	protected ImageIcon imgVolumeFalse;
	
	// variaveis para construir o botao efeitos especiais
	protected JButton jbEffects;
	protected ImageIcon moldEffects;
	
	protected ImageIcon imgEffectsTrue;
	protected ImageIcon imgEffectsFalse;
	
	
	// auxiliares
	private static int widthHeight = 24; // nao pode mudar esses valores (tamanho do volume)
	private static int spaceX = 310, spaceY = 9; //nao pode mudar esses valores (posicao da volume)
	private int x = 100, y = 225; // posicao do botao volume, pode alterar
	private int space = 80;
	
	//logo
	protected ImageIcon imgSettings;
	
	
	public Settings() {
		
		logo = new ImageIcon("res\\menu\\setLogo.png");
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
		
		// Botao efeitos especiais
		
		moldEffects = new ImageIcon("res\\menu\\moldeEfeitos.png");
		
		jbEffects = new JButton();
		jbEffects.setBounds(x, y + space, 350, 45);
		
		jbEffects.setText(null);
		jbEffects.setIcon(moldEffects); // texto do botão
		jbEffects.setPressedIcon(moldEffects); // Imagem ao clicar
		jbEffects.setBorderPainted(false);
		jbEffects.setContentAreaFilled(false);
		
		add(jbEffects);
	}
	
	protected void paintComponent(Graphics g) {
		
		Image img = Util.background.getImage();
		g.drawImage(img, 0, 0, getWidth(), Util.DEFAULT_SCREEN_HEIGHT * 10, this);
		
		// ajustes
		img = imgSettings.getImage();
		g.drawImage(img, 180, 10, 200, 70, this);
		
		// musica
		if(Util.STATUS_SOUND) {
			Image img1 = imgVolumeTrue.getImage();
			g.drawImage(img1, x + spaceX, y + spaceY, widthHeight, widthHeight, this);
		}else {
			img = imgVolumeFalse.getImage();
			g.drawImage(img, x + spaceX - 2, y + spaceY, widthHeight, widthHeight, this);
		}
		
		// efeitos especiais
		
		if(Util.STATUS_EFFECTS) {
			Image img1 = imgVolumeTrue.getImage();
			g.drawImage(img1, x + spaceX, y + spaceY + space, widthHeight, widthHeight, this);
		}else {
			img = imgVolumeFalse.getImage();
			g.drawImage(img, x + spaceX - 2, y + spaceY + space, widthHeight, widthHeight, this);
		}
		
		// QuodGame
		img = logo.getImage();
		g.drawImage(img, 370, Util.DEFAULT_SCREEN_HEIGHT - 125, 200, 80, null);
		
		
	}
}
