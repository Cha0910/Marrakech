import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class RightPanel extends JPanel{
	private Frame frame;
	private Game game;
	private Assam assam;
	private CardLayout layout = new CardLayout();
	private JButton[] buttons;
	private JTextArea log = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(log);
	
	public RightPanel(Frame f, Grid g, Game ga, Assam a)
	{
		super();
		this.frame = f;
		this.game = ga;
		this.assam = a;
		this.addComponents();
	}
	
	public void addComponents()
	{
		int width = (int) (this.frame.getWidth() * 0.25);
		int height = (int) (this.frame.getHeight());
		this.setBounds(1000, 0, width, height);
		this.setOpaque(true);
		this.setLayout(this.layout);

		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.WHITE);
		
		String[] strings = {"▲", "◀", "▶", "▼", "주사위 굴리기"};
		int range = strings.length;
		buttons = new JButton[range];
		for (int i = 0 ; i < range ; i++)
		{
			buttons[i] = new JButton(strings[i]);
			
			if(i == 0) {buttons[i].setBounds(80, 30 , 50, 50);}
			else if(i == 1 || i == 2) {buttons[i].setBounds((i-1)*160, 95, 50, 50);}
			else if(i == 3) {buttons[i].setBounds(80, 170, 50, 50); buttons[i].setEnabled(false);}
			else {buttons[i].setBounds(0, 260, 220, 50);}
			if (strings[i].equals("주사위 굴리기"))
				buttons[i].setEnabled(false);
			buttons[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					if(string.equals("주사위 굴리기")) 
					{
						game.gameRun();
						enableRollDiceButton();
					}
					
					else
					{
						assam.setDirection(string);
						moveButtonsClick();	
					}
				}
			});
			pan.add(buttons[i]);
		}
		
		JButton menuButton = new JButton("메인 메뉴로");
		menuButton.setBounds(0, 630, 100, 50);
		menuButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(game.getServer() != null) {game.getServer().socketClose();} 
						frame.push(new Menu(frame));
					}
				});
		pan.add(menuButton);
		
		log.setEditable(false);
		scrollPane.setBounds(0, 350, 220, 250);
		pan.add(scrollPane);
		
		this.add(pan);
		this.layout.show(this, "1");
	}
	
	public void printLog(String massege)
	{
		log.append(massege + "\n");
		log.setCaretPosition(log.getDocument().getLength());
	}
	
	public void setEnableMoveButtons()
	{
		int direction = assam.getDirection();
		buttons[0].setEnabled(true);
		buttons[1].setEnabled(true);
		buttons[2].setEnabled(true);
		buttons[3].setEnabled(true);
		buttons[4].setEnabled(false);
		if(direction == 0) {buttons[3].setEnabled(false);}
		else if(direction == 1) {buttons[1].setEnabled(false);}
		else if(direction == 2) {buttons[0].setEnabled(false);}
		else if(direction == 3) {buttons[2].setEnabled(false);}
	}
	
	public void setEnd()
	{
		buttons[0].setEnabled(false);
		buttons[1].setEnabled(false);
		buttons[2].setEnabled(false);
		buttons[3].setEnabled(false);
		buttons[4].setEnabled(false);
		this.printLog("게임이 종료되었습니다.");
	}
	
	public void moveButtonsClick()
	{
		buttons[0].setEnabled(false);
		buttons[1].setEnabled(false);
		buttons[2].setEnabled(false);
		buttons[3].setEnabled(false);
		buttons[4].setEnabled(true);
	}
	
	public void enableRollDiceButton()
	{
		buttons[4].setEnabled(false);
	}
}
