import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.io.*;
import javax.imageio.*;

public class Grid extends JPanel{
	private Frame frame;
	private Game game;
	private JPanel panel;
	JButton[][] buttons = new JButton[7][7];
	private int xpos1 = -1, ypos1 = -1, xpos2 = -1, ypos2 = -1;
	private boolean isPossible = false;
	boolean isRugPossible;
	public Color defaultColor;
	
	public Grid(Game g, Frame f)
	{
		super();
		this.frame = f;
		this.game = g;
		this.setBounds(0, 0, f.getWidth(), f.getHeight());
		this.setLayout(null);
		this.setGridButtons();
		this.add(panel);
	}
	
	public void paintComponent(Graphics g)
	{
		int width = (int) this.getSize().width;
		int height = (int) this.getSize().height;
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);
		try
		{
			File path = new File("./Images/Background.png");
			Image img = ImageIO.read(path);
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			g2.drawImage(img, 0, 0, width-579, height-39, this);
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	private void setGridButtons()
	{
		this.panel = new JPanel();
		this.panel.setBounds(79, 79, frame.getWidth()-740, frame.getHeight()-200);
		this.panel.setLayout(new GridLayout(7,7));
		for(int i=0; i<7; i++)
		{
			for(int j=0; j<7; j++)
			{
				buttons[j][i] = new JButton();
				buttons[j][i].setName(Integer.toString(j) + "-" + Integer.toString(i));
				buttons[j][i].setBackground(null);
				buttons[j][i].addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent e) 
					{
						if(xpos1 == -1 || ypos1 == -1 || xpos2 == -1 || ypos2 == -1) {}
						
						else
						{
							setColorButtons();
							if(isPossible) 
							{
								setDisableButtons();
								game.gameRun2();
							}
						}
					}

					public void mousePressed(MouseEvent e) {}
					public void mouseReleased(MouseEvent e) {}

					public void mouseEntered(MouseEvent e) 
					{
						JButton b = (JButton)e.getSource();
						String[] pos = b.getName().split("-");
						int xpos = Integer.parseInt(pos[0]);
						int ypos = Integer.parseInt(pos[1]);
						setBorder(xpos, ypos);
					}

					public void mouseExited(MouseEvent e) {}
				});
				buttons[j][i].setEnabled(false);
				this.panel.add(buttons[j][i]);
			}
		}
		this.defaultColor = buttons[0][0].getBackground();
	}
	
	public void setEnableButtons(Assam assam)
	{
		int xpos = assam.getxpos();
		int ypos = assam.getypos();
		Border border = new LineBorder(Color.WHITE, 3);
		this.isRugPossible = false;
		Color color = this.defaultColor;
		
		if(ypos-1 >= 0)
		{
			buttons[xpos][ypos-1].setEnabled(true);
			buttons[xpos][ypos-1].setBorder(border);
			if(buttons[xpos][ypos-1].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
			else if(!this.isRugPossible)
			{
				if(color.equals(defaultColor)) {color = buttons[xpos][ypos-1].getBackground();}
				else if(!color.equals(buttons[xpos][ypos-1].getBackground())) {this.isRugPossible = true;}
			}
			
			if(ypos-2 >= 0)
			{
				buttons[xpos][ypos-2].setEnabled(true);
				buttons[xpos][ypos-2].setBorder(border);
				if(buttons[xpos][ypos-2].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
				else if(!this.isRugPossible)
				{
					if(color.equals(defaultColor)) {color = buttons[xpos][ypos-2].getBackground();}
					else if(!color.equals(buttons[xpos][ypos-2].getBackground())) {this.isRugPossible = true;}
				}
			}
		}
		
		if(ypos+1 <= 6)
		{
			buttons[xpos][ypos+1].setEnabled(true);
			buttons[xpos][ypos+1].setBorder(border);
			if(buttons[xpos][ypos+1].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
			else if(!this.isRugPossible)
			{
				if(color.equals(defaultColor)) {color = buttons[xpos][ypos+1].getBackground();}
				else if(!color.equals(buttons[xpos][ypos+1].getBackground())) {this.isRugPossible = true;}
			}
			if(ypos+2 <= 6) 
			{
				buttons[xpos][ypos+2].setEnabled(true);
				buttons[xpos][ypos+2].setBorder(border);
				if(buttons[xpos][ypos+2].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
				else if(!this.isRugPossible)
				{
					if(color.equals(defaultColor)) {color = buttons[xpos][ypos+2].getBackground();}
					else if(!color.equals(buttons[xpos][ypos+2].getBackground())) {this.isRugPossible = true;}
				}
			}
		}
		
		if(xpos-1 >= 0)
		{
			buttons[xpos-1][ypos].setEnabled(true);
			buttons[xpos-1][ypos].setBorder(border);
			if(buttons[xpos-1][ypos].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
			else if(!this.isRugPossible)
			{
				if(color.equals(defaultColor)) {color = buttons[xpos-1][ypos].getBackground();}
				else if(!color.equals(buttons[xpos-1][ypos].getBackground())) {this.isRugPossible = true;}
			}
			if(xpos-2 >= 0) 
			{
				buttons[xpos-2][ypos].setEnabled(true);
				buttons[xpos-2][ypos].setBorder(border);
				if(buttons[xpos-2][ypos].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
				else if(!this.isRugPossible)
				{
					if(color.equals(defaultColor)) {color = buttons[xpos-2][ypos].getBackground();}
					else if(!color.equals(buttons[xpos-2][ypos].getBackground())) {this.isRugPossible = true;}
				}
			}
		}
		
		if(xpos+1 <= 6)
		{
			buttons[xpos+1][ypos].setEnabled(true);
			buttons[xpos+1][ypos].setBorder(border);
			if(buttons[xpos+1][ypos].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
			else if(!this.isRugPossible)
			{
				if(color.equals(defaultColor)) {color = buttons[xpos+1][ypos].getBackground();}
				else if(!color.equals(buttons[xpos+1][ypos].getBackground())) {this.isRugPossible = true;}
			}
			if(xpos+2 <= 6) 
			{
				buttons[xpos+2][ypos].setEnabled(true);
				buttons[xpos+2][ypos].setBorder(border);
				if(buttons[xpos+2][ypos].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
				else if(!this.isRugPossible)
				{
					if(color.equals(defaultColor)) {color = buttons[xpos+2][ypos].getBackground();}
					else if(!color.equals(buttons[xpos+2][ypos].getBackground())) {this.isRugPossible = true;}
				}
			}
		}
		
		if(xpos-1 >= 0 && ypos-1 >= 0)
		{
			buttons[xpos-1][ypos-1].setEnabled(true);
			buttons[xpos-1][ypos-1].setBorder(border);
			if(buttons[xpos-1][ypos-1].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
			else if(!this.isRugPossible)
			{
				if(color.equals(defaultColor)) {color = buttons[xpos-1][ypos-1].getBackground();}
				else if(!color.equals(buttons[xpos-1][ypos-1].getBackground())) {this.isRugPossible = true;}
			}
		}
		
		if(xpos+1 <= 6 && ypos-1 >= 0)
		{
			buttons[xpos+1][ypos-1].setEnabled(true);
			buttons[xpos+1][ypos-1].setBorder(border);
			if(buttons[xpos+1][ypos-1].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
			else if(!this.isRugPossible)
			{
				if(color.equals(defaultColor)) {color = buttons[xpos+1][ypos-1].getBackground();}
				else if(!color.equals(buttons[xpos+1][ypos-1].getBackground())) {this.isRugPossible = true;}
			}
		}
		
		if(xpos-1 >= 0 && ypos+1 <= 6)
		{
			buttons[xpos-1][ypos+1].setEnabled(true);
			buttons[xpos-1][ypos+1].setBorder(border);
			if(buttons[xpos-1][ypos+1].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
			else if(!this.isRugPossible)
			{
				if(color.equals(defaultColor)) {color = buttons[xpos-1][ypos+1].getBackground();}
				else if(!color.equals(buttons[xpos-1][ypos+1].getBackground())) {this.isRugPossible = true;}
			}
		}
		
		if(xpos+1 <= 6 && ypos+1 <= 6)
		{
			buttons[xpos+1][ypos+1].setEnabled(true);
			buttons[xpos+1][ypos+1].setBorder(border);
			if(buttons[xpos+1][ypos+1].getBackground().equals(defaultColor)) {this.isRugPossible = true;}
			else if(!this.isRugPossible)
			{
				if(color.equals(defaultColor)) {color = buttons[xpos+1][ypos+1].getBackground();}
				else if(!color.equals(buttons[xpos+1][ypos+1].getBackground())) {this.isRugPossible = true;}
			}
		}
	}
	
	public void setDisableButtons()
	{
		Border border = new LineBorder(Color.GRAY, 1);
		xpos1 = -1; ypos1 = -1;
		xpos2 = -1; ypos2 = -1;
		this.isPossible = false;
		for(int i=0; i<7; i++)
		{
			for(int j=0; j<7; j++)
			{
				buttons[j][i].setEnabled(false);
				buttons[j][i].setBorder(border);
			}
		}
	}
	
	private void setColorButtons()
	{
		Color color = game.getPlayerColor();
		
		if(this.game.istwoPlayer)
		{
			if(this.game.getPlayer().getRugs() < 13)
			{
				color = this.game.getPlayerColorArray()[1];
			}
		}
		
		int xabs = Math.abs(xpos1 - xpos2);
		int yabs = Math.abs(ypos1 - ypos2);
		
		Color button1Color = buttons[xpos1][ypos1].getBackground();
		Color button2Color = buttons[xpos2][ypos2].getBackground();
		
		if(!(xabs == 0 && yabs == 1) && !(xabs == 1 && yabs == 0)) 
		{
			JOptionPane.showMessageDialog(null,"양탄자는 이어져있어야 합니다.");
		}
		
		else if(button1Color.equals(button2Color) && !button1Color.equals(this.defaultColor))
		{
			JOptionPane.showMessageDialog(null,"같은 색상의 양탄자 위에는 설치할 수 없습니다.");
		}
		
		else
		{
			buttons[xpos1][ypos1].setBackground(color);
			buttons[xpos2][ypos2].setBackground(color);
			this.isPossible = true;
		}
		
	}
	
	public void computerSetColorButtons(int[] buttonsPos)
	{
		Color color = game.getPlayerColor();
		
		if(this.game.istwoPlayer)
		{
			if(this.game.getPlayer().getRugs() < 13)
			{
				color = this.game.getPlayerColorArray()[1];
			}
		}
		
		int xabs = Math.abs(buttonsPos[0] - buttonsPos[2]);
		int yabs = Math.abs(buttonsPos[1] - buttonsPos[3]);
		
		Color button1Color = buttons[buttonsPos[0]][buttonsPos[1]].getBackground();
		Color button2Color = buttons[buttonsPos[2]][buttonsPos[3]].getBackground();
		
		if(!(xabs == 0 && yabs == 1) && !(xabs == 1 && yabs == 0)) 
		{
			JOptionPane.showMessageDialog(null,"양탄자는 이어져있어야 합니다.");
		}
		
		else if(button1Color.equals(button2Color) && !button1Color.equals(this.defaultColor))
		{
			JOptionPane.showMessageDialog(null,"같은 색상의 양탄자 위에는 설치할 수 없습니다.");
		}
		
		buttons[buttonsPos[0]][buttonsPos[1]].setBackground(color);
		buttons[buttonsPos[2]][buttonsPos[3]].setBackground(color);
		this.isPossible = true;

		
	}
	
	private void setBorder(int xpos, int ypos)
	{
		Border playerBorder = new LineBorder(Color.BLACK, 3);
		Border defaultBorder = new LineBorder(Color.WHITE, 3);
		
		if(buttons[xpos][ypos].isEnabled())
		{
			if(xpos1 == -1 && ypos1 == -1)
			{
				xpos1 = xpos;
				ypos1 = ypos;
				buttons[xpos1][ypos1].setBorder(playerBorder);
			}
			
			else if(xpos2 == -1 || ypos2 == -1)
			{
				xpos2 = xpos;
				ypos2 = ypos;
				buttons[xpos2][ypos2].setBorder(playerBorder);
			}
			
			else if(xpos2 != -1 && ypos != -1)
			{
				buttons[xpos1][ypos1].setBorder(defaultBorder);
				xpos1 = xpos2;
				ypos1 = ypos2;
				xpos2 = xpos;
				ypos2 = ypos;
				buttons[xpos2][ypos2].setBorder(playerBorder);
			}
		}
	}
	
	public int Count(Color color)
	{
		int num = 0;
		for(int i=0; i<7; i++)
		{
			for(int j=0; j<7; j++)
			{
				if(buttons[j][i].getBackground().equals(color)) {num++;}
			}
		}
		return num;
	}
}
