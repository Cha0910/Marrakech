import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Client extends JFrame{
	private Frame frame;
	JButton[] buttons = new JButton[2];
	private JTextArea log = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(log);
	private Socket socket;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private JTextArea IP;
	private JButton[] button = new JButton[2];;
	JLabel numLabel;
	Server server;
	
	public Client(Frame f)
	{
		super();
		this.setSize(600, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame = f;
		this.setLayout(null);
		this.setLocationRelativeTo(this.frame);
		this.getContentPane().setBackground(Color.WHITE);
		this.addComponents();
	}
	
	private void addComponents()
	{
		log.setEditable(false);
		scrollPane.setBounds(30, 30, 220, 200);
		this.add(scrollPane, BorderLayout.WEST);
		
		IP = new JTextArea();
		IP.setBounds((int) (this.getWidth() * 0.5 + 55), (int) (this.getHeight() * 0.23), 200, 20);
		Border border = new LineBorder(Color.GRAY);
		IP.setBorder(border);
		this.add(IP, BorderLayout.WEST);
		
		Font font = new Font("Dialog", Font.BOLD, 14);
		numLabel = new JLabel();
		ArrayList<String> labelStrings = new ArrayList<>();
		labelStrings.add("서버 IP");
		
		JLabel label = new JLabel(labelStrings.get(0));
		label.setFont(font);
		label.setForeground(Color.BLACK);
		label.setBounds((int) (this.getWidth() * 0.5), (int) (this.getHeight() * 0.23), 50, 20);
		this.add(label, BorderLayout.WEST);
		
		
		ArrayList<String> buttonStrings = new ArrayList<>();
		buttonStrings.add("- 접속");
		buttonStrings.add("- 메인메뉴로");
		
		for(int i=0; i<buttonStrings.size(); i++)
		{
			button[i] = new JButton(buttonStrings.get(i));
			button[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					
					if(string.equals("- 접속"))
					{
						if(connect()) {waitStart();}
							
						else{button[0].setEnabled(true);}
					}
					
					else if(string.equals("- 메인메뉴로"))
					{
						frame.push(new Menu(frame));
						dispose();
					}
				}
			});
			button[i].setBounds((int) (this.getWidth() * 0.5 + i*150), (int) (this.getHeight() * 0.5), 110, 50);
			this.add(button[i], BorderLayout.WEST);
		}
	}
	
	private boolean connect()
	{
		this.log.append("접속을 시도합니다.\n");
		try
		{
			this.socket = new Socket(this.IP.getText(), 8888);
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			out.write("conneting\n");
			out.flush();
			String inputMessage = in.readLine();
			this.log.append(inputMessage);	
			return true;
				
		}catch (IOException e) {
         this.log.append("해당 IP가 없거나 오류가 발생했습니다.\n다시 입력해주세요\n");
         button[0].setEnabled(true);
         }finally {
        	 try {
        		 if(socket != null) {socket.close();}
        	 }catch (IOException e) {
        	         this.log.append("해당 IP가 없거나 오류가 발생했습니다. 다시 입력해주세요\n");
        	         }
         }
		return false;
	}
	
	private void waitStart()
	{
		try
		{
			String startMessage[];
			while(true)
			{
				this.socket = new Socket(this.IP.getText(), 8888);
				this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				String inputMessage = in.readLine();
				startMessage = inputMessage.split("-");
				if(startMessage[0].equals("start")) {break;}
			}
			
			frame.push(new Game(frame, Integer.parseInt(startMessage[1]), Integer.parseInt(startMessage[2]), true, null));
			dispose();
		}catch (IOException e) {
         this.log.append("오류가 발생했습니다.\n");
         }
	}
}
