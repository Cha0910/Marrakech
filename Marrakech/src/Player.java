import java.awt.*;

public class Player {
	Color color;
	private int index;
	private int rugs;
	private int coin5;
	private int coin1;
	private int coin;
	private int gridRugs;
	private boolean isOut;
	private boolean isComputer;
	Color[] colorArray;
	
	public Player(int i, int r, Color co, boolean com)
	{
		this.index = i;
		this.rugs = r;
		this.coin1 = 5;
		this.coin5 = 5;
		this.coin = coin1 + coin5*5;
		this.color = co;
		this.gridRugs = 0;
		this.isOut = false;
		this.isComputer = com;
	}
	
	public Player(int i, int r, Color co[], boolean com)
	{
		this.index = i;
		this.rugs = r;
		this.coin1 = 5;
		this.coin5 = 5;
		this.coin = coin1 + coin5*5;
		this.color = co[0];
		this.colorArray = co;
		this.gridRugs = 0;
		this.isOut = false;
		this.isComputer = com;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public Color[] getColorArray()
	{
		return this.colorArray;
	}

	public int getRugs()
	{
		return this.rugs;
	}

	public void subRugs()
	{
		this.rugs--;
	}

	public int getIndex()
	{
		return this.index;
	}
	
	public int getCoin1()
	{
		return this.coin1;
	}
	
	public int getCoin5()
	{
		return this.coin5;
	}
	
	public void setCoin(int coin5, int coin1)
	{
		this.coin5 = coin5;
		this.coin1 = coin1;
		this.coin = coin5*5 + coin1;
	}
	public int getCoin()
	{
		return this.coin;
	}
	
	public int[] subCoin(int coin)
	{
		int num5 = coin / 5;
		int num1 = coin % 5;
		
		if(this.coin == coin)
		{
			num5 = this.coin5;
			num1 = this.coin1;
			this.coin5 = 0;
			this.coin1 = 0;
		}
		
		else
		{
			if(num5 > this.coin5)
			{
				num5 = this.coin5;
				this.coin5 = 0;
				coin -= num5*5;
				num1 = coin;
				this.coin1 -= num1;
			}
			
			else
			{
				this.coin5 -= num5;
				this.coin1 -= num1;
			}
		}
		this.coin = coin5*5 + coin1;
		
		int numCoin[] = {num5, num1, this.coin1};
		return numCoin;
	}

	public void addCoin(int[] numCoin)
	{
		this.coin5 += numCoin[0];
		this.coin1 += numCoin[1];
		this.coin = coin5*5 + coin1;
	}
	
	public void setOut() 
	{
		this.isOut = true;
	}
	
	public boolean getisOut()
	{
		return this.isOut;
	}
	
	public void setgridRugs(int rugs)
	{
		this.gridRugs = rugs;
	}
	
	public int getgridRugs()
	{
		return this.gridRugs;
	}
	
	public boolean isComputer()
	{
		return this.isComputer;
	}
}
