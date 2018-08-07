package game.component;

import java.awt.Graphics;

public class GameObject {
	
	/* Constante Geral Nula */
	public final int NONE = 0;
	
	/* Constantes de Velocidade */
	public final int SPEED_HIGH = 15;
	public final int SPEED_MEDIUM = 10;
	public final int SPEED_SLOW = 5;
	
	/* Constantes de Lasers */
	public final int LASER_NORMAL = 1;
	public final int LASER_DOUBLE = 2;
	public final int LASER_TRIPLE = 3;
	
	/* Constantes de Dano */
	public final int DAMAGE_LOW = 2;
	public final int DAMAGE_MEDIUM = 5;
	public final int DAMAGE_HIGH = 10;
	
	/* Vida e Dano do objeto */
	private int hp;
	private int damage;
	private int typeAmmo;
	
	/* Posições do Objeto */
	private int x, y;
	private int width, height;
	private int speedX, speedY;
	private boolean active;
	
	/*
	 * Construtor
	 */
	public GameObject(int x, int y, int width, int height, int speedX, int speedY,
			boolean active, int hp, int damage, int typeAmmo) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speedX = speedX;
		this.speedY = speedY;
		this.active = active;
		this.hp = hp;
		this.damage = damage;
		this.typeAmmo = typeAmmo;
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
	
	public int getHp() {
		return hp;
	}

	public int getDamage() {
		return damage;
	}

	public int getTypeAmmo() {
		return typeAmmo;
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
