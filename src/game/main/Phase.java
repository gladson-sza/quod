package game.main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.component.Laser;
import game.component.Player;
import game.component.Util;
import game.enemy.*;
import game.sound.Sound;

public class Phase extends JPanel {

	private static final long serialVersionUID = 1L;

	private int enemyDown;
	private int currentStage;
	private static int score;
	private boolean haveBoss;
	public boolean bossDie;

	protected ImageIcon background;
	protected ImageIcon imgLife;

	public Timer timerEnemy;
	public ArrayList<Enemy> alEnemy;
	public EnemyBoss boss;
	public Player player;
	public Image explosion;
	protected QuodGame qg;

	protected int posLife;
	public int life;
	public int moveBackground;
	public int moveBackgroundAux;
	public boolean side = true;

	public JButton jbStop;

	protected ImageIcon imgText;
	protected ImageIcon imgBack;
	protected ImageIcon imgDamage;
	protected ImageIcon hitLife;
	protected ImageIcon imgStart;

	public Phase(String backgroundPath) {

		currentStage = 0;
		background = new ImageIcon(backgroundPath);
		
		bossDie = false;
		haveBoss = false;
		life = 3;
		enemyDown = 0;

		player = new Player();
		alEnemy = new ArrayList<Enemy>();

		explosion = new ImageIcon("res\\effects\\explosion.gif").getImage();
		imgLife = new ImageIcon("res\\ship\\life.png");
		imgDamage = new ImageIcon("res\\effects\\damange.png");
		hitLife = new ImageIcon("res\\ship\\hitLife.png");

		moveBackground = -2100;
		moveBackgroundAux = -5100;

		// botÃ£o pausar
		imgStart = new ImageIcon("res\\menu\\start.png");
		imgBack = new ImageIcon(" ");
		imgText = new ImageIcon("res\\menu\\stop.png");

		jbStop = new JButton();

		setLayout(null);

		jbStop.setBounds(Util.DEFAULT_SCREEN_WIDTH - 50, 5, 35, 35);
		add(jbStop);

		jbStop.setText(null);
		jbStop.setIcon(imgBack); // texto do botÃ£o
		jbStop.setPressedIcon(imgBack); // Imagem ao clicar

		// borda
		jbStop.setBorderPainted(false);
		jbStop.setContentAreaFilled(false);

		// temporizado para gerar novo inimigo
		timerEnemy = new Timer(1750, new NewEnemy());

	}

	/*
	 * reinicia todos os atributos
	 */
	@SuppressWarnings("deprecation")
	public void restartGame() {
		phaseClear();
		Util.STOP = false;

		Util.SOUND_PHASE.stop();

		if (Util.STATUS_SOUND) {
			Util.SOUND_PHASE = new Sound(new File("res\\sound\\phaseTheme.mp3"));
			Util.SOUND_PHASE.start();
		}

		life = 3;
		enemyDown = 0;
		bossDie = false;
		haveBoss = false;
		setScore(0);
		timerEnemy.start();
		this.requestFocus();
		moveBackground = -2100;
		moveBackgroundAux = -5100;
	}

