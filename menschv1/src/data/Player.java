package data;

import java.awt.Color;

public class Player {
	
	private final String name;
	private final Color color;
	private final int playerNumber;
	
	private Figure fig1;
	private Figure fig2;
	private Figure fig3;
	private Figure fig4;
	
	public Player(String name, Color color, int plNumber){
		this.name = name;
		this.color = color;
		this. playerNumber = plNumber;
		this.fig1= new Figure();
		this.fig2 = new Figure();
		this.fig3 = new Figure();
		this.fig4 = new Figure();
	}

	public String getName() {
		return name;
	}

	

	public Color getColor() {
		return color;
	}

	
	public int getPlNumber() {
		return this.playerNumber;
	}


	public Figure getFig1() {
		return fig1;
	}

	public void setFig1(Figure fig1) {
		this.fig1 = fig1;
	}

	public Figure getFig2() {
		return fig2;
	}

	public void setFig2(Figure fig2) {
		this.fig2 = fig2;
	}

	public Figure getFig3() {
		return fig3;
	}

	public void setFig3(Figure fig3) {
		this.fig3 = fig3;
	}

	public Figure getFig4() {
		return fig4;
	}

	public void setFig4(Figure fig4) {
		this.fig4 = fig4;
	}
	
	
	
}
