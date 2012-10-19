package data;

public class Dice {
	
	private static int DiceNR;
	
	public Dice() {
		super();
	}

	public void createNewDice() {
		int x = (int) (((Math.random()*10) % 6)+1);

		DiceNR = x;
	}
	public int getDice() {
		return DiceNR;
	}
}
