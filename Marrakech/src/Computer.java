import java.awt.*;
import java.util.*;

public class Computer {
	private Game game;
	private Grid grid;
	private Assam assam;
	private int Direction;
	private int[] testpos = new int[2];
	private int[] rugPos = new int[4];
	private double directoin0sum;
	private double directoin1sum;
	private double directoin2sum;
	private double directoin3sum;
	private int minNumOther;
	private Random rand;
	
	public Computer(Game ga, Grid g, Assam a)
	{
		this.game = ga;
		this.grid = g;
		this.assam = a;
		this.rand = new Random();
	}	
	
	public String selectDirection()
	{
		int assamDirection = this.assam.getDirection();
		String direction = "";
		int xpos = this.assam.getxpos();
		int ypos = this.assam.getypos();
		double min;
		this.directoin0sum = 0;
		this.directoin1sum = 0;
		this.directoin2sum = 0;
		this.directoin3sum = 0;
		
		this.Direction = 0;
		this.testmove(4, 0, xpos, ypos);
		
		this.Direction = 1;
		this.testmove(4, 1, xpos, ypos);
		
		this.Direction = 2;
		this.testmove(4, 2, xpos, ypos);
		
		this.Direction = 3;
		this.testmove(4, 3, xpos, ypos);
		
		if(assamDirection == 0) 
		{
			min = this.directoin0sum; assamDirection = 0;
			if(this.directoin1sum <= min)
			{
				if(this.directoin1sum == min) {if(rand.nextInt(2) == 1) {min = this.directoin1sum; assamDirection = 1;}}
				else {min = this.directoin1sum; assamDirection = 1;}
			}
			if(this.directoin3sum <= min)
			{
				if(this.directoin3sum == min) {if(rand.nextInt(2) == 1) {min = this.directoin3sum; assamDirection = 3;}}
				else {min = this.directoin3sum; assamDirection = 3;}
			}
		}
		else if(assamDirection == 1) 
		{
			min = this.directoin0sum; assamDirection = 0;
			if(this.directoin1sum <= min) 
			{
				if(this.directoin1sum == min) {if(rand.nextInt(2) == 1) {min = this.directoin1sum; assamDirection = 1;}}
				else {min = this.directoin1sum; assamDirection = 1;}
			}
			if(this.directoin2sum <= min)
			{
				if(this.directoin2sum == min) {if(rand.nextInt(2) == 1) {min = this.directoin2sum; assamDirection = 2;}}
				else {min = this.directoin2sum; assamDirection = 2;}
			}
		}
		else if(assamDirection == 2) 
		{
			min = this.directoin1sum; assamDirection = 1;
			if(this.directoin2sum <= min)
			{
				if(this.directoin2sum == min) {if(rand.nextInt(2) == 1) {min = this.directoin2sum; assamDirection = 2;}}
				else {min = this.directoin2sum; assamDirection = 2;}
			}
			if(this.directoin3sum <= min)
			{
				if(this.directoin3sum == min) {if(rand.nextInt(2) == 1) {min = this.directoin3sum; assamDirection = 3;}}
				else {min = this.directoin3sum; assamDirection = 3;}
			}
		}
		else if(assamDirection == 3) 
		{
			min = this.directoin0sum; assamDirection = 0;
			if(this.directoin2sum <= min)
			{
				if(this.directoin2sum == min) {if(rand.nextInt(2) == 1) {min = this.directoin2sum; assamDirection = 2;}}
				else {min = this.directoin2sum; assamDirection = 2;}
			}
			if(this.directoin3sum <= min)
			{
				if(this.directoin1sum == min) {if(rand.nextInt(2) == 1) {min = this.directoin1sum; assamDirection = 1;}}
				else {min = this.directoin1sum; assamDirection = 1;}
			}
		}
		
		if(assamDirection == 0) {direction = "¡ã";}
		else if(assamDirection == 1) {direction = "¢º";}
		else if(assamDirection == 2) {direction = "¡å";}
		else if(assamDirection == 3) {direction = "¢¸";}
		
		return direction;
	}
	
