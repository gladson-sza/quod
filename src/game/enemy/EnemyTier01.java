package game.enemy;

/*
 * Inimigo simples, movimenta-se apenas pelo eixo y
 */
public class EnemyTier01 extends Enemy {

	public EnemyTier01(int enemyPosition) {
		super(enemyPosition);
	}

	@Override
	public void run() {

		while (isActive()) {
			try {
				Thread.sleep(45);
				moveDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
