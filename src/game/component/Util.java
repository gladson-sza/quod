/**
 * @author Gladson Souza de Araújo
 * 
 * Classe utilitária para mecânicas e constantes
 * de parâmetros utilizadas no jogo.
 */

package game.component;

public final class Util {

	/* Constante Geral Nula */
	public static final int NONE = 0;

	/* Tamanho da Tela */
	public static final int DEFAULT_SCREEN_WIDTH = 550;
	public static final int DEFAULT_SCREEN_HEIGHT = 650;

	/* Constantes de Velocidade */
	public static final int SPEED_HIGH = 20;
	public static final int SPEED_MEDIUM = 10;
	public static final int SPEED_SLOW = 5;

	/* Constantes do Tamanho da Nave do Player */
	public static final int PLAYER_POSITION_X = 240;
	public static final int PLAYER_POSITION_Y = 500;
	public static final int PLAYER_WIDTH = 100;
	public static final int PLAYER_HEIGHT = 100;

	/* Constantes das imagens da Nave do Player */
	public static final String PLAYER_IMAGES[] = { "res\\\\ship\\\\PlayerShip\\\\PlayerShipSprite1.png",
			"res\\\\ship\\\\PlayerShip\\\\PlayerShipSprite2.png" };

	/* Constantes do Tamanho das Naves Inimigas */
	public static final int ENEMY_POSITION = -100;
	public static final int ENEMY_WIDTH = 70;
	public static final int ENEMY_HEIGHT = 70;

	/* Tempo de Explosão */
	public static final int EXPLOSION_TIME = 57;

	/* Constantes das imagens das Naves Inimigas */

	public static final String ENEMY_IMAGES[] = { "res\\ship\\EnemyShip\\EnemyShipSprite1.png",
			"res\\ship\\EnemyShip\\EnemyShipSprite2.png" };

	/*
	 * Essa classe verifica se o objeto a colide com o objeto b
	 */
	public static boolean colision(GameObject a, GameObject b) {

		// verifica se houve colisão
		if (a.getX() + a.getWidth() > b.getX() - b.getWidth() && b.getX() + b.getWidth() > a.getX()
				&& a.getY() <= b.getY() + b.getHeight())
			return true;

		return false;
	}

}
