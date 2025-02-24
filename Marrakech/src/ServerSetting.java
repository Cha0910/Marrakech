import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ServerSetting extends JPanel{
	private Frame frame;
	private int numPlayer;
	ServerSetting serverSetting;
	JButton[] button = new JButton[2];
	JLabel numLabel;
	Server server;
	
	public ServerSetting(Frame f)
	{
		super();
		this.frame = f;
		int width = f.getWidth();
		int height = f.getHeight();
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		this.numPlayer = 2;
		this.serverSetting = this;
		
		this.addComponents();
	}
	
	private void addComponents()
	{
		Font font = new Font("Dialog", Font.BOLD, 14);
		numLabel = new JLabel();
		ArrayList<String> labelStrings = new ArrayList<>();
		labelStrings.add("플레이어 수");
		
		JLabel label = new JLabel(labelStrings.get(0));
		label.setFont(font);
		label.setForeground(Color.BLACK);
		label.setBounds((int) (this.getWidth() * 0.33), (int) (this.getHeight() * 0.23) + 0 , 130, 20);
		this.add(label, BorderLayout.WEST);
			
		numLabel = new JLabel(Integer.toString(numPlayer));
		numLabel.setFont(font);
		numLabel.setForeground(Color.BLACK);
		numLabel.setBounds((int) (this.getWidth() * 0.48), (int) (this.getHeight() * 0.23) + 0 , 20, 20);
		this.add(numLabel, BorderLayout.WEST);
		
		
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
						numPlayer--;
						numButtonEnable();
						numLabel.setText(Integer.toString(numPlayer));
					}
					
					else if(string.equals("+"))
					{
						numPlayer++;	
						numButtonEnable();
						numLabel.setText(Integer.toString(numPlayer));
					}
				}
			});
			
			button[i].setBounds((int) (this.getWidth() * 0.53 + i*80), (int) (this.getHeight() * 0.23), 45, 20);
			this.add(button[i], BorderLayout.WEST);
		}
		this.numButtonEnable();
		
		buttonStrings.clear();
		buttonStrings.add("- 서버 생성");
		buttonStrings.add("- 메인메뉴로");
		
		for(int i=0; i<buttonStrings.size(); i++)
		{
			JButton button = new JButton(buttonStrings.get(i));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					
					if(string.equals("- 서버 생성"))
					{
						server = new Server(frame, serverSetting);
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
		
		if(numPlayer < 3) {button[0].setEnabled(false);}
		else if(numPlayer > 3) {button[1].setEnabled(false);}
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
		return this.numPlayer;
	}
}
