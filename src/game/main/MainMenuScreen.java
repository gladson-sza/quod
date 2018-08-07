package game.main;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuScreen extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
		
	protected ImageIcon imageIcon;
	protected JButton jbStartGame;
	protected JButton jbSave;
	protected JButton jbConfig;
	
	/*
	 * Construtor
	 */
	protected MainMenuScreen() {
		
		// imagem de fundo
		imageIcon = new ImageIcon("res\\background\\galaxy_background01.jpg");
		
		// configuração do layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// configuração dos botões
		jbStartGame = new JButton();
		jbSave = new JButton();
		jbConfig = new JButton();
		
		jbStartGame.setText("Novo Jogo");
		jbSave.setText("Continuar");
		jbConfig.setText("Configurações");
		
		jbStartGame.addActionListener(this);
		jbSave.addActionListener(this);
		jbConfig.addActionListener(this);
		
		// posiciona os botões na tela
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridwidth = 3;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.5;
		
		// individual de botões
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(jbStartGame, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(jbSave, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(jbConfig, gbc);
			
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Image img = imageIcon.getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*
		if (e.getSource() == jbStartGame)
		
		else if (e.getSource() == jbSave)
		
		else
		*/
		
	}
}
