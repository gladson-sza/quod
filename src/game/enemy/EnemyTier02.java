package game.enemy;

import java.util.Random;

/*
 * Inimigo com complexidade basica, movimenta-se tres vezes pelo eixo x
 * continua o movimento pelo eixo y
 */
public class EnemyTier02 extends Enemy {

	private static final int LEFT = 1;
	private static final int RIGHT = 0;

	private int direction;
	private int timeGo;

	public EnemyTier02(int enemyPosition) {
		super(enemyPosition);

		direction = new Random().nextInt(2);
		timeGo = 120;

	}

	@Override
	public void run() {

		while (isActive()) {

			// Movimenta para a esquerda
			if (direction == RIGHT && timeGo > 0 && getY() > 0) {
				moveRight();
				timeGo--;

				if (timeGo % 30 == 0) {
					direction = LEFT;
				}
			}

			// Movimenta para a direita
			if (direction == LEFT && timeGo > 0 && getY() > 0) {
				moveLeft();
				timeGo--;

				if (timeGo % 30 == 0) {
					direction = RIGHT;
				}
			}

			// Movimento padrao
			moveDown();

			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
