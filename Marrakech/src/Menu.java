import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class Menu extends JPanel{
	private Frame frame;
	
	public Menu(Frame f)
	{
		super();
		this.frame = f;
		
		int width = f.getWidth();
		int height = f.getHeight();
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		
		this.addComponents();
	}
	private void addComponents()
	{
		Font font = new Font("Dialog", Font.BOLD, 14);
		ArrayList<String> labelStrings = new ArrayList<>();
		labelStrings.add("• 오프라인 플레이");
		labelStrings.add("• 온라인 플레이");
		for(int i = 0; i < labelStrings.size(); i++)
		{
			JLabel label = new JLabel(labelStrings.get(i));
			label.setFont(font);
			label.setForeground(Color.BLACK);
			label.setBounds((int) (this.getWidth() * 0.45), (int) (this.getHeight() * 0.23) + i*70 , 200, 20);
			this.add(label, BorderLayout.CENTER);
		}

		ArrayList<String> buttonStrings = new ArrayList<>();
		buttonStrings.add("- 게임 시작");
		buttonStrings.add("- 게임 생성");
		buttonStrings.add("- 게임 참여");
		buttonStrings.add("- 환경설정");
		buttonStrings.add("- 종료");
		
		for (int i = 0 ; i < buttonStrings.size() ; i++)
		{
			JButton button = new JButton(buttonStrings.get(i));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					if (string.equals("- 게임 시작"))
					{
						frame.push(new GameSetting(frame));
					}
					
					else if(string.equals("- 게임 생성"))
					{
						frame.push(new ServerSetting(frame));
					}
					
					else if(string.equals("- 게임 참여"))
					{
						new Client(frame);
					}
					
					else if(string.equals("- 환경설정"))
					{
						frame.push(new Setting(frame));
					}
					
					else if (string.equals("- 종료"))
					{
						frame.dispose();
						System.exit(0);	
					}
				}
			});
			
			if(i == 0)
			{
				button.setBounds((int) (this.getWidth() * 0.46), 200+30*i, 100, 30);
			}
			else if(i < 3)
			{
				button.setBounds((int) (this.getWidth() * 0.46), 240+30*i, 100, 30);
			}
			else
			{
				button.setBounds((int) (this.getWidth() * 0.45), 240+35*i, 100, 30);
			}
			this.add(button, BorderLayout.CENTER);
		}
	}


	public void paintComponent(Graphics g)
	{
		int width = (int) this.getSize().width;
		int height = (int) this.getSize().height;
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);
	}
}
