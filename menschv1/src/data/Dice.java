package data;

public class Dice {
	
	private int Dice;
	
	public Dice() {
		super();
	}

	public int getnewDice() {
		int x = (int) (Math.random()*10);
		if (x > 7 | x == 0) {
			getnewDice();
		}
		return x = Dice;
	}
	public int getDice() {
		return Dice;
	}
}
