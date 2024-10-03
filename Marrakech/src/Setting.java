import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

import javax.swing.*;

public class Setting extends JPanel{
	private Frame frame;
	private Color[] color = new Color[4];
	private int delay;
	
	Properties pro = new Properties();
    Reader reader;
    String FilePath = System.getProperty("user.dir") + "\\Setting.properties";
    
	public Setting(Frame f)
	{
		super();
		this.frame = f;
		
		int width = f.getWidth();
		int height = f.getHeight();
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		
		this.load();
		this.addComponents();
	}
	
	private void saveSetting()
	{
		pro.setProperty("P1R", Integer.toString(color[0].getRed()));
		pro.setProperty("P1G", Integer.toString(color[0].getGreen()));
		pro.setProperty("P1B", Integer.toString(color[0].getBlue()));

		pro.setProperty("P2R", Integer.toString(color[1].getRed()));
		pro.setProperty("P2G", Integer.toString(color[1].getGreen()));
		pro.setProperty("P2B", Integer.toString(color[1].getBlue()));
		
		pro.setProperty("P3R", Integer.toString(color[2].getRed()));
		pro.setProperty("P3G", Integer.toString(color[2].getGreen()));
		pro.setProperty("P3B", Integer.toString(color[2].getBlue()));
		
		pro.setProperty("P4R", Integer.toString(color[3].getRed()));
		pro.setProperty("P4G", Integer.toString(color[3].getGreen()));
		pro.setProperty("P4B", Integer.toString(color[3].getBlue()));
		
		pro.setProperty("delay", Integer.toString(delay));
		
		try {
			pro.save(new FileOutputStream(FilePath), "SettingFile");
		} catch (IOException e) {
        	JOptionPane.showMessageDialog(null,"파일을 저장하던 중 오류가 발생했습니다. 파일을 저장하지 않습니다.");
        	e.printStackTrace();
        } 
	}
	
	private void load()
	{
		 try
		    {
		    	reader = new FileReader(FilePath);
		    	pro.load(reader);
		    	this.loadSetting(pro);
		    }
		    catch (FileNotFoundException e1) {
		    	JOptionPane.showMessageDialog(null,"저장된 파일이 없습니다. 기본 설정으로 설정합니다.");
		    	this.setDefault();
		    	e1.printStackTrace();
	        } catch (IOException e) {
	        	JOptionPane.showMessageDialog(null,"파일을 읽어오던 중 오류가 발생했습니다. 기본 설정으로 설정합니다.");
	        	this.setDefault();
	        	e.printStackTrace();
	        } 
	}
	   
	private void loadSetting(Properties p)
	{
		String P1R = p.getProperty("P1R");
		String P1G = p.getProperty("P1G");
		String P1B = p.getProperty("P1B");
		
		String P2R = p.getProperty("P2R");
		String P2G = p.getProperty("P2G");
		String P2B = p.getProperty("P2B");
		
		String P3R = p.getProperty("P3R");
		String P3G = p.getProperty("P3G");
		String P3B = p.getProperty("P3B");
		
		String P4R = p.getProperty("P4R");
		String P4G = p.getProperty("P4G");
		String P4B = p.getProperty("P4B");
		
		String delay = p.getProperty("delay");
		
		this.color[0] = new Color(Integer.parseInt(P1R),Integer.parseInt(P1G),Integer.parseInt(P1B));
		this.color[1] = new Color(Integer.parseInt(P2R),Integer.parseInt(P2G),Integer.parseInt(P2B));
		this.color[2] = new Color(Integer.parseInt(P3R),Integer.parseInt(P3G),Integer.parseInt(P3B));
		this.color[3] = new Color(Integer.parseInt(P4R),Integer.parseInt(P4G),Integer.parseInt(P4B));
		
		this.delay = Integer.parseInt(delay);
	}
	
	private void setDefault()
	{
		this.color[0] = new Color(255, 0, 0);
		this.color[1] = new Color(0, 0, 255);
		this.color[2] = new Color(0, 255, 0);
		this.color[3] = new Color(255, 255, 0);
		this.delay = 100;
	}
	
