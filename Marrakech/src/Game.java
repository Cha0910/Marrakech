import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.Random;

public class Game extends JPanel{
	private Frame frame;
	private Grid grid;
	private Assam assam;
	private Dice dice;
	private JPanel info;
	private RightPanel rightPanel;
	private Player[] player;
	private Computer computer;
	private int turnPlayer;
	private Color[] color = new Color[4];
	private int delay;
	private int numPlayer;
	private int numUser;
	private int numComputer;
	private int noRugPlayer;
	private Server server;
	public boolean istwoPlayer;
	private boolean isOnline;
	public DFS dfs;
	
	public Game(Frame f, int u, int c, boolean o, Server s)
	{
		super();
		this.frame = f;
		this.numPlayer = u + c;
		this.numUser = u;
		this.numComputer = c;
		this.isOnline = o;
		
		this.server = s;
		this.grid = new Grid(this, frame);
		this.assam = new Assam(grid);
		this.dfs = new DFS(this.grid);
		this.dice = new Dice();
		this.rightPanel = new RightPanel(frame, grid, this, assam);
		this.computer = new Computer(this, this.grid, this.assam);
		this.setBounds(0, 0, f.getWidth(), f.getHeight());
		this.setLayout(null);
		this.load();
		this.addPlayer();
		this.setInfo();
		this.add(info);
		this.add(rightPanel);
		this.add(grid);
		this.rightPanel.printLog("플레이어 " + Integer.toString(this.turnPlayer+1) + "의 차례입니다.");
	}
	
	private void addPlayer()
	{
		this.noRugPlayer = 0;
		player = new Player[numPlayer];
		int rugs = 24;
		if(numPlayer == 2)
		{
			for (int i = 0 ; i < numUser ; i++)
			{
				Color[] colorArray = {color[i], color[i+2]};
				player[i] = new Player(i, rugs, colorArray, false);
			}
			
			for (int i = numUser; i < this.numPlayer ; i++)
			{
				Color[] colorArray = {color[i], color[i+2]};
				player[i] = new Player(i, rugs, colorArray, true);
			}
			this.istwoPlayer = true;
		}
		else
		{
			if (numPlayer == 3) {rugs = 15;}
			else if (numPlayer == 4) {rugs = 12;}
			for (int i = 0 ; i < numUser ; i++)
			{
				player[i] = new Player(i, rugs, color[i], false);
			}
			
			for (int i = numUser; i < this.numPlayer ; i++)
			{
				player[i] = new Player(i, rugs, color[i], true);
			}
		}
		Random rand = new Random();
		this.turnPlayer = rand.nextInt(numUser);
	}
	
	private void ComputerRun()
	{
		this.assam.setDirection(this.computer.selectDirection());
		this.rightPanel.moveButtonsClick();
		this.rightPanel.enableRollDiceButton();
		this.gameRun();
	}
	
	private void ComputerRun2()
	{
		this.grid.computerSetColorButtons(this.computer.setRugPos());
		this.grid.setDisableButtons();
		this.gameRun2();
	}
	