	public int[] setRugPos()
	{
		int xpos = assam.getxpos();
		int ypos = assam.getypos();
		this.minNumOther = 49;
		
		ArrayList<int[]> gridButtons = new ArrayList<int[]>();
		boolean[] enableButtons = new boolean[12];
		boolean[] possibleSetRog = new boolean[12];
		int[] gridButton0 = {xpos,ypos-1};
		int[] gridButton1 = {xpos,ypos-2};
		int[] gridButton2 = {xpos,ypos+1};
		int[] gridButton3 = {xpos,ypos+2};		
		int[] gridButton4 = {xpos-1,ypos};
		int[] gridButton5 = {xpos-2,ypos};
		int[] gridButton6 = {xpos+1,ypos};
		int[] gridButton7 = {xpos+2,ypos};
		int[] gridButton8 = {xpos-1,ypos-1};
		int[] gridButton9 = {xpos+1,ypos-1};
		int[] gridButton10 = {xpos-1,ypos+1};
		int[] gridButton11 = {xpos+1,ypos+1};
		gridButtons.add(gridButton0);
		gridButtons.add(gridButton1);
		gridButtons.add(gridButton2);
		gridButtons.add(gridButton3);
		gridButtons.add(gridButton4);
		gridButtons.add(gridButton5);
		gridButtons.add(gridButton6);
		gridButtons.add(gridButton7);
		gridButtons.add(gridButton8);
		gridButtons.add(gridButton9);
		gridButtons.add(gridButton10);
		gridButtons.add(gridButton11);
		
		if(ypos-1 >= 0)
		{
			enableButtons[0] = true;
			
			if(ypos-2 >= 0)
			{
				enableButtons[1] = true;
			}
		}
		
		if(ypos+1 <= 6)
		{
			enableButtons[2] = true;
			
			if(ypos+2 <= 6) 
			{
				enableButtons[3] = true;
			}
		}
		
		if(xpos-1 >= 0)
		{
			enableButtons[4] = true;
			
			if(xpos-2 >= 0) 
			{
				enableButtons[5] = true;
			}
		}
		
		if(xpos+1 <= 6)
		{
			enableButtons[6] = true;
			
			if(xpos+2 <= 6) 
			{
				enableButtons[7] = true;
			}
		}
		
		if(xpos-1 >= 0 && ypos-1 >= 0)
		{
			enableButtons[8] = true;
		}
		
		if(xpos+1 <= 6 && ypos-1 >= 0)
		{
			enableButtons[9] = true;
		}
		
		if(xpos-1 >= 0 && ypos+1 <= 6)
		{
			enableButtons[10] = true;
		}
		
		if(xpos+1 <= 6 && ypos+1 <= 6)
		{
			enableButtons[11] = true;
		}
		
		if(enableButtons[0] && enableButtons[1]) {if(this.isPossibleSetRog(gridButton0, gridButton1)) {possibleSetRog[0] = true; rugPos = this.setButtonsPos(gridButton0, gridButton1);}}
		if(enableButtons[2] && enableButtons[3]) {if(this.isPossibleSetRog(gridButton2, gridButton3)) {possibleSetRog[1] = true; rugPos = this.setButtonsPos(gridButton2, gridButton3);}}
		if(enableButtons[4] && enableButtons[5]) {if(this.isPossibleSetRog(gridButton4, gridButton5)) {possibleSetRog[2] = true; rugPos = this.setButtonsPos(gridButton4, gridButton5);}}
		if(enableButtons[6] && enableButtons[7]) {if(this.isPossibleSetRog(gridButton6, gridButton7)) {possibleSetRog[3] = true; rugPos = this.setButtonsPos(gridButton6, gridButton7);}}
		if(enableButtons[0] && enableButtons[8]) {if(this.isPossibleSetRog(gridButton0, gridButton8)) {possibleSetRog[4] = true; rugPos = this.setButtonsPos(gridButton0, gridButton8);}}
		if(enableButtons[0] && enableButtons[9]) {if(this.isPossibleSetRog(gridButton0, gridButton9)) {possibleSetRog[5] = true; rugPos = this.setButtonsPos(gridButton0, gridButton9);}}
		if(enableButtons[2] && enableButtons[10]) {if(this.isPossibleSetRog(gridButton2, gridButton10)) {possibleSetRog[6] = true; rugPos = this.setButtonsPos(gridButton2, gridButton10);}}
		if(enableButtons[2] && enableButtons[11]) {if(this.isPossibleSetRog(gridButton2, gridButton11)) {possibleSetRog[7] = true; rugPos = this.setButtonsPos(gridButton2, gridButton11);}}
		if(enableButtons[4] && enableButtons[8]) {if(this.isPossibleSetRog(gridButton4, gridButton8)) {possibleSetRog[8] = true; rugPos = this.setButtonsPos(gridButton4, gridButton8);}}
		if(enableButtons[4] && enableButtons[10]) {if(this.isPossibleSetRog(gridButton4, gridButton10)) {possibleSetRog[9] = true; rugPos = this.setButtonsPos(gridButton4, gridButton10);}}
		if(enableButtons[6] && enableButtons[9]) {if(this.isPossibleSetRog(gridButton6, gridButton9)) {possibleSetRog[10] = true; rugPos = this.setButtonsPos(gridButton6, gridButton9);}}
		if(enableButtons[6] && enableButtons[11]) {if(this.isPossibleSetRog(gridButton6, gridButton11)) {possibleSetRog[11] = true; rugPos = this.setButtonsPos(gridButton6, gridButton11);}}

		return rugPos;
	}
	
