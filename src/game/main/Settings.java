package game.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.component.Util;
import game.sound.Sound;

public class Settings extends JPanel {

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
	private static int widthHeight = 45; // nao pode mudar esses valores (tamanho do volume)
	private static int spaceX = 310, spaceY = 3; // nao pode mudar esses valores (posicao da volume)
	private int x = 100, y = 225; // posicao do botao volume, pode alterar
	private int space = 80;

	public Settings() {

		logo = new ImageIcon("res\\button\\ajustes.png");
		

		// Som e mudo
		imgVolumeTrue = new ImageIcon("res\\menu\\som.png");
		imgVolumeFalse = new ImageIcon("res\\menu\\mudo.png");

		comeBackText = new ImageIcon("res\\button\\voltar.png");
		imgback = new ImageIcon(" ");

		/*
		 * Configuracao do botao voltar
		 */
		jbComeBack = new JButton();
		jbComeBack.setBounds(12, 12, 45, 45);
		
		jbComeBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (Util.STATUS_EFFECTS)
					new Sound(new File("res\\sound\\buttonBelow.mp3")).start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});

		setLayout(null);

		jbComeBack.setText(null);
		jbComeBack.setIcon(comeBackText); // texto do botão
		jbComeBack.setPressedIcon(imgback); // Imagem ao clicar
		jbComeBack.setBorderPainted(false);
		jbComeBack.setContentAreaFilled(false);

		add(jbComeBack);

		/*
		 * Configuracao do botao de volume
		 */

		mold = new ImageIcon("res\\menu\\m�sica.png");

		jbVolume = new JButton();
		jbVolume.setBounds(x, y, 400, 40);

		jbVolume.setText(null);
		jbVolume.setIcon(mold); // texto do botão
		jbVolume.setPressedIcon(mold); // Imagem ao clicar
		jbVolume.setBorderPainted(false);
		jbVolume.setContentAreaFilled(false);

		add(jbVolume);

		// Botao efeitos especiais

		moldEffects = new ImageIcon("res\\menu\\efeitos.png");

		jbEffects = new JButton();
		jbEffects.setBounds(x, y + space, 400, 40);

		jbEffects.setText(null);
		jbEffects.setIcon(moldEffects); // texto do botão
		jbEffects.setPressedIcon(moldEffects); // Imagem ao clicar
		jbEffects.setBorderPainted(false);
		jbEffects.setContentAreaFilled(false);

		add(jbEffects);
	}

	protected void paintComponent(Graphics g) {

		Image img = Util.backgroundMenu2.getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		// ajustes
		img = logo.getImage();
		g.drawImage(img, 180, 15, 300, 40, this);

		// musica
		if (Util.STATUS_SOUND) {
			Image img1 = imgVolumeTrue.getImage();
			g.drawImage(img1, x + spaceX, y + spaceY, widthHeight, widthHeight, this);
		} else {
			img = imgVolumeFalse.getImage();
			g.drawImage(img, x + spaceX , y + spaceY, widthHeight, widthHeight, this);
		}

		// efeitos especiais

		if (Util.STATUS_EFFECTS) {
			Image img1 = imgVolumeTrue.getImage();
			g.drawImage(img1, x + spaceX, y + spaceY + space, widthHeight, widthHeight, this);
		} else {
			img = imgVolumeFalse.getImage();
			g.drawImage(img, x + spaceX , y + spaceY + space, widthHeight, widthHeight, this);
		}


	}
}
