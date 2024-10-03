import java.awt.*;

public class DFS {
	private int dx[] = {-1, 0, 1, 0};
	private int dy[] = {0, 1, 0, -1};
	private int numRug;
	private boolean[][] visited = new boolean[7][7];
	private Grid grid;
	
	public DFS(Grid g)
	{
		this.grid = g;
		this.reset();
	}
	
	private void dfsRecursion(Color color, int x, int y)
	{
		this.visited[x][y] = true;
		this.numRug++;
		
		for(int i=0; i<4; i++)
		{
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(0 <= nx && nx < 7 && 0 <= ny && ny < 7)
			{
				if(this.grid.buttons[nx][ny].getBackground().equals(color) && !this.visited[nx][ny])
				{
					dfsRecursion(color, nx, ny);
				}
			}
		}
	}
	
	public int getnumRug(Color color, int x, int y)
	{
		this.dfsRecursion(color, x, y);
		int rug = this.numRug;
		this.reset();
		return rug;
	}
	
	private void reset()
	{
		this.numRug = 0;
		for(int i=0; i<7; i++)
		{
			for(int j=0; j<7; j++)
			{
				this.visited[i][j] = false;
			}
		}
	}
	
	public void setgrid(Grid g)
	{
		this.grid = g;
	}
}
