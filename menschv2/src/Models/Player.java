package Models;

import java.util.*;

public class Player {
	
	private final String name;
	private final int playerID;
	private Dice dice;
	private int startField;
		
	private Stack<Figure> startStack = new Stack<Figure>();
	
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
	    if(startStack.isEmpty()){
		System.out.println("Keine Figuren mehr im Startfeld!");
		return null;
	    }
	    return startStack.pop();
	}
	
	public void pushFigure(Figure figure){
	    if(startStack.size() == 4) {
		System.out.println("Alle Figuren des Spielers sind bereits im Startfeld");
		return;
	    }
	    figure.resetWegLaenge();
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
}