	private void testmove(int dice, int direction, int xpos, int ypos)
	{
		int testmovePoint = dice;
		int testxpos = xpos , testypos = ypos;
		if(direction == 0)
		{
			if(testypos == 0) 
			{
				if(testxpos == 0) {direction = 1;}
				else if(testxpos == 1) {direction = 2; testxpos = 2;}
				else if(testxpos == 2) {direction = 2; testxpos = 1;}
				else if(testxpos == 3) {direction = 2; testxpos = 4;}
				else if(testxpos == 4) {direction = 2; testxpos = 3;}
				else if(testxpos == 5) {direction = 2; testxpos = 6;}
				else if(testxpos == 6) {direction = 2; testxpos = 5;}
			}
			
			else {testypos--;}
		}
		
		else if(direction == 1)
		{
			if(testxpos == 6) 
			{
				if(testypos == 0) {direction = 3; testypos = 1;}
				else if(testypos == 1) {direction = 3; testypos = 0;}
				else if(testypos == 2) {direction = 3; testypos = 3;}
				else if(testypos == 3) {direction = 3; testypos = 2;}
				else if(testypos == 4) {direction = 3; testypos = 5;}
				else if(testypos == 5) {direction = 3; testypos = 4;}
				else if(testypos == 6) {direction = 0;}
			}
			
			else {testxpos++;}
		}
		
		else if(direction == 2)
		{
			if(testypos == 6)
			{
				if(testxpos == 0) {direction = 0; testxpos = 1;}
				else if(testxpos == 1) {direction = 0; testxpos = 0;}
				else if(testxpos == 2) {direction = 0; testxpos = 3;}
				else if(testxpos == 3) {direction = 0; testxpos = 2;}
				else if(testxpos == 4) {direction = 0; testxpos = 5;}
				else if(testxpos == 5) {direction = 0; testxpos = 4;}
				else if(testxpos == 6) {direction = 3;}
			}
			
			else {testypos++;}
		}
		
		else if(direction == 3)
		{
			if(testxpos == 0) 
			{
				if(testypos == 0) {direction = 2;}
				else if(testypos == 1) {direction = 1; testypos = 2;}
				else if(testypos == 2) {direction = 1; testypos = 1;}
				else if(testypos == 3) {direction = 1; testypos = 4;}
				else if(testypos == 4) {direction = 1; testypos = 3;}
				else if(testypos == 5) {direction = 1; testypos = 6;}
				else if(testypos == 6) {direction = 1; testypos = 5;}
			}
			
			else {testxpos--;}
		}
		testmovePoint--;
		if(testmovePoint > 0) 
		{
			this.testpos[0] = testxpos;
			this.testpos[1] = testypos;
			if(this.Direction == 0) 
			{
				if(testmovePoint == 3) {this.directoin0sum += this.getnumRugs() * 1.0 / 6.0;}
				if(testmovePoint == 2) {this.directoin0sum += this.getnumRugs() * 2.0 / 6.0;}
				if(testmovePoint == 1) {this.directoin0sum += this.getnumRugs() * 2.0 / 6.0;}
			}
			else if(this.Direction == 1) 
			{
				if(testmovePoint == 3) {this.directoin1sum += this.getnumRugs() * 1.0 / 6.0;}
				if(testmovePoint == 2) {this.directoin1sum += this.getnumRugs() * 2.0 / 6.0;}
				if(testmovePoint == 1) {this.directoin1sum += this.getnumRugs() * 2.0 / 6.0;}
			}
			else if(this.Direction == 2) 
			{
				if(testmovePoint == 3) {this.directoin2sum += this.getnumRugs() * 1.0 / 6.0;}
				if(testmovePoint == 2) {this.directoin2sum += this.getnumRugs() * 2.0 / 6.0;}
				if(testmovePoint == 1) {this.directoin2sum += this.getnumRugs() * 2.0 / 6.0;}
			}
			else if(this.Direction == 3) 
			{
				if(testmovePoint == 3) {this.directoin3sum += this.getnumRugs() * 1.0 / 6.0;}
				if(testmovePoint == 2) {this.directoin3sum += this.getnumRugs() * 2.0 / 6.0;}
				if(testmovePoint == 1) {this.directoin3sum += this.getnumRugs() * 2.0 / 6.0;}
			}
			this.testmove(testmovePoint, direction, testxpos, testypos);
		}
		else
		{
			this.testpos[0] = testxpos;
			this.testpos[1] = testypos;
			if(this.Direction == 0) {this.directoin0sum += this.getnumRugs() * 1.0 / 6.0;}
			else if(this.Direction == 1) {this.directoin1sum += this.getnumRugs() * 1.0 / 6.0;}
			else if(this.Direction == 2) {this.directoin2sum += this.getnumRugs() * 1.0 / 6.0;}
			else if(this.Direction == 3) {this.directoin3sum += this.getnumRugs() * 1.0 / 6.0;}
		}
	}	
	