	/*
	 * Essa classe instancia os novos inimigos no Timer de acordo com a fase
	 */
	private class NewEnemy implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (currentStage) {
			case 0:
				alEnemy.add(new EnemyTier01(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH - Util.ENEMY_WIDTH)));
				break;
			case 1:
				alEnemy.add(new EnemyTier02(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH - Util.ENEMY_WIDTH)));
				break;
			case 2:
				alEnemy.add(new EnemyTier03(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH - Util.ENEMY_WIDTH)));
				break;
			case 3:
				alEnemy.add(new EnemyTier04(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH - Util.ENEMY_WIDTH)));
				break;
			default:
				switch (new Random().nextInt(4)) {
				case 0:
					alEnemy.add(
							new EnemyTier01(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH / 2 - Util.ENEMY_WIDTH)));
					break;
				case 1:
					alEnemy.add(
							new EnemyTier02(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH / 2 - Util.ENEMY_WIDTH)));
					break;
				case 2:
					alEnemy.add(
							new EnemyTier03(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH / 2 - Util.ENEMY_WIDTH)));
					break;
				case 3:
					alEnemy.add(
							new EnemyTier04(new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH / 2 - Util.ENEMY_WIDTH)));
					break;
				}

				switch (new Random().nextInt(4)) {
				case 0:
					alEnemy.add(new EnemyTier01(Util.DEFAULT_SCREEN_WIDTH / 2
							+ new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH / 2 - Util.ENEMY_WIDTH)));
					break;
				case 1:
					alEnemy.add(new EnemyTier02(Util.DEFAULT_SCREEN_WIDTH / 2
							+ new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH / 2 - Util.ENEMY_WIDTH)));
					break;
				case 2:
					alEnemy.add(new EnemyTier03(Util.DEFAULT_SCREEN_WIDTH / 2
							+ new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH / 2 - Util.ENEMY_WIDTH)));
					break;
				case 3:
					alEnemy.add(new EnemyTier04(Util.DEFAULT_SCREEN_WIDTH / 2
							+ new Random().nextInt(Util.DEFAULT_SCREEN_WIDTH / 2 - Util.ENEMY_WIDTH)));
					break;
				}
			}

		}

	}

	/*
	 * Esse metodo zera todos os atributos
	 */
	public void phaseClear() {

		player.alLaser.clear();
		player = new Player();

		for (int i = 0; i < alEnemy.size(); i++)
			alEnemy.get(i).alLaser.clear();

		alEnemy.clear();

	}

	/*
	 * Movimenta o fundo
	 */
	public void moveBackground() {
		if (!Util.STOP && !haveBoss) {

			// Movimenta o eixo y da imagem
			moveBackground += 3;
			moveBackgroundAux += 3;

			// Alterna infinitamente
			if (moveBackgroundAux == 0)
				moveBackground = -3000;
			if (moveBackground == 0)
				moveBackgroundAux = -3000;
		}
	}

	/*
	 * Verifica se o player acertou a nave inimiga ou se ela saiu da tela
	 */
	public void verifyPlayerHits() {
		for (int i = 0; i < player.alLaser.size(); i++) {

			// Verifica se o laser saiu da tela
			if (player.alLaser.get(i).getY() <= -player.alLaser.get(i).getHeight())
				player.alLaser.get(i).setActive(false);

			// Verifica se atingiu algum inimigo ou se saiu da tela
			for (int j = 0; j < alEnemy.size(); j++) {
				if (Util.colision(player.alLaser.get(i), alEnemy.get(j)) && alEnemy.get(j).isActive()) {
					player.alLaser.get(i).setActive(false);
					alEnemy.get(j).setActive(false);

					setScore(getScore() + 100);
					enemyDown++;
				}

				if (alEnemy.get(j).getY() >= +Util.DEFAULT_SCREEN_HEIGHT) {
					alEnemy.get(j).setActive(false);
				}
			}
		}
	}

	/*
	 * Verifica se o inimigo atingiu o player com o laser
	 */
	public void verifyEnemyHits() {
		for (int i = 0; i < alEnemy.size(); i++) {
			Enemy enemy = alEnemy.get(i);

			for (int j = 0; j < enemy.alLaser.size(); j++) {
				Laser enemyLaser = enemy.alLaser.get(j);

				if (Util.colision(enemyLaser, player) && enemyLaser.isActive()) {
					enemyLaser.setActive(false);
					life--;
					if (Util.STATUS_EFFECTS)
						Util.hit = true;

					if (Util.STATUS_SOUND && life > 0)
						new Sound(new File("res\\sound\\hited.mp3")).start();

				}
			}

		}
	}

	/*
	 * Desenha os lasers e remove os que sairam da tela ou atingiram adversarios
	 */
	public void drawPlayerLaser(Graphics g) {
		// Remove o laser do player
		for (int i = 0; i < player.alLaser.size(); i++) {
			if (!player.alLaser.get(i).isActive()) {
				player.alLaser.remove(i);
			}

		}

		// Desenha o Laser do player
		for (int i = 0; i < player.alLaser.size(); i++) {
			if (player.alLaser.get(i).isActive() && player.isActive()) {
				player.alLaser.get(i).draw(g);
			}
		}
	}

	public void drawEnemyLaser(Graphics g) {
		// Remove o laser inimigo
		for (int i = 0; i < alEnemy.size(); i++) {
			Enemy enemy = alEnemy.get(i);

			for (int j = 0; j < enemy.alLaser.size(); j++) {
				Laser enemyLaser = enemy.alLaser.get(j);

				if (!enemyLaser.isActive() || !enemy.isActive()) {
					enemy.alLaser.remove(j);
				}
			}
		}

		// Desenha o laser inimigo
		for (int i = 0; i < alEnemy.size(); i++) {
			Enemy enemy = alEnemy.get(i);

			if (enemy.isActive()) {
				for (int j = 0; j < enemy.alLaser.size(); j++) {
					Laser enemyLaser = enemy.alLaser.get(j);

					if (enemyLaser.isActive()) {
						enemyLaser.draw(g);
					}
				}
			}
		}
	}

	/*
	 * Desenha ou remove o player da tela
	 */
	public void drawPlayer(Graphics g) {

		if (life > 0)
			player.draw(g);
		else if (!player.isExplode() && Util.STATUS_EFFECTS) {
			try {
				AudioInputStream as = AudioSystem.getAudioInputStream(new File("res\\sound\\playerExplosion.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(as);
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

			player.setExplode(true);

		} else if (player.getCountExplosion() < Util.EXPLOSION_TIME && Util.STATUS_EFFECTS) {
			if (!Util.STOP) {
				g.drawImage(explosion, player.getX(), player.getY(), 100, 100, this);
				player.countExplosionUp();
				player.setActive(false);
			}
		} else {
			Util.PLAYING = true;
		}
	}

	/*
	 * Desenha e remove os inimigos da tela
	 */
	public void drawEnemy(Graphics g) {
		for (int i = 0; i < alEnemy.size(); i++) {
			// Verifica se atingiu o player
			if (Util.colision(player, alEnemy.get(i)) && alEnemy.get(i).isActive()) {
				alEnemy.get(i).setActive(false);
				life--;
				if (Util.STATUS_EFFECTS)
					Util.hit = true;
			}

			// Verifica se saiu da tela
			if (alEnemy.get(i).getY() >= Util.DEFAULT_SCREEN_HEIGHT && alEnemy.get(i).isActive()) {
				alEnemy.get(i).setActive(false);
				alEnemy.get(i).setExplode(true);

				setScore(getScore() - 200);
			}

			// Desenha se estiver ativo
			if (alEnemy.get(i).isActive())
				alEnemy.get(i).draw(g);
			else if (!alEnemy.get(i).isExplode()) {
				alEnemy.get(i).setExplode(true);
				alEnemy.get(i).explode();
			} else if (alEnemy.get(i).getCountExplosion() < Util.EXPLOSION_TIME && Util.STATUS_EFFECTS) {

				g.drawImage(explosion, alEnemy.get(i).getX(), alEnemy.get(i).getY(), alEnemy.get(i).getWidth(),
						alEnemy.get(i).getHeight(), this);

				alEnemy.get(i).countExplosionUp();
			} else {
				alEnemy.remove(i);
			}
		}
	}

	/*
	 * Verifica os lasers que atingiram o boss
	 */
	public void verifyPlayerHitsInBoss() {
		for (int i = 0; i < player.alLaser.size(); i++) {
			if (Util.colision(player.alLaser.get(i), boss) && boss.isActive()) {
				player.alLaser.get(i).setActive(false);

				if (!boss.isImunity())
					boss.setLife(boss.getLife() - 1);
			}

			if (boss.getLife() == 0)
				boss.setActive(false);
		}
	}

	/*
	 * Verifica os lases do boss
	 */
	public void verifyBossHits() {
		for (int i = 0; i < boss.alLaser.size(); i++) {

			// verifica se saiu da tela
			if (boss.alLaser.get(i).getY() > Util.DEFAULT_SCREEN_HEIGHT)
				boss.alLaser.get(i).setActive(false);

			// verifica se colidiu com o player
			if (Util.colision(boss.alLaser.get(i), player) && boss.alLaser.get(i).isActive()) {
				boss.alLaser.get(i).setActive(false);
				life--;

				if (Util.STATUS_EFFECTS)
					Util.hit = true;

				if (Util.STATUS_SOUND && life > 0)
					new Sound(new File("res\\sound\\hited.mp3")).start();
			}
		}

	}

	/*
	 * Desenha o boss se setiver ativo
	 */
	public void drawBoss(Graphics g) {
		if (boss.isActive()) {
			boss.draw(g);
		} else {
			bossDie = true;
			g.drawImage(explosion, boss.getX(), boss.getY(), 100, 100, this);
		}
	}

	/*
	 * Desenha os lasers do boss
	 */
	public void drawBossLaser(Graphics g) {
		// Remove o laser
		for (int i = 0; i < boss.alLaser.size(); i++) {
			if (!boss.alLaser.get(i).isActive())
				boss.alLaser.remove(i);
		}

		// Desenha o laser
		for (int i = 0; i < boss.alLaser.size(); i++)
			boss.alLaser.get(i).draw(g);
	}

	/*
	 * Este metodo controla as colisoes da fase
	 */
	public void phaseControl(Graphics g) {

		// Controlador padrao
		moveBackground();
		verifyPlayerHits();
		verifyEnemyHits();

		// Desenha ou remove os lasers
		drawPlayerLaser(g);
		drawEnemyLaser(g);

		// Controlador para o boss
		if (haveBoss) {
			if (boss.getY() > 100 && boss.getLife() > 0) {
				Image bossLife = new ImageIcon(Util.BOSS_LIFE[boss.getLife() - 1]).getImage();
				g.drawImage(bossLife, 170, 10, 200, 50, this);
			}
			
			verifyPlayerHitsInBoss();
			verifyBossHits();
			drawBossLaser(g);
			drawBoss(g);
		}

		// Remove ou desenha os inimigos e o player
		drawPlayer(g);
		drawEnemy(g);

		if (enemyDown < 10)
			currentStage = 0;
		else if (enemyDown < 20)
			currentStage = 1;
		else if (enemyDown < 30)
			currentStage = 2;
		else if (enemyDown < 40)
			currentStage = 3;
		else {
			currentStage = 4;

			if (!haveBoss) {
				timerEnemy.setDelay(60000);
				boss = new EnemyBoss();
				haveBoss = true;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		// Cor padrÃ£o da fonte
		g.setColor(Color.WHITE);

		// Desenha o background e define as cores da fonte
		Image imageBackground = Util.backgroundGame.getImage();
		g.drawImage(imageBackground, 0, moveBackground, getWidth(), 3000, this);
		g.drawImage(imageBackground, 0, moveBackgroundAux, getWidth(), 3000, this);

		if (!Util.STOP) {
			// Executa o controlador da fase até pausar
			phaseControl(g);
		}

		// Desenha o status do Laser
		Image laserStatus = new ImageIcon(Util.LASER_CHARGE[Util.SHOOT_COUNT]).getImage();
		g.drawImage(laserStatus, 0, 60, 50, 80, null);

		// Vida do Player
		Image img;

		posLife = 10;

		img = imgLife.getImage();

		// vidas
		for (int i = 0; i < life; i++) {
			g.drawImage(img, posLife, 28, 40, 40, this);
			posLife += 37;
		}

		// vida vermelha
		if (Util.hit) {
			img = hitLife.getImage();
			g.drawImage(img, posLife, 28, 40, 40, this);
		}

		// efeito de dano
		if (Util.hit) {
			Image imageDamage = imgDamage.getImage();
			g.drawImage(imageDamage, 0, 0, getWidth(), getHeight(), this);
		}

		// Botao pause e despause
		if (Util.STOP) {
			img = imgStart.getImage();
			g.drawImage(img, Util.DEFAULT_SCREEN_WIDTH - 50, 5, 35, 35, null);
		} else {
			img = imgText.getImage();
			g.drawImage(img, Util.DEFAULT_SCREEN_WIDTH - 50, 5, 35, 35, null);
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		g2d.setBackground(Color.BLACK);
		g2d.drawString("Pontos: ", 15, 21);
		g2d.setColor(Color.yellow);
		g2d.drawString(" " + getScore(), 100, 21);
	}

	public int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Phase.score = score;
	}
}