	private void addComponents()
	{
		Font font = new Font("Dialog", Font.BOLD, 14);
		ArrayList<String> labelStrings = new ArrayList<>();
		labelStrings.add("• 플레이어 1 색상");
		labelStrings.add("• 플레이어 2 색상");
		labelStrings.add("• 플레이어 3 색상");
		labelStrings.add("• 플레이어 4 색상");
		
		ArrayList<String> buttonStrings = new ArrayList<>();
		buttonStrings.add("- 플레이어 1 색 변경");
		buttonStrings.add("- 플레이어 2 색 변경");
		buttonStrings.add("- 플레이어 3 색 변경");
		buttonStrings.add("- 플레이어 4 색 변경");
		
		for(int i = 0; i < labelStrings.size(); i++)
		{
			JLabel label = new JLabel(labelStrings.get(i));
			label.setFont(font);
			label.setForeground(Color.BLACK);
			label.setBounds((int) (this.getWidth() * 0.15), (int) (this.getHeight() * 0.23) + i*70 , 130, 20);
			this.add(label, BorderLayout.WEST);
			
			JLabel label2 = new JLabel();
			label2.setOpaque(true);
			label2.setBackground(color[i]);
			label2.setBounds((int) (this.getWidth() * 0.28), (int) (this.getHeight() * 0.23) + i*70 , 40, 30);
			this.add(label2, BorderLayout.WEST);
			
			JTextField R = new JTextField(3);
			JTextField G = new JTextField(3);
			JTextField B = new JTextField(3);
			R.setText(Integer.toString(color[i].getRed()));
			G.setText(Integer.toString(color[i].getGreen()));
			B.setText(Integer.toString(color[i].getBlue()));
			R.setBounds((int) (this.getWidth() * 0.35), (int) (this.getHeight() * 0.23) + i*70 , 40, 30);
			G.setBounds((int) (this.getWidth() * 0.40), (int) (this.getHeight() * 0.23) + i*70 , 40, 30);
			B.setBounds((int) (this.getWidth() * 0.45), (int) (this.getHeight() * 0.23) + i*70 , 40, 30);
			this.add(R, BorderLayout.WEST);
			this.add(G, BorderLayout.WEST);
			this.add(B, BorderLayout.WEST);
			
			JButton button = new JButton(buttonStrings.get(i));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					int r,g,b;
					
					if(string.equals("- 플레이어 1 색 변경"))
					{
						r = Integer.parseInt(R.getText());
						g = Integer.parseInt(G.getText());
						b = Integer.parseInt(B.getText());	
						
						if(r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0)
						{
							JOptionPane.showMessageDialog(null,"입력 가능한 값은 0 ~ 255 입니다.\n 다시 입력해 주세요.");
						}
						
						else
						{
							Color newColor = new Color(r,g,b);
							if(newColor.equals(color[1]) || newColor.equals(color[2]) || newColor.equals(color[3]))
							{
								JOptionPane.showMessageDialog(null,"중복된 색상은 사용할 수 없습니다.\n 다시 입력해 주세요.");
							}
							
							else if((r == 0 && g == 0 && b == 0) ||(r == 255 && g == 255 && b == 255))
							{
								JOptionPane.showMessageDialog(null,"검은색과 흰색은 사용할 수 없습니다.\n 다시 입력해 주세요.");
							}
							
							else 
							{
								color[0] = newColor;
								label2.setBackground(color[0]);
							}
						}				
					}
					
					else if(string.equals("- 플레이어 2 색 변경"))
					{
						r = Integer.parseInt(R.getText());
						g = Integer.parseInt(G.getText());
						b = Integer.parseInt(B.getText());
						
						if(r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0)
						{
							JOptionPane.showMessageDialog(null,"입력 가능한 값은 0 ~ 255 입니다.\n 다시 입력해 주세요.");
						}
						
						else
						{
							Color newColor = new Color(r,g,b);
							if(newColor.equals(color[0]) || newColor.equals(color[2]) || newColor.equals(color[3]))
							{
								JOptionPane.showMessageDialog(null,"중복된 색상은 사용할 수 없습니다.\n 다시 입력해 주세요.");
							}
							else 
							{
								color[1] = newColor;
								label2.setBackground(color[1]);
							}
						}
					}
					
					else if(string.equals("- 플레이어 3 색 변경"))
					{
						r = Integer.parseInt(R.getText());
						g = Integer.parseInt(G.getText());
						b = Integer.parseInt(B.getText());
						
						if(r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0)
						{
							JOptionPane.showMessageDialog(null,"입력 가능한 값은 0 ~ 255 입니다.\n 다시 입력해 주세요.");
						}
						
						else
						{
							Color newColor = new Color(r,g,b);
							if(newColor.equals(color[0]) || newColor.equals(color[1]) || newColor.equals(color[3]))
							{
								JOptionPane.showMessageDialog(null,"중복된 색상은 사용할 수 없습니다.\n 다시 입력해 주세요.");
							}
							else 
							{
								color[2] = newColor;
								label2.setBackground(color[2]);
							}
						}
					}
					
					else if(string.equals("- 플레이어 4 색 변경"))
					{
						r = Integer.parseInt(R.getText());
						g = Integer.parseInt(G.getText());
						b = Integer.parseInt(B.getText());
						
						if(r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0)
						{
							JOptionPane.showMessageDialog(null,"입력 가능한 값은 0 ~ 255 입니다.\n 다시 입력해 주세요.");
						}
						
						else
						{
							Color newColor = new Color(r,g,b);
							if(newColor.equals(color[0]) || newColor.equals(color[1]) || newColor.equals(color[2]))
							{
								JOptionPane.showMessageDialog(null,"중복된 색상은 사용할 수 없습니다.\n 다시 입력해 주세요.");
							}
							else 
							{
								color[3] = newColor;
								label2.setBackground(color[3]);
							}
						}
					}
				}
			});
			button.setBounds((int) (this.getWidth() * 0.50), (int) (this.getHeight() * 0.23) + i*70 , 150, 30);
			this.add(button, BorderLayout.WEST);
		}
		
		JLabel label = new JLabel("• 애니메이션 딜레이");
		label.setFont(font);
		label.setForeground(Color.BLACK);
		label.setBounds((int) (this.getWidth() * 0.15), (int) (this.getHeight() * 0.23) + 300 , 130, 20);
		this.add(label, BorderLayout.WEST);
		
		JLabel delayLabel = new JLabel(Integer.toString(delay));
		delayLabel.setBounds((int) (this.getWidth() * 0.35), (int) (this.getHeight() * 0.23) + 300 , 30, 20);
		this.add(delayLabel, BorderLayout.WEST);
		
		ArrayList<String> delayButtonStrings = new ArrayList<>();
		delayButtonStrings.add("-10");
		delayButtonStrings.add("+10");
		
		for(int i = 0; i < delayButtonStrings.size(); i++)
		{
			JButton button = new JButton(delayButtonStrings.get(i));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					
					if(string.equals("-10"))
					{
						delay -= 10;
						if(delay < 0) {delay = 0;}
						delayLabel.setText(Integer.toString(delay));
					}
					
					else if(string.equals("+10"))
					{
						delay += 10;
						delayLabel.setText(Integer.toString(delay));
					}
				}
			});
			button.setBounds((int) (this.getWidth() * 0.30 + i*100), (int) (this.getHeight() * 0.23) + 300 , 55, 20);
			this.add(button, BorderLayout.WEST);
		}
		
		ArrayList<String> closeButtonStrings = new ArrayList<>();
		closeButtonStrings.add("- 저장 후 메인메뉴로");
		closeButtonStrings.add("- 저장하지 않고 메인메뉴로");
		
		for(int i = 0; i < closeButtonStrings.size(); i++)
		{
			JButton button = new JButton(closeButtonStrings.get(i));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					
					if(string.equals("- 저장 후 메인메뉴로"))
					{
						saveSetting();
						frame.push(new Menu(frame));
					}
					
					else if(string.equals("- 저장하지 않고 메인메뉴로"))
					{
						frame.push(new Menu(frame));
					}
				}
			});
			button.setBounds((int) (this.getWidth() * 0.30 + i*250), (int) (this.getHeight() * 0.23) + 400 , 200, 30);
			this.add(button, BorderLayout.WEST);
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
