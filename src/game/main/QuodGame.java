/**
 * @author Gladson Souza de Araújo
 * @author Paulo Victor Ribeiro da Silva
 * 
 */

package game.main;

import javax.swing.JFrame;

public class QuodGame extends JFrame {
	
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
		setSize(640, 480);
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
}
