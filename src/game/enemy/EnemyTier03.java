package game.enemy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import game.component.EnemyLaser;

public class EnemyTier03 extends Enemy {
	
	private Timer shootTimer;
	
	public EnemyTier03(int enemyPosition) {
		super(enemyPosition);
		
		shootTimer = new Timer(1300, new Shoot());
		shootTimer.start();
	}
	
	/*
	 * Classe interna que efetua os disparos
	 */
	private class Shoot implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			alLaser.add(new EnemyLaser(getX() + 25, getY() + getHeight() + 5, 25, 25, true));
		}
		
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
