package game.component;

import java.awt.Graphics;

public abstract class GameObject {
	
	/* Vida e Dano do objeto */
	private int hp;
	private int typeLaser;
	
	/* Posições do Objeto */
	private int x, y;
	private int width, height;
	private int speedX, speedY;
	private boolean active;
	
	/*
	 * Construtor
	 */
	public GameObject(int x, int y, int width, int height, int speedX, int speedY,
			boolean active, int hp, int typeLaser) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speedX = speedX;
		this.speedY = speedY;
		this.active = active;
		this.hp = hp;
		this.typeLaser = typeLaser;
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
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int damage) {
		hp -= damage;
	}

	public int getTypeLaser() {
		return typeLaser;
	}
	
	public void setTypeLaser(int typeLaser) {
		this.typeLaser = typeLaser;
	}
	
	/* Movimentos */

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
