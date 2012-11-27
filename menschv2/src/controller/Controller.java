package controller;

import java.util.Scanner;

import model.Figure;
import model.Player;
import model.Playground;

public class Controller {

    Playground pg;
    
    public Controller(){
	this.pg = new Playground();
    }
    
    public void init() {

	Scanner in = new Scanner(System.in);
	System.out.println("Anzahl der Spieler eingeben");
	int pl = in.nextInt();
	for (int i = 0; i < pl; i++) {
	    this.pg.addPlayer(new Player(i));
	}
	runningGame(pl);
    }
    
    public void comingOut(int playerID) {
	int startField = pg.getPlayer(playerID).getStartField();
	
	/** if Player still got Figures on startStack **/
	if(pg.getPlayer(playerID).figureStackEmpty() == false){
	    Figure newFig = pg.getPlayer(playerID).popFigure();
	    /**  if startField is Occupied AND figure on startField is not the players Figure **/
	    if(pg.isOccupied(startField) && pg.getFigureOnPosition(startField).getFigurePlayer().getPlayerID() != playerID){
		kickEnemyFigure(newFig, startField);
	    }
	    pg.setFigureOnPosition(newFig, startField);
	}
    }
    
    public void kickEnemyFigure(Figure newFigure, int position){
	Figure old = pg.getFigureOnPosition(position);
	old.resetWegLaenge();
	old.getFigurePlayer().pushFigure(old);
	pg.setFigureOnPosition(newFigure, position);
    }
    
    public void moveForward(Figure fig, int positions){
	   System.out.println("pos: " + positions);
	    int newPos = fig.getFigurePos() + positions;
	    if(pg.isOccupied(newPos)){
		kickEnemyFigure(fig, newPos);
		return;
	    }
	    pg.setFigureOnPosition(fig, newPos);
	    System.out.println("WegLaenge von Figur " + fig.getFigureID() +" is "+ fig.getWeglaenge());
	    return;
	    //if feldende erreicht
	}
    
    public Figure pickFigure(int playerID){
	System.out.print("Spieler " + playerID + " Wählen sie eine Figur: zu verfügung stehen ");
	pg.getPlayer(playerID).printSoldiers();
	Scanner in = new Scanner(System.in);
	return pg.getPlayer(playerID).getFigure(in.nextInt());
    }
    
    public void runningGame(int pl){
	int i= 0;
	while(i < pl){
	    int roll;
	    roll = pg.getPlayer(i).rolling();
	    System.out.println(roll);
	    if( roll == 6){
		comingOut(i);
	    }else if(pg.getPlayer(i).getStackSize() == 4){
		continue;
	    }else{
		moveForward(pickFigure(i),roll);
	    }
	    i++;
	    if(i == pl){
		i = 0;
	    }
	}
    }
}
