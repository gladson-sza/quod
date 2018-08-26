/**
 * @author Gladson Souza de Araújo
 * 
 * Classe utilitária para mecânicas e constantes
 * de parâmetros utilizadas no jogo.
 */

package game.component;

import javax.swing.ImageIcon;

public final class Util {

	/* Variável de Controle de Jogo */
	public static boolean PLAYING = true;
	public static int SHOOT_COUNT = 0;

	/* Constante Geral Nula */
	public static final int NONE = 0;

	/* Tamanho da Tela */
	public static final int DEFAULT_SCREEN_WIDTH = 550;
	public static final int DEFAULT_SCREEN_HEIGHT = 650;

	/* Constantes de Velocidade */
	public static int SPEED_HIGH = 20;
	public static int SPEED_MEDIUM = 10;
	public static int SPEED_SLOW = 5;

	/* Constantes do Tamanho da Nave do Player */
	public static final int PLAYER_POSITION_X = 240;
	public static final int PLAYER_POSITION_Y = 500;
	public static final int PLAYER_WIDTH = 100;
	public static final int PLAYER_HEIGHT = 100;

	/* Constantes das imagens da Nave do Player */
	public static final String PLAYER_IMAGES[] = { "res\\\\ship\\\\PlayerShip\\\\PlayerShipSprite1.png",
			"res\\\\ship\\\\PlayerShip\\\\PlayerShipSprite2.png" };

	/* Constantes das imagens das Naves Inimigas */
	public static final String ENEMY_IMAGES[] = { "res\\ship\\EnemyShip\\EnemyShipSprite1.png",
			"res\\ship\\EnemyShip\\EnemyShipSprite2.png" };

	/* Imagem do status do Laser */
	public static final String LASER_CHARGE[] = { "res\\hud\\laserEmptyCharge.png", "res\\hud\\laser10Charge.png",
			"res\\hud\\laser20Charge.png", "res\\hud\\laser30Charge.png", "res\\hud\\laser40Charge.png",
			"res\\hud\\laser50Charge.png", "res\\hud\\laser60Charge.png", "res\\hud\\laser70Charge.png",
			"res\\hud\\laser80Charge.png", "res\\hud\\laser90Charge.png", "res\\hud\\laserFullCharge.png" };

  /* Constantes do Tamanho das Naves Inimigas */
	public static final int ENEMY_POSITION = -100;
	public static final int ENEMY_WIDTH = 70;
	public static final int ENEMY_HEIGHT = 70;

	/* Tempo de Explosão */
	public static final int EXPLOSION_TIME = 57;
	
	// fundo gamestart
	
	public static final ImageIcon background = new ImageIcon ("res\\background\\galaxy_background01.jpg");

	/*
	 * Essa classe verifica se o objeto a colide com o objeto b
	 */
	public static boolean colision(GameObject a, GameObject b) {

		// Plano de colisão X
		int aColisionPanelWidth = a.getX() + a.getWidth();
		int bColisionPanelWidth = b.getX() + b.getWidth();

		// Plano de colisão Y
		int aColisionPanelHeight = a.getY() + a.getHeight();
		int bColisionPanelHeight = b.getY() + b.getHeight();

		// verifica se houve colisão
		if ((a.getX() >= b.getX() && a.getX() <= bColisionPanelWidth
				|| b.getX() >= a.getX() && b.getX() <= aColisionPanelWidth)
				&& (a.getY() >= b.getY() && a.getY() <= bColisionPanelHeight
						|| b.getY() >= a.getY() && b.getY() <= aColisionPanelHeight))
			return true;

		return false;
	}

}
