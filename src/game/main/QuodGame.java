/**
 * @author Gladson Souza de Araújo
 * @author Paulo Victor Ribeiro da Silva
 * 
 */

package game.main;

import game.component.Player;
import game.component.Util;
import game.phase.Phase;
import game.phase.Stage01;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class QuodGame extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	public Phase phase;
	
	/*
	 * Construtor
	 */
	public QuodGame() {
		mainScreen();
	}
	
	/*
	 * Configurações da Tela
	 */
	public void mainScreen() {

		setTitle("Quod - The Game");
		setSize(Util.DEFAULT_SCREEN_WIDTH, Util.DEFAULT_SCREEN_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		phase = new Stage01("res\\background\\galaxy_background01.jpg",
				new Player(20, Util.LASER_GREEN), 0 );
		
		add(phase);
		addKeyListener(this);
		
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
	/*
	 * Método Principal
	 */
	public static void main(String[] args) {
		new QuodGame();
	}
	
	/* Teclado */
	@Override
	public void keyPressed(KeyEvent key) {

		switch (key.getKeyCode()) {
			case KeyEvent.VK_LEFT :
				phase.player.moveLeft();
				break;
			case KeyEvent.VK_A :
				phase.player.moveLeft();
				break;
			case KeyEvent.VK_RIGHT :
				phase.player.moveRight();
				break;
			case KeyEvent.VK_D :
				phase.player.moveRight();
				break;
			case KeyEvent.VK_SPACE :
				phase.player.gun.shoot();
				break;
			default :
				break;
		}
		
		phase.repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
