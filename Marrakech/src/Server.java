import java.net.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class Server extends JFrame{
	private Socket socket = null;                
	private ServerSocket serverSocket = null;
	private Frame frame;
	private ServerSetting servetSetting;
    private JTextArea log = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(log);
	private JButton[] buttons = new JButton[3];
	private Server server;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	JLabel[] numLabel;
    int numPlayer;
    int numUser;
    int numComputer;
    
    public Server(Frame f,ServerSetting ss)
    {
    	this.frame = f;
    	this.setSize(600,300);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.servetSetting = ss;
		this.numPlayer = this.servetSetting.getnumPlayer();
		this.numUser = 1;
		this.numComputer = numPlayer - numUser;
		this.setLayout(null);
		this.setLocationRelativeTo(this.frame);
		this.getContentPane().setBackground(Color.WHITE);
		this.addComponents();
		this.setServer();
		this.server = this;
    }
    
    private void addComponents()
    {
    	log.setEditable(false);
		scrollPane.setBounds(30, 30, 220, 200);
		this.add(scrollPane, BorderLayout.WEST);
		
		Font font = new Font("Dialog", Font.BOLD, 14);
		numLabel = new JLabel[2];
		ArrayList<String> labelStrings = new ArrayList<>();
		labelStrings.add("���� �÷��̾� ��");
		labelStrings.add("���� �� �÷��̾� ��");
		
		for(int i=0; i<labelStrings.size(); i++)
		{
			JLabel label = new JLabel(labelStrings.get(i));
			label.setFont(font);
			label.setForeground(Color.BLACK);
			label.setBounds((int) (this.getWidth() * 0.49), (int) (this.getHeight() * 0.13) + i*70 , 150, 20);
			this.add(label, BorderLayout.WEST);
			
			if(i == 0){ numLabel[i] = new JLabel(Integer.toString(numUser));}
			else {numLabel[i] = new JLabel(Integer.toString(numPlayer));}
			numLabel[i].setFont(font);
			numLabel[i].setForeground(Color.BLACK);
			numLabel[i].setBounds((int) (this.getWidth() * 0.77), (int) (this.getHeight() * 0.13) + i*70 , 20, 20);
			this.add(numLabel[i], BorderLayout.WEST);
		}
		
		ArrayList<String> buttonStrings = new ArrayList<>();
		buttonStrings.add("- ����");
		buttonStrings.add("- ���");
		buttonStrings.add("- ���θ޴���");
		
		for(int i=0; i<buttonStrings.size(); i++)
		{
			buttons[i] = new JButton(buttonStrings.get(i));
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String string = e.getActionCommand();
					
					if(string.equals("- ����"))
					{
						if(socket == null)
						{
							frame.push(new Game(frame, numUser, numComputer, true, server));
						}
						
						else
						{
							writeClient("start" + "-" +  numUser + "-" + numComputer);
							frame.push(new Game(frame, numUser, numComputer, true, server));
						}
						dispose();
					}
					
					else if(string.equals("- ���"))
					{
						waitClient();
					}
					
					else if(string.equals("- ���θ޴���"))
					{
						frame.push(new Menu(frame));
						socketClose();
						dispose();
					}
				}
			});
			
			if(i==2){buttons[i].setBounds((int) (this.getWidth() * 0.45 + i*80), (int) (this.getHeight() * 0.17) + 130,110, 50);}
			else {buttons[i].setBounds((int) (this.getWidth() * 0.45 + i*80), (int) (this.getHeight() * 0.17) + 130, 70, 50);}
			
			this.add(buttons[i], BorderLayout.WEST);
		}
    }
    
    private void setServer()
    {
    	try{
    		this.serverSocket = new ServerSocket(8888);
    		log.append("������ �����Ǿ����ϴ�.\n");
    	}
    	catch(IOException e)
        {
            System.out.println("�ش� ��Ʈ�� �����ֽ��ϴ�.");
        }
    }
    
    private void waitClient()
    {
    	try{
    			this.socket = this.serverSocket.accept();
        		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        		this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        		
        		String str = in.readLine();
        		if(str.equals("conneting"))
        		{
        			log.append("�÷��̾ �����߽��ϴ�.\n");
            		this.numUser++;
            		this.numComputer--;
            		this.numLabel[0].setText(Integer.toString(this.numUser));
            		if(numUser == numPlayer) {buttons[1].setEnabled(false);}
            		out.write("����Ǿ����ϴ�.\n" + "\n");
            		out.flush();
        		}
        }catch(IOException e)
        {
            System.out.println("�ش� ��Ʈ�� �����ֽ��ϴ�.");}	
    }	
    
    private void writeClient(String str)
    {
    	try{
			this.socket = this.serverSocket.accept();
    		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    		out.write(str + "\n");
    		out.flush();
    }catch(IOException e)
    {
        System.out.println("������ �߻��߽��ϴ�.");}
    }
    
    public void socketClose()
    {
    	try {
			this.serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}


