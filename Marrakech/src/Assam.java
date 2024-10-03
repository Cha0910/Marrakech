import java.awt.*;
import javax.swing.*;

public class Assam {
	private int xpos;
	private int ypos;
	private int Direction;
	private int movePoint;
	private Grid grid;
	private int delay;
	
	public Assam(Grid g)
	{
		this.xpos = 3;
		this.ypos = 3;
		this.Direction = 0;
		this.movePoint = 0;
		this.grid = g;
		this.setImage(this.Direction);
	}
	
	private void setImage(int direction)
	{
		String path = "";
		if(direction == 0) {path = "./Images/Assam0.png";}
		
		else if(direction == 1) {path = "./Images/Assam1.png";}

		else if(direction == 2) {path = "./Images/Assam2.png";}

		else if(direction == 3) {path = "./Images/Assam3.png";}
		
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		icon.setImage(img);
		grid.buttons[xpos][ypos].setIcon(icon);	
		grid.buttons[xpos][ypos].setDisabledIcon(icon);
	}
	
	public int getDirection()
	{
		return this.Direction;
	}
	
	public int getxpos()
	{
		return this.xpos;
	}
	
	public int getypos()
	{
		return this.ypos;
	}
	
	public void setDirection(String direction)
	{
		if(direction.equals("¡ã")) {this.Direction = 0;}
		
		else if(direction.equals("¢º")) {this.Direction = 1;}
		
		else if(direction.equals("¡å")) {this.Direction = 2;}
		
		else if(direction.equals("¢¸")) {this.Direction = 3;}
		
		this.setImage(Direction);
	}
	
	public void move(int dice, int d)
	{
		this.delay = d;
		movePoint = dice;
		grid.buttons[xpos][ypos].setIcon(null);	
		if(Direction == 0)
		{
			if(ypos == 0) 
			{
				if(xpos == 0) {this.Direction = 1;}
				else if(xpos == 1) {this.Direction = 2; this.xpos = 2;}
				else if(xpos == 2) {this.Direction = 2; this.xpos = 1;}
				else if(xpos == 3) {this.Direction = 2; this.xpos = 4;}
				else if(xpos == 4) {this.Direction = 2; this.xpos = 3;}
				else if(xpos == 5) {this.Direction = 2; this.xpos = 6;}
				else if(xpos == 6) {this.Direction = 2; this.xpos = 5;}
			}
			
			else {ypos--;}
		}
		
		else if(Direction == 1)
		{
			if(xpos == 6) 
			{
				if(ypos == 0) {this.Direction = 3; this.ypos = 1;}
				else if(ypos == 1) {this.Direction = 3; this.ypos = 0;}
				else if(ypos == 2) {this.Direction = 3; this.ypos = 3;}
				else if(ypos == 3) {this.Direction = 3; this.ypos = 2;}
				else if(ypos == 4) {this.Direction = 3; this.ypos = 5;}
				else if(ypos == 5) {this.Direction = 3; this.ypos = 4;}
				else if(ypos == 6) {this.Direction = 0;}
			}
			
			else {xpos++;}
		}
		
		else if(Direction == 2)
		{
			if(ypos == 6)
			{
				if(xpos == 0) {this.Direction = 0; xpos = 1;}
				else if(xpos == 1) {this.Direction = 0; this.xpos = 0;}
				else if(xpos == 2) {this.Direction = 0; this.xpos = 3;}
				else if(xpos == 3) {this.Direction = 0; this.xpos = 2;}
				else if(xpos == 4) {this.Direction = 0; this.xpos = 5;}
				else if(xpos == 5) {this.Direction = 0; this.xpos = 4;}
				else if(xpos == 6) {this.Direction = 3;}
			}
			
			else {ypos++;}
		}
		
		else if(Direction == 3)
		{
			if(xpos == 0) 
			{
				if(ypos == 0) {this.Direction = 2;}
				else if(ypos == 1) {this.Direction = 1; this.ypos = 2;}
				else if(ypos == 2) {this.Direction = 1; this.ypos = 1;}
				else if(ypos == 3) {this.Direction = 1; this.ypos = 4;}
				else if(ypos == 4) {this.Direction = 1; this.ypos = 3;}
				else if(ypos == 5) {this.Direction = 1; this.ypos = 6;}
				else if(ypos == 6) {this.Direction = 1; this.ypos = 5;}
			}
			
			else {xpos--;}
		}
		movePoint--;
		this.setImage(this.Direction);
		if(movePoint > 0) {this.move(this.movePoint, this.delay);}
		else {grid.setEnableButtons(this);}
	}
}
