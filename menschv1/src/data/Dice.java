package data;

public class Dice {
	
	private static int DiceNR;
	
	public Dice() {
		super();
	}

	public int getnewDice() {
		int x = (int) (((Math.random()*10) % 6)+1);

		return DiceNR = x;
	}
	public int getDice() {
		return DiceNR;
	}
}
