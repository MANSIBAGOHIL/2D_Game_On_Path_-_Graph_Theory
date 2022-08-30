package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.*;

import entity.Player;

public class GamePanel extends JPanel implements Runnable
{
	//SCREEN SETTINGS 
	final int originalTileSize = 16; // 16x16 tiles
	final int scale = 3;
	
	//private Player player;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tiles
	final int maxScreenCol = 18;
	final int maxScreenRow = 14;
	
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,keyH);
	
	
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	int FPS = 120;
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void run()
	{
		double drawInterval = 1000000000/FPS; //0.016666 sec
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		
		while(gameThread != null)
		{
			//1.UPDATE. info updation like character positions
			//2.DRAW. draw screen as updated info
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1)
			{
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if(timer >= 1000000000)
			{
				System.out.println("FPS: "+drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	public void update()
	{
		player.update();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g1 = (Graphics2D)g;
		
		player.draw(g1);
		
		g1.dispose();
	}
}