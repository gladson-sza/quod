package game.enemy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import game.component.EnemyLaser;
import game.component.Util;

public class EnemyBoss extends Enemy {

	Timer timerShoot;

	private final int LEFT = 0;
	private final int RIGHT = 1;

	private int relativePosition;
	private int action;
	private int life;
	private boolean imunity;

	/*
	 * Construtor
	 */
	public EnemyBoss() {
		super(Util.DEFAULT_SCREEN_WIDTH / 2 - Util.ENEMY_WIDTH / 2);

		setWidth(85);

		ship = new ImageIcon("res\\ship\\EnemyShip\\bossShip.gif");
		setLife(5);

		action = new Random().nextInt(2);
		relativePosition = LEFT;

		timerShoot = new Timer(500, new BossShoot());
		timerShoot.start();
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isImunity() {
		return imunity;
	}

	public void setImunity(boolean imunity) {
		this.imunity = imunity;
	}

	/*
	 * Disparos do boss
	 */
	private class BossShoot implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!Util.STOP)
				alLaser.add(new EnemyLaser(getX() + 25, getY() + getHeight() + 5, 35, 30, true));
		}

	}

	@Override
	public void draw(Graphics g) {

		Image imageShip = ship.getImage();
		g.drawImage(imageShip, getX(), getY(), getWidth(), getHeight(), null);

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
	 * O boss realiza um movimento de uTurn com a sua nave de maneira veloz nesse
	 * tempo o boss nao ataca, mas nao recebe dano
	 */
	public void uTurn() {

		setImunity(true);

		setSpeedY(Util.SPEED_HIGH);
		setSpeedX(Util.SPEED_HIGH);

		// se aproxima do player
		while (getY() + getHeight() < Util.PLAYER_POSITION_Y - Util.SPEED_HIGH) {
			moveDown();
			update();
		}

		// realiza o movimento lateral
		if (getX() == 0) {
			relativePosition = RIGHT;
			while (getX() + getWidth() < Util.DEFAULT_SCREEN_WIDTH) {
				moveRight();
				update();
			}
		} else {
			relativePosition = LEFT;
			while (getX() > 0) {
				moveLeft();
				update();
			}
		}

		// retorna a posicao inicial disparando
		timerShoot.setDelay(250);
		timerShoot.start();

		while (getY() >= Util.DEFAULT_SCREEN_HEIGHT / 4) {
			moveUp();
			update();
		}

		// Reseta a velocidade
		timerShoot.setDelay(800);
		setSpeedY(Util.SPEED_SLOW);
		setSpeedX(Util.SPEED_SLOW);

		setImunity(false);

	}

	/*
	 * Vai para a esquerda efetuando disparos
	 */
	public void leftShoots() {
		relativePosition = LEFT;

		while (getX() > 0 && isActive()) {
			moveLeft();
			timerShoot.start();
			update();
		}

	}

	/*
	 * Vai para a direita efetuando disparos
	 */
	public void rightShoots() {
		relativePosition = RIGHT;

		while (getX() + getWidth() < Util.DEFAULT_SCREEN_WIDTH && isActive()) {
			moveRight();

			timerShoot.start();
			update();
		}

	}

	/*
	 * O boss efetua disparos em dois pontos da tela de maneira veloz
	 */
	public void zigZagShoot() {

		// Aumenta a velocidade
		setSpeedX(Util.SPEED_HIGH);
		setSpeedY(Util.SPEED_HIGH);

		// variaveis de controle
		int countMove = 4;
		int countShoot = 5;
		int holdLaser = 5;

		// Verifica para onde deve ir
		switch (relativePosition) {
		case LEFT: // Para a esquerda

			// posicao inicial esquerda
			while (getX() <= Util.DEFAULT_SCREEN_WIDTH / 4 && isActive()) {
				moveRight();
				update();
			}

			// repte quatro vezes
			while (countMove-- > 0 && isActive()) {

				while (countShoot-- > 0 && isActive()) {
					alLaser.add(new EnemyLaser(getX() + 25, getY() + getHeight() + 5, 35, 30, true));

					while (holdLaser-- > 0) // segura o tempo do disparo
						update();

					holdLaser = 5; // Reseta o tempo do disparo
				}

				// Reseta o disparo
				countShoot = 5;

				/* Alterna as posicoes */
				if (relativePosition == LEFT) {
					relativePosition = RIGHT;

					while (getX() < 345 && isActive()) {
						moveRight();
						update();
					}
				} else {
					relativePosition = LEFT;

					while (getX() > 150 && isActive()) {
						moveLeft();
						update();
					}
				}

			}

			// volta para a borda de acao
			if (isActive())
				rightShoots();

			break;
		case RIGHT: // Para a direita

			while (getX() > 345 && isActive()) {
				moveLeft();
				update();
			}

			// repte quatro vezes
			while (countMove-- > 0 && isActive()) {

				while (countShoot-- > 0 && isActive()) {
					alLaser.add(new EnemyLaser(getX() + 25, getY() + getHeight() + 5, 35, 30, true));

					while (holdLaser-- > 0) // segura o tempo do disparo
						update();

					holdLaser = 5; // Reseta o tempo do disparo
				}

				// Reseta o disparo
				countShoot = 5;

				/* Alterna as posicoes */
				if (relativePosition == RIGHT && isActive()) {
					relativePosition = LEFT;

					while (getX() > 150 && isActive()) {
						moveLeft();
						update();
					}
				} else {
					relativePosition = RIGHT;

					while (getX() < 345 && isActive()) {
						moveRight();
						update();
					}
				}

			}

			if (isActive())
				leftShoots();

			break;
		}

		// Reseta a velocidade
		setSpeedX(Util.SPEED_SLOW);
		setSpeedY(Util.SPEED_SLOW);

	}

	@Override
	public void run() {

		setImunity(true);
		setSpeedY(Util.SPEED_SLOW);

		// Entrada do boss, ele vai ate parte da tela
		while (getY() < Util.DEFAULT_SCREEN_HEIGHT / 4 && !Util.STOP) {
			moveDown();
			update();
		}

		timerShoot.stop();
		timerShoot.setDelay(800);

		setImunity(false);

		// Controle das acoes do boss, sao eventos aleatorios
		while (isActive()) {

			if (!Util.STOP) {
				switch (action) {
				case 0:
					leftShoots();
					break;
				case 1:
					rightShoots();
					break;
				case 2:
					uTurn();
					break;
				case 3:
					zigZagShoot();
					break;
				}
			}

			// Aleatoriza o proximo evento
			action = new Random().nextInt(4);
			timerShoot.stop();
			update();
		}

	}

}
