package Models;

public class Figure {
	private int wegLaenge;
	private int figureID;
	private Player player;
	
	public Figure(int figID,Player player) {
	    this.wegLaenge = 0;
	    this.figureID = figID;
	    this.player = player;
	};
	
	public int getFigurePos() {
		return this.wegLaenge;
	}
	public void setFigurePos(int figure) {
		wegLaenge = figure;
	}
	
	
	public void resetWegLaenge(){
	    this.wegLaenge = 0;
	}

	public int getWegLaenge() {
	    return wegLaenge;
	}

	public int getFigureID() {
	    return figureID;
	}
	
	public Player getFigurePlayer(){
	    return this.player;
	}

	
	
}