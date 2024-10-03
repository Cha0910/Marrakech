import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
	public Frame()
	{
		super();
        
        final int HEIGHT = 780;
        final int WIDTH = 1320;
        Dimension dim = new Dimension(WIDTH, HEIGHT);
        
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        this.initComponents();

        this.pack();
	}
	
	private void initComponents()
    {
		this.push(new Menu(this));
    }
	
	
	public void push(JComponent p)
    {
        this.getContentPane().removeAll();
        this.add(p, BorderLayout.CENTER);
        this.revalidate();
        this.getContentPane().repaint();
    }
}
