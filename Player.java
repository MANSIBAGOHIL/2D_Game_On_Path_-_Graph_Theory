package entity;
import java.awt.Color;
import java.awt.Dimension;
import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;

public class Player extends Entity
{
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp,KeyHandler keyH)   //GamePanel gp,KeyHandler keyH
	{
		//gp = new GamePanel();
		//keyH = new KeyHandler();
		this.gp= gp;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues()
	{
		x=100;
		y=100;
		speed = 4;
		direction = "up";
	}
	public void getPlayerImage()
	{
		try
		{
			up1 = ImageIO.read(getClass().getResourceAsStream("/star_up.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/star_up.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/star_right.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/star_left.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/running_0R.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/running_0L.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/running_1R.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/running_1L.png"));
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void update()
	{
		if(keyH.upPressed == true)
		{
			direction="up";
			y -= speed;
		}
		else if(keyH.downPressed == true)
		{
			direction = "down";
			y += speed;
		}
		else if(keyH.leftPressed == true)
		{
			direction = "left";
			x -= speed; 
		}
		else if(keyH.rightPressed == true)
		{
			direction = "right";
			x += speed;
		}
		
		spriteCounter++;
		if(spriteCounter >15)
		{
			if(spriteNum == 1)
			{
				spriteNum = 2;
			}
			else if(spriteNum == 2)
			{
				spriteNum = 3;
			}
			else if(spriteNum == 3)
			{
				spriteNum = 1;
			}
			else
			{
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	public void draw(Graphics2D g1)
	{
		//g1.setColor(Color.white);
		//g1.fillRect(x, y, gp.tileSize, gp.tileSize); //x, y, Width, Height
		
		BufferedImage image = null;
		
		switch(direction)
		{
			case "up":
			image = up1;
			break;
			case "down":
			image = down1;
			break;
			case "left":
			if(spriteNum == 1)
			{
				image = left1;
			}
			if(spriteNum == 2)
			{
				image = left2;
			}
			if(spriteNum == 3)
			{
				image = left3;
			}
			break;
			case "right":
			if(spriteNum == 1)
			{
				image = right1;
			}
			if(spriteNum == 2)
			{
				image = right2;
			}
			if(spriteNum == 3)
			{
				image = right3;
			}
			break;
		}
		g1.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
	}
}