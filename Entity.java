package entity;
import java.awt.image.BufferedImage;

public class Entity
{
	public int x,y;
	public int speed;
	
	public BufferedImage up1,down1,left1,right1,left2,right2,left3,right3;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}