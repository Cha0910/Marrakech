import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GameSetting extends JPanel{
	private Frame frame;
	private int numUser;
	private int numComputer;
	GameSetting gameSetting;
	JButton[] button = new JButton[2];
	JButton[] computerButton = new JButton[2];
	
	public GameSetting(Frame f)
	{
		super();
		this.frame = f;
		this.gameSetting = this;
		int width = f.getWidth();
		int height = f.getHeight();
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		
		this.numUser = 2;
		this.numComputer = 0;
		
		this.addComponents();
	}
	
	private void addComponents()
	{
		Font font = new Font("Dialog", Font.BOLD, 14);
		JLabel[] numLabel = new JLabel[2];
		ArrayList<String> labelStrings = new ArrayList<>();
		labelStrings.add("플레이어 수");
		labelStrings.add("컴퓨터 플레이어 수");
		
		for(int i=0; i<labelStrings.size(); i++)
		{
			JLabel label = new JLabel(labelStrings.get(i));
			label.setFont(font);
			label.setForeground(Color.BLACK);
			label.setBounds((int) (this.getWidth() * 0.33), (int) (this.getHeight() * 0.23) + i*70 , 130, 20);
			this.add(label, BorderLayout.WEST);
			
			if(i == 0){ numLabel[i] = new JLabel(Integer.toString(numUser));}
			else {numLabel[i] = new JLabel(Integer.toString(numComputer));}
			numLabel[i].setFont(font);
			numLabel[i].setForeground(Color.BLACK);
			numLabel[i].setBounds((int) (this.getWidth() * 0.48), (int) (this.getHeight() * 0.23) + i*70 , 20, 20);
			this.add(numLabel[i], BorderLayout.WEST);
		}
		
		ArrayList<String> buttonStrings = new ArrayList<>();
		buttonStrings.add("-");
		buttonStrings.add("+");
		for(int i=0; i<buttonStrings.size(); i++)
		{
			button[i] = new JButton(buttonStrings.get(i));
			button[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					
					if(string.equals("-"))
					{
						numUser--;
						numButtonEnable();
						numLabel[0].setText(Integer.toString(numUser));
					}
					
					else if(string.equals("+"))
					{
						numUser++;	
						numButtonEnable();
						numLabel[0].setText(Integer.toString(numUser));
					}
				}
			});
			
			button[i].setBounds((int) (this.getWidth() * 0.53 + i*80), (int) (this.getHeight() * 0.23), 45, 20);
			this.add(button[i], BorderLayout.WEST);
		}
		
		for(int i=0; i<buttonStrings.size(); i++)
		{
			computerButton[i] = new JButton(buttonStrings.get(i));
			computerButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					
					if(string.equals("-"))
					{
						numComputer--;
						numButtonEnable();
						numLabel[1].setText(Integer.toString(numComputer));
					}
					
					else if(string.equals("+"))
					{
						numComputer++;
						numButtonEnable();
						numLabel[1].setText(Integer.toString(numComputer));
					}
				}
			});
			
			computerButton[i].setBounds((int) (this.getWidth() * 0.53 + i*80), (int) (this.getHeight() * 0.23) + 70, 45, 20);
			this.add(computerButton[i], BorderLayout.WEST);
		}
		this.numButtonEnable();
		
		buttonStrings.clear();
		buttonStrings.add("- 시작");
		buttonStrings.add("- 메인메뉴로");
		
		for(int i=0; i<buttonStrings.size(); i++)
		{
			JButton button = new JButton(buttonStrings.get(i));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					
					if(string.equals("- 시작"))
					{
						frame.push(new Game(frame, numUser, numComputer, false, null));
					}
					
					else if(string.equals("- 메인메뉴로"))
					{
						frame.push(new Menu(frame));
					}
				}
			});
			button.setBounds((int) (this.getWidth() * 0.38 + i*150), (int) (this.getHeight() * 0.23) + 130, 110, 50);
			this.add(button, BorderLayout.WEST);
		}
	}
	
	private void numButtonEnable()
	{
		button[0].setEnabled(true);
		button[1].setEnabled(true);
		computerButton[0].setEnabled(true);
		computerButton[1].setEnabled(true);
		
		if(numUser < 2) {button[0].setEnabled(false);}
		else if(numUser > 3) {button[1].setEnabled(false);}
		
		if(numComputer < 1) {computerButton[0].setEnabled(false);}
		else if(numComputer > 3) {computerButton[1].setEnabled(false);}
		
		if(numUser + numComputer < 3) {button[0].setEnabled(false); computerButton[0].setEnabled(false);}
		else if(numUser + numComputer > 3) {button[1].setEnabled(false); computerButton[1].setEnabled(false);}
	}
	
	public void paintComponent(Graphics g)
	{
		int width = (int) this.getSize().width;
		int height = (int) this.getSize().height;
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);
	}
	
	public int getnumPlayer()
	{
		return this.numComputer + this.numUser;
	}
	
	public int getnumUser()
	{
		return this.numUser;
	}
	
	public int getnumComputer()
	{
		return this.numComputer;
	}
}
