package javalike;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener {

	private Thread eggThread;
	private BufferedImage backImage;

	private boolean gameOver;

	
	private int level = 1;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public BufferedImage getGameoverImage() {
		return gameoverImage;
	}

	public void setGameoverImage(BufferedImage gameoverImage) {
		this.gameoverImage = gameoverImage;
	}

	Egg eggObject = new Egg(this);
	private BufferedImage gameoverImage;
	Basket basketObject = new Basket(this);
	Player playerObject = new Player(this);

	public Thread getEggThread() {
		return eggThread;
	}

	public void setEggThread(Thread eggThread) {
		this.eggThread = eggThread;
	}

	public BufferedImage getBackImage() {
		return backImage;
	}

	public void setBackImage(BufferedImage backImage) {
		this.backImage = backImage;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Egg getEggObject() {
		return eggObject;
	}

	public void setEggObject(Egg eggObject) {
		this.eggObject = eggObject;
	}

	public Basket getBasketObject() {
		return basketObject;
	}

	public void setBasketObject(Basket basketObject) {
		this.basketObject = basketObject;
	}

	public Player getPlayerObject() {
		return playerObject;
	}

	public void setPlayerObject(Player playerObject) {
		this.playerObject = playerObject;
	}

	public void addNotify() {
		super.addNotify();
		eggThread = new Thread(this);
		eggThread.start();
	}

	public void paint(Graphics g) {
		requestFocus();
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		setBackground(Color.BLACK);

		g2.drawImage(backImage, 0, 0, null);

		eggObject.paint(g2);
		basketObject.paint(g2);

		g2.setColor(Color.WHITE);
		Font font = new Font("arial", Font.BOLD, 30);
		g2.setFont(font);
		g2.drawString("Score:", 30, 480);
		g2.drawString("" + playerObject.getScore(), 130, 480);
		g2.drawString("level:", 480, 480);
		g2.drawString("" + level, 560, 480);

		if (gameOver)
			displayGameOver(g2);

	}

	public void move() {
		eggObject.move();
		basketObject.move();
		playerObject.getEgg();
	}

	public Game() {
		addKeyListener(this);
		try {
			backImage = ImageIO.read(new File("back.png"));
			gameoverImage = ImageIO.read(new File("gameover.png"));

		} catch (IOException ex) {
			System.err.println("not found image file!!!");
		}

	}

	public void displayGameOver(Graphics2D g) {
		g.drawImage(gameoverImage, 0, 0, null);

	}

	public void run() {

		while (!gameOver) {

			move();
			repaint();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	
	public void keyPressed(KeyEvent e) {

		basketObject.keyPressed(e);

	}

	
	public void keyReleased(KeyEvent e) {
		basketObject.keyReleased(e);

	}


	public void keyTyped(KeyEvent arg0) {

	}

}
