package model;

import java.util.*;
import model.Figure;
import model.Player;

public class Playground {

	private int anzMit;
	private Figure fieldArray[];
	private List<Coordinate> fieldCoordinatelist = new ArrayList<Coordinate>();
	
	private Figure target1[];
	private List<Coordinate> target1Coordinatelist = new ArrayList<Coordinate>();

	private Figure target2[];
	private List<Coordinate> target2Coordinatelist = new ArrayList<Coordinate>();

	private Figure target3[];
	private List<Coordinate> target3Coordinatelist = new ArrayList<Coordinate>();

	private Figure target4[];
	private List<Coordinate> target4Coordinatelist = new ArrayList<Coordinate>();

	
	private LinkedList<Player> players = new LinkedList<Player>();
	
	
	private class Coordinate{
		int x,y;
		
		public Coordinate(int x, int y){
			x = x;
			y = y;
		}

	}
	public Playground() {
		this.fieldArray = new Figure[40];
		this.target1 = new Figure[4];
		this.target2 = new Figure[4];
		this.target3 = new Figure[4];
		this.target4 = new Figure[4];
		this.anzMit = 0;
	}

	public void addCoordinates(){
		// Feldkoordinaten
		fieldCoordinatelist.add(new Coordinate(75,275));
		fieldCoordinatelist.add(new Coordinate(125,275));
		fieldCoordinatelist.add(new Coordinate(175,275));
		fieldCoordinatelist.add(new Coordinate(225,275));
		fieldCoordinatelist.add(new Coordinate(275,275));
		fieldCoordinatelist.add(new Coordinate(275,225));
		fieldCoordinatelist.add(new Coordinate(275,175));
		fieldCoordinatelist.add(new Coordinate(275,125));
		fieldCoordinatelist.add(new Coordinate(275,50));
		fieldCoordinatelist.add(new Coordinate(325,50));
		fieldCoordinatelist.add(new Coordinate(375,50));
		fieldCoordinatelist.add(new Coordinate(375,125));
		fieldCoordinatelist.add(new Coordinate(375,175));
		fieldCoordinatelist.add(new Coordinate(375,225));
		fieldCoordinatelist.add(new Coordinate(375,275));
		fieldCoordinatelist.add(new Coordinate(375,275));
		fieldCoordinatelist.add(new Coordinate(425,275));
		fieldCoordinatelist.add(new Coordinate(475,275));
		fieldCoordinatelist.add(new Coordinate(525,275));
		fieldCoordinatelist.add(new Coordinate(575,275));
		fieldCoordinatelist.add(new Coordinate(575,325));
		fieldCoordinatelist.add(new Coordinate(575,375));
		fieldCoordinatelist.add(new Coordinate(525,375));
		fieldCoordinatelist.add(new Coordinate(475,375));
		fieldCoordinatelist.add(new Coordinate(425,375));
		fieldCoordinatelist.add(new Coordinate(375,375));
		fieldCoordinatelist.add(new Coordinate(375,425));
		fieldCoordinatelist.add(new Coordinate(375,475));
		fieldCoordinatelist.add(new Coordinate(375,525));
		fieldCoordinatelist.add(new Coordinate(375,575));
		fieldCoordinatelist.add(new Coordinate(325,575));
		fieldCoordinatelist.add(new Coordinate(275,575));
		fieldCoordinatelist.add(new Coordinate(275,525));
		fieldCoordinatelist.add(new Coordinate(275,475));
		fieldCoordinatelist.add(new Coordinate(275,425));
		fieldCoordinatelist.add(new Coordinate(275,375));
		fieldCoordinatelist.add(new Coordinate(225,375));
		fieldCoordinatelist.add(new Coordinate(175,375));
		fieldCoordinatelist.add(new Coordinate(125,375));
		fieldCoordinatelist.add(new Coordinate(75,375));
		fieldCoordinatelist.add(new Coordinate(75,325));
		
		//TargetfeldKoordinaten
		target1Coordinatelist.add(new Coordinate(125,325));
		target1Coordinatelist.add(new Coordinate(175,325));
		target1Coordinatelist.add(new Coordinate(225,325));
		target1Coordinatelist.add(new Coordinate(275,325));
		
		target2Coordinatelist.add(new Coordinate(325,125));
		target2Coordinatelist.add(new Coordinate(325,175));
		target2Coordinatelist.add(new Coordinate(325,225));
		target2Coordinatelist.add(new Coordinate(325,275));
		
		target3Coordinatelist.add(new Coordinate(525,325));
		target3Coordinatelist.add(new Coordinate(475,325));
		target3Coordinatelist.add(new Coordinate(425,325));
		target3Coordinatelist.add(new Coordinate(375,325));
		
		target4Coordinatelist.add(new Coordinate(325,525));
		target4Coordinatelist.add(new Coordinate(325,475));
		target4Coordinatelist.add(new Coordinate(325,425));
		target4Coordinatelist.add(new Coordinate(325,375));
	}
	
	public int[] getFieldCoordnate(int index){
		int[] array = new int[2];
		array[0]= fieldCoordinatelist.get(index).x;
		array[1]= fieldCoordinatelist.get(index).y;
		
		return array;
	}
	
	public int[] getTargetCoordnate(int playerID,int index){
		int[] array = new int[2];
		switch(playerID){
		case 0:
			array[0]= target1Coordinatelist.get(index).x;
			array[1]= target1Coordinatelist.get(index).y;
		case 1:
			array[0]= target2Coordinatelist.get(index).x;
			array[1]= target2Coordinatelist.get(index).y;
		case 2:
			array[0]= target3Coordinatelist.get(index).x;
			array[1]= target3Coordinatelist.get(index).y;
		case 3:
			array[0]= target4Coordinatelist.get(index).x;
			array[1]= target4Coordinatelist.get(index).y;
		}
		
		return array;
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
		if (fig != null)
			fig.setFigurePos(position);
	    this.fieldArray[position] = fig;
	}
	
	public boolean isOccupied(int position) {
	    if(this.fieldArray[position] == null) {
		return false;
	    }
	    return true;
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
