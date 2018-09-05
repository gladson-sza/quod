package game.enemy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import game.component.EnemyLaser;
import game.component.Util;

public class EnemyBoss extends Enemy {

	Timer timerShoot;

	private final int RIGHT = 0;
	private final int LEFT = 1;

	private int action;

	/*
	 * Construtor
	 */
	public EnemyBoss() {
		super(Util.DEFAULT_SCREEN_WIDTH / 2 - Util.ENEMY_WIDTH / 2);

		action = new Random().nextInt(2);
		position = RIGHT;

		timerShoot = new Timer(500, new BossShoot());
		timerShoot.start();
	}

	/*
	 * Disparos do boss
	 */
	private class BossShoot implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			alLaser.add(new EnemyLaser(getX() + 25, getY() + getHeight() + 5, 25, 25, true));
		}

	}

	/*
	 * Tempo de atualizacao da Thread
	 */
	public void update() {
		try {
			Thread.sleep(45);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * O boss realiza um movimento de uTurn com a sua nave de maneira veloz
	 */
	public void uTurn() {

		setSpeedY(Util.SPEED_HIGH);
		setSpeedX(Util.SPEED_HIGH);

		// se aproxima do player
		while (getY() + getHeight() < Util.PLAYER_POSITION_Y - Util.SPEED_HIGH) {
			moveDown();
			update();
		}

		// realiza o movimento lateral
		if (getX() == 0) {
			position = RIGHT;
			while (getX() + getWidth() < Util.DEFAULT_SCREEN_WIDTH) {
				moveRight();
				update();
			}
		} else {
			position = LEFT;
			while (getX() > 0) {
				moveLeft();
				update();
			}
		}

		// retorna a posicao inicial
		timerShoot.setDelay(250);
		timerShoot.start();

		while (getY() >= Util.DEFAULT_SCREEN_HEIGHT / 4) {
			moveUp();
			update();
		}

		// reserta a velocidade
		timerShoot.setDelay(800);
		setSpeedY(Util.SPEED_SLOW);
		setSpeedX(Util.SPEED_SLOW);

	}

	/*
	 * Vai para a esquerda efetuando disparos
	 */
	public void leftShoots() {
		position = LEFT;

		while (getX() > 0) {
			moveLeft();
			timerShoot.start();
			update();
		}

	}

	/*
	 * Vai para a direita efetuando disparos
	 */
	public void rightShoots() {
		position = RIGHT;

		while (getX() + getWidth() < Util.DEFAULT_SCREEN_WIDTH) {
			moveRight();

			timerShoot.start();
			update();
		}

	}

	/*
	 * O boss efetua disparos em dois pontos da tela de maneira veloz
	 */
	public void zigZagShoot() {

		System.out.println("Entrou");

		// Disparos rapidos
		timerShoot.stop();
		int countTime = 0;
		int count = 4;

		// Verifica a posicao inicial
		if (position == LEFT) {

			// vai para a posicao adequada
			while (getX() < Util.DEFAULT_SCREEN_WIDTH / 4) {
				moveRight();
				update();
			}

			// efetua os disparos 4x
			while (count-- > 0) {
				
				if (position == RIGHT) {

					countTime = 0;
					while (countTime++ < 5) {

						// Espera tres updates para disparar
						int n = 3;
						while (n-- > 0)
							update();

						alLaser.add(new EnemyLaser(getX() + 25, getY() + getHeight() + 5, 25, 25, true));
					}

					// vai para a proxima posicao
					while (getX() < Util.DEFAULT_SCREEN_WIDTH - Util.DEFAULT_SCREEN_WIDTH / 3) {
						moveRight();
						update();
					}

				} else {

					countTime = 0;
					while (countTime++ < 5) {

						// Espera tres updates para disparar
						int n = 3;
						while (n-- > 0)
							update();

						alLaser.add(new EnemyLaser(getX() + 25, getY() + getHeight() + 5, 25, 25, true));
					}

					// vai para a proxima posicao
					while (getX() > Util.DEFAULT_SCREEN_WIDTH / 4) {
						moveLeft();
						update();
					}
				}

				countTime = 0;
				update();
			}

		} else {

			// vai para a posicao adequada
			while (getX() > Util.DEFAULT_SCREEN_WIDTH / 4) {
				moveLeft();
				update();
			}
		}
	}

	@Override
	public void run() {

		setSpeedY(Util.SPEED_SLOW);

		// Entrada do boss, ele vai ate parte da tela
		while (getY() < Util.DEFAULT_SCREEN_HEIGHT / 4) {
			moveDown();
			update();
		}

		timerShoot.stop();
		timerShoot.setDelay(800);

		leftShoots();

		// Controle das acoes do boss, sao eventos aleatorios
		while (isActive()) {

			switch (action) {
			case 0:
				zigZagShoot();
				// leftShoots();
				break;
			case 1:
				zigZagShoot();
				// rightShoots();
				break;
			case 2:
				zigZagShoot();
				// uTurn();
				break;
			case 3:
				zigZagShoot();
				break;
			}

			// Aleatoriza o proximo evento
			action = new Random().nextInt(4);
			timerShoot.stop();
			update();
		}

	}

}
