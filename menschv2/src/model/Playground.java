package model;

import java.util.*;

import model.Figure;
import model.Player;

public class Playground {

	private int anzMit;
	private Figure fieldArray[];
	private int feldlaenge = 40;
	private int zielfeldlaenge = 4;
	
	private Figure target1[];
	private Figure target2[];
	private Figure target3[];
	private Figure target4[];
	
	private LinkedList<Player> players = new LinkedList<Player>();
		
	public Playground() {
		this.fieldArray = new Figure[feldlaenge];
		this.target1 = new Figure[zielfeldlaenge];
		this.target2 = new Figure[zielfeldlaenge];
		this.target3 = new Figure[zielfeldlaenge];
		this.target4 = new Figure[zielfeldlaenge];
		this.anzMit = 0;
	}

	public void addPlayer(Player newPlayer){		
		players.add(newPlayer);
		anzMit++;
	}
	public int getAnzMit() {
		return anzMit;
	}

	public void setAnzMit(int anzMit) {
		this.anzMit = anzMit;
	}
	
	public Figure getFigureOnPosition(int position){
		if(this.fieldArray[position] == null){
			return null;
		}
	    return this.fieldArray[position];
	}
	public void setFigureOnPosition(Figure fig, int position){
		if (fig != null) {
			fig.setFigurePos(position);
		}
	    this.fieldArray[position] = fig;
	}
	
	public boolean isOccupied(int position) {
	    if(this.fieldArray[position] != null) {
		return true;
	    }
	    return false;
	}	
	
	
	public Player getPlayer(int playerID){
	    return (players.get(playerID)) ;
	}
	public Figure[] getFieldArray(){
		return fieldArray;
	}
	public Figure[] getTargetArray(int playerID){
		switch(playerID){
		case 0:
			return target1;
		case 1:
			return target2;
		case 2:
			return target3;
		case 3:
			return target4;
		default:
			return null;
		}
	}
	
	public void storeFigure(Figure fig, int storagePoint){
		int c = fig.getPlayerID();
		switch(c)
		{
		case 0:
			target1[storagePoint] = fig;
			break;
		case 1:
			target2[storagePoint] = fig;
			break;
		case 2:
			target3[storagePoint] = fig;
			break;
		case 3:
			target4[storagePoint] = fig;
			break;
		}
	}
	
}