	private void load()
	{
		Properties pro = new Properties();
	    Reader reader;
	    String FilePath = System.getProperty("user.dir") + "\\Setting.properties";
	    
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
	
	private void setInfo()
	{
		this.info = new JPanel();
		this.info.setBackground(Color.white);
		this.info.setLayout(new GridLayout(32, 1));
		this.info.setBounds(800, 40, 200, frame.getHeight());
		this.info.removeAll();
        
		for (Player p : this.player)
		{
			int num = p.getIndex()+1;
			String[] strings = {"플레이어 "+num, "양탄자 : "+p.getRugs(), "보드에 있는 양탄자 : " +p.getgridRugs(), "1 디르함 : " +p.getCoin1(), "5 디르함 : "+p.getCoin5(), "디르함의 합 : "+p.getCoin()};
			for (int i = 0 ; i < strings.length ; i++)
			{
				JLabel label = new JLabel(strings[i]);
				label.setForeground(Color.BLACK);
				label.setFont(new Font("Serif", Font.PLAIN, 15));
				if (i == 0)
				{
					label.setForeground(new Color(p.getColor().getRGB()));
					label.setFont(new Font("Serif", Font.BOLD, 15));
				}
				this.info.add(label);
			}
			this.info.add(new JLabel());
		}	
		this.add(info);
		this.info.repaint();
	}
	
	private void changePlayer()
	{
		this.turnPlayer++;
		if(this.turnPlayer > this.numPlayer-1) {this.turnPlayer = 0;}
		if(this.player[this.turnPlayer].getisOut())
		{
			this.changePlayer();
		}
		
		else
		{
			this.rightPanel.printLog("\n플레이어 " + (this.player[this.turnPlayer].getIndex()+1) + "의 차례입니다.");
			this.rightPanel.setEnableMoveButtons();
		}
	}
	
	private void playerOut()
	{
		 this.player[this.turnPlayer].setOut();
		 this.numPlayer--;
		 this.rightPanel.printLog("플레이어 " + (this.player[this.turnPlayer].getIndex()+1) + "이(가) 탈락했습니다.");
	}
	
	private boolean isEnd()
	{
		if(this.numPlayer == 1)
		{
			this.rightPanel.printLog("플레이어 " + (this.player[this.turnPlayer].getIndex()+1) + "이(가) 승리했습니다.");
			return true;
		}
		
		else if(this.noRugPlayer >= this.numPlayer)
		{
			int max = -1;
			int num = -1;
			for (Player p : this.player)
			{
				if(p.getisOut()) {continue;}
				int sum = p.getCoin() + p.getgridRugs();	
				if(sum > max) {num = p.getIndex(); max = sum;}
				else if(sum == max) {if(p.getCoin() > this.player[num].getCoin()) {num = p.getIndex();}}
			}	
			this.rightPanel.printLog("플레이어 " + (this.player[num].getIndex()+1) + "이(가) 승리했습니다.");
			return true;
		}
		
		return false;
	}
	
	private void CalcRug(int x, int y)
	{
		Color color = this.grid.buttons[x][y].getBackground();
		if(this.istwoPlayer) 
		{
			if(color.equals(this.getPlayerColorArray()[0]) || color.equals(this.getPlayerColorArray()[1])) {return;}
			for(int i=0; i<this.player.length; i++)
			{
				if(color.equals(this.player[i].getColorArray()[0]) || color.equals(this.player[i].getColorArray()[1]))
				{
					int numRug = this.dfs.getnumRug(color, x, y);
					int playerCoin = this.player[this.turnPlayer].getCoin();
					if(numRug > playerCoin) {numRug = playerCoin;}
					int numCoin[] = this.player[this.turnPlayer].subCoin(numRug);
					this.player[i].addCoin(numCoin);
					if(numCoin[2] < 0) {this.RedistributionCoin();}
					this.rightPanel.printLog("플레이어 " + (this.player[this.turnPlayer].getIndex()+1) + "이(가) 플레이어 " + (this.player[i].getIndex()+1) + "에게 \n" + numRug + "디르함을 지불했습니다.");
					if(this.player[this.turnPlayer].getCoin() == 0) {this.playerOut();}
					return;
				}
			}
		}
		
		else
		{
			if(color.equals(this.getPlayerColor())) {return;}
			for(int i=0; i<this.player.length; i++)
			{
				if(color.equals(player[i].getColor()))
				{
					int numRug = this.dfs.getnumRug(color, x, y);
					int playerCoin = this.player[this.turnPlayer].getCoin();
					if(numRug > playerCoin) {numRug = playerCoin;}
					int numCoin[] = this.player[this.turnPlayer].subCoin(numRug);
					this.player[i].addCoin(numCoin);
					if(numCoin[2] < 0) {this.RedistributionCoin();}
					this.rightPanel.printLog("플레이어 " + (this.player[this.turnPlayer].getIndex()+1) + "이(가) 플레이어 " + (this.player[i].getIndex()+1) + "에게 \n" + numRug + "디르함을 지불했습니다.");
					if(this.player[this.turnPlayer].getCoin() == 0) {this.playerOut();}
					return;
				}
			}
		}
	}
	
	private void RedistributionCoin()
	{
		int numCoin5 = this.player.length * 5;
		
		for (Player p : this.player)
		{
			int coin = p.getCoin();
			int coin5 = coin / 5;
			int coin1 = coin % 5;
			if(coin5 > numCoin5)
			{
				int addCoin = coin5 - numCoin5;
				coin5 = numCoin5;
				coin1 += addCoin*5;
			}
			p.setCoin(coin5, coin1);
			numCoin5 -= coin5;
		}	
	}
	
	private void setgridRugs()
	{
		if(this.istwoPlayer) {for (Player p : this.player){p.setgridRugs(this.grid.Count(p.getColorArray()[0]) + this.grid.Count(p.getColorArray()[1])); }}
		else {for (Player p : this.player){p.setgridRugs(this.grid.Count(p.getColor())); }	}
	}
	
	public void gameRun()
	{
		int movePoint = this.dice.rollDice();
		this.rightPanel.printLog("주사위의 눈금이 " + movePoint + " 나왔습니다.");
		this.assam.move(movePoint, this.delay);
		this.CalcRug(this.assam.getxpos(), this.assam.getypos());
		if(this.player[this.turnPlayer].getisOut()) {this.grid.setDisableButtons(); this.gameRun2();}
		else if(!this.grid.isRugPossible) {this.grid.setDisableButtons();  this.rightPanel.printLog("양탄자를 설치 할 수 없습니다. \n다음 플레이어로 턴을 넘깁니다.");  this.gameRun2();}
		else if(this.player[this.turnPlayer].isComputer()) {this.ComputerRun2();}
		this.setInfo();
	}
	
	public void gameRun2()
	{
		player[this.turnPlayer].subRugs();
		this.setgridRugs();
		if(this.player[this.turnPlayer].getRugs() == 0) {this.noRugPlayer++;}
		this.changePlayer();
		if(this.isEnd()) {this.rightPanel.setEnd();}
		else if(this.player[this.turnPlayer].isComputer()) {this.ComputerRun();}
		this.setInfo();
	}
	
	public Color getPlayerColor()
	{
		return this.player[this.turnPlayer].getColor();
	}
	
	public Color[] getPlayerColorArray()
	{
		return this.player[this.turnPlayer].getColorArray();
	}
	
	public Player getPlayer()
	{
		return this.player[this.turnPlayer];
	}
	
	public boolean getisOnlie()
	{
		return this.isOnline;
	}
	
	public Server getServer()
	{
		return this.server;
	}
}
