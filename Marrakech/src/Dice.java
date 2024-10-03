import java.util.*;

public class Dice {
	final int[] dice = {1, 2, 2, 3, 3, 4};
	Random rand = new Random();
	
	public int rollDice()
	{
		return dice[rand.nextInt(dice.length)];
	}
}
