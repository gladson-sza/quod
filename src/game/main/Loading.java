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

public class Loading extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected ImageIcon	imgText;
	protected JButton jbPress;
	
	public Loading() {

		setLayout(null);
		
		imgText = new ImageIcon("res\\button\\enter.png");
		
		jbPress = new JButton();
		
		jbPress.addMouseListener(new MouseAdapter() {
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
		
		jbPress.setBounds(Util.DEFAULT_SCREEN_WIDTH /2 - 80, Util.DEFAULT_SCREEN_HEIGHT / 2, 300, 40);
		jbPress.setText(null);
		jbPress.setIcon(imgText); // texto do botão
		jbPress.setPressedIcon(imgText); // Imagem ao clicar
		jbPress.setBorderPainted(false);
		jbPress.setContentAreaFilled(false);
		
		add(jbPress);
	}

	@Override
	public void paintComponent(Graphics g) {

		// Tela de Fundo
		Image imageBackground = Util.backgroundMenu2.getImage();
		g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), this);
		
		Image img = Util.quodGame.getImage();
		g.drawImage(img, 25, 40, 500, 300, this);
	}
}
