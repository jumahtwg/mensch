package Models;

import java.util.*;

public class Player {
	
	private final String name;
	private final int playerID;
	private Dice dice;
	private int startField;
		
	private Stack<Figure> startStack = new Stack<Figure>();
	private List<Figure> pgFigureList = new LinkedList<Figure>();
	public Player(String name,int playerID){
		this.name = name;
		this.playerID = playerID;
		for(int i = 1;i <= 4; i++){
		    startStack.push(new Figure(i,this));
		}
		this.dice = new Dice();
		
		switch(playerID){
		case 1:
		    this.startField = 0;
		case 2:
		    this.startField = 10;
		case 3:
		    this.startField = 20;
		case 4:
		    this.startField = 30;
		default:
		    
		}
	}

	public String getName() {
		return name;
	}

	public int getPlayerID() {
		return this.playerID;
	}

	public Figure popFigure() {
	    Figure tmp;
	    if(startStack.isEmpty()){
		System.out.println("Keine Figuren mehr im Startfeld!");
		return null;
	    }
	    tmp = startStack.pop();
	    pgFigureList.add(tmp.getFigureID(), tmp);
	    return tmp;
	}
	
	public void pushFigure(Figure figure){
	    if(startStack.size() == 4) {
		System.out.println("Alle Figuren des Spielers sind bereits im Startfeld");
		return;
	    }
	    figure.resetWegLaenge();
	    pgFigureList.remove(figure.getFigureID());
	    startStack.push(figure);
	    return;
	}
	
	public boolean figureStackEmpty(){
	    if(startStack.isEmpty()){
		return true;
	    }
	    return false;
	}
	
	public int getStackSize(){
	    return startStack.size();
	}
	
	public int rolling(){
	    return dice.roll();
	}
	
	public int getStartField(){
	    return this.startField;
	}
	public Figure getFigure(int figureID){
	    return pgFigureList.get(figureID -1);
	}
}
