package model;

public class Dice {
	private int WuerfelAugen = 6;
	public int roll() {
		return (int)(((Math.random()*10) % WuerfelAugen)+1);
	}
}
