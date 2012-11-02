package data;

import java.awt.Color;

public class Player {
	
	private final String name;
	private final int playerNumber;
	private int anzFigures;
	
	private Figure fig1;
	private Figure fig2;
	private Figure fig3;
	private Figure fig4;
	
	private Figure start[][];
	
	public Player(String name,int plNumber){
		this.name = name;
		this.playerNumber = plNumber;
		this.fig1= new Figure();
		this.fig2 = new Figure();
		this.fig3 = new Figure();
		this.fig4 = new Figure();
		this.anzFigures = 4;
		
		this.start = new Figure[][] {{fig1,fig2},{fig3,fig4}};
	}

	public String getName() {
		return name;
	}

	public int getPlNumber() {
		return this.playerNumber;
	}

	public Figure getFig1() {
		return fig1;
	}

	public Figure getFig2() {
		return fig2;
	}

	public Figure getFig3() {
		return fig3;
	}

	public Figure getFig4() {
		return fig4;
	}
	
	public int getFigureLeft(){
	    return anzFigures;
	}
}
