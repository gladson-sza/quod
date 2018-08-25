package game.component;

import java.awt.Graphics;

public abstract class GameObject {

	/* Posições do Objeto */
	private int x, y;
	private int width, height;
	private int speedX, speedY;
	private boolean active;
	private boolean explode;

	/*
	 * Construtor
	 */
	public GameObject(int x, int y, int width, int height, int speedX, int speedY, boolean active) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speedX = speedX;
		this.speedY = speedY;
		this.active = active;
	}

	/* Getters e Setters */

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/* Movimentos */

	public boolean isExplode() {
		return explode;
	}

	public void setExplode(boolean explode) {
		this.explode = explode;
	}

	public void moveUp() {
		y -= speedY;
	}

	public void moveDown() {
		y += speedY;
	}

	public void moveLeft() {
		x -= speedX;
	}

	public void moveRight() {
		x += speedX;
	}

	/* Desenha o Componente */
	public void draw(Graphics g) {

	}

}
