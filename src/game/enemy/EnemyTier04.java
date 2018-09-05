package game.enemy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import game.component.EnemyLaser;

public class EnemyTier04 extends EnemyTier02 {
	
	Timer shootTimer;
	
	public EnemyTier04(int enemyPosition) {
		super(enemyPosition);
		
		shootTimer = new Timer(1500, new Shoot());
		shootTimer.start();
	}
	
	/*
	 * Classe interna que efetua os disparos
	 */
	private class Shoot implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			alLaser.add(new EnemyLaser(getX() + 25, getY() + getHeight() + 5, 25, 25, true));
		}
		
	}
}
