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

public class Control extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	// variaveis para contruir o botao voltar
	protected JButton jbComeBack;
	protected ImageIcon comeBackText;
	protected ImageIcon imgback;
	protected ImageIcon imgControl;
	protected ImageIcon imgLogoControls;
	
	public Control() {
		
		comeBackText = new ImageIcon("res\\button\\voltar.png");
		imgControl = new ImageIcon("res\\menu\\tecla.png");
		imgback = new ImageIcon(" ");
		imgLogoControls = new ImageIcon("res\\button\\controles.png");
		
		jbComeBack = new JButton();
		
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
		
		jbComeBack.setBounds(12, 12, 45, 45);
		
		setLayout(null);
		
		jbComeBack.setText(null);
		jbComeBack.setIcon(comeBackText); // texto do botão
		jbComeBack.setPressedIcon(imgback); // Imagem ao clicar
		jbComeBack.setBorderPainted(false);
		jbComeBack.setContentAreaFilled(false);
		
		add(jbComeBack);
	}
	
	protected void paintComponent(Graphics g) {
		
		Image img = Util.backgroundMenu2.getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		
		img = imgLogoControls.getImage();
		g.drawImage(img, Util.DEFAULT_SCREEN_HEIGHT/2 -170, 15, 300, 40, this);
		
		img = imgControl.getImage();
		g.drawImage(img, 0, 15, getWidth(), getHeight(), this);
		
	}
}
