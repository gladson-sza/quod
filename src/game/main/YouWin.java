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

public class YouWin extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Phase phase;
	protected ImageIcon background;
	protected ImageIcon imgWin;
	protected ImageIcon imgRestart;
	protected JButton jbRestart;
	
	public YouWin(Phase phase) {
		this.phase = phase;
		
		background = new ImageIcon("res\\background\\backgroundWIN.jpg");
		imgWin = new ImageIcon("res\\logo\\win.png");
		imgRestart = new ImageIcon("res\\button\\restart80.png");
		
		setLayout(null);
		
		jbRestart = new JButton();
		jbRestart.setBounds(Util.DEFAULT_SCREEN_WIDTH / 2 - 36, 215, 80, 80);
		jbRestart.setText(null);
		jbRestart.setIcon(imgRestart); // texto do botão
		jbRestart.setPressedIcon(imgRestart); // Imagem ao clicar
		jbRestart.setBorderPainted(false);
		jbRestart.setContentAreaFilled(false);
		
		jbRestart.addMouseListener(new MouseAdapter() {
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
		
		add(jbRestart);

	}
	
	protected void paintComponent(Graphics g) {
		Image img;
		
		img = background.getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		
		img = imgWin.getImage();
		g.drawImage(img, 215, 15, 500, 100, null);
	}
}