	private double getnumRugs()
	{
		Color color = this.grid.buttons[this.testpos[0]][this.testpos[1]].getBackground();
		if(game.istwoPlayer)
		{
			if(color.equals(game.getPlayerColorArray()[0]) || color.equals(game.getPlayerColorArray()[0]) || color.equals(grid.defaultColor)) {return 0.0;}
		}
		
		else
		{
			if(color.equals(game.getPlayerColor()) || color.equals(grid.defaultColor)) {return 0.0;}
		}
		
		return (double)this.game.dfs.getnumRug(this.grid.buttons[this.testpos[0]][this.testpos[1]].getBackground(), this.testpos[0], this.testpos[1]);	
	}
	
	private boolean isPossibleSetRog(int[] button1, int[] button2)
	{
		Color btn1Color = this.grid.buttons[button1[0]][button1[1]].getBackground();
		Color btn2Color = this.grid.buttons[button2[0]][button2[1]].getBackground();
		if(btn1Color.equals(this.grid.defaultColor) || btn2Color.equals(this.grid.defaultColor) || !btn1Color.equals(btn2Color)) {return true;}
		return false;
	}
	
	private int[] testSetRog(int[] testRogPos)
	{
		int numOther = 49;
		
		if(this.game.istwoPlayer)
		{
			Color color1 = this.game.getPlayerColorArray()[0];
			Color color2 = this.game.getPlayerColorArray()[1];
			for(int i=0; i<7; i++)
			{
				for(int j=0; j<7; j++)
				{
					if((j == testRogPos[0] && i == testRogPos[1]) || (j == testRogPos[2] && i == testRogPos[3]))
					{
						numOther--;
					}
					else if(this.grid.buttons[j][i].getBackground().equals(color1) || this.grid.buttons[j][i].getBackground().equals(color2)||
							this.grid.buttons[j][i].getBackground().equals(this.grid.defaultColor)){numOther--;}
				}
			}
		}
		
		else
		{
			Color color = this.game.getPlayerColor();
			for(int i=0; i<7; i++)
			{
				for(int j=0; j<7; j++)
				{
					if((j == testRogPos[0] && i == testRogPos[1]) || (j == testRogPos[2] && i == testRogPos[3]))
					{
						numOther--;
					}
					else if(this.grid.buttons[j][i].getBackground().equals(color) || this.grid.buttons[j][i].getBackground().equals(this.grid.defaultColor)){numOther--;}
				}
			}
		}
		
		if(numOther <= this.minNumOther) 
		{
			this.minNumOther = numOther;
			return testRogPos;
		}
		
		return this.rugPos;
	}
	
	private int[] setButtonsPos(int[] btn1, int[] btn2)
	{
		int[] rugpos = new int[4];
		rugpos[0] = btn1[0];
		rugpos[1] = btn1[1];
		rugpos[2] = btn2[0];
		rugpos[3] = btn2[1];
		return this.testSetRog(rugpos);
	}
}
