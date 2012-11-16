package Controller;

import java.util.Scanner;

import Models.Figure;
import Models.Player;
import Models.Playground;

public class Controller {

    Playground pg;
    
    public Controller(){
	this.pg = new Playground();
    }
    
    public void init() {

	Scanner in = new Scanner(System.in);
	System.out.println("Anzahl der Spieler eingeben");
	int pl = in.nextInt();
	for (int i = 1; i <= pl; i++) {
	    System.out.println("Geben sie den Namen des " + i
		    + ". Spielers ein");
	    String name = in.next();
	    this.pg.addPlayer(new Player(name, i));
	}
	runningGame();
    }
    
    public void comingOut(int playerID) {
	int startField = pg.getPlayer(playerID).getStartField();
	
	/** if Player still got Figures on startStack **/
	if(pg.getPlayer(playerID).figureStackEmpty() != true){
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
	   
	    int newPos = fig.getFigurePos() + positions;
	    if(pg.isOccupied(newPos)){
		kickEnemyFigure(fig, newPos);
		return;
	    }
	    pg.setFigureOnPosition(fig, newPos);
	    return;
	    //if feldende erreicht
	}
    
    public Figure pickFigure(Player playerID){
	System.out.println("Wählen sie eine Figur:1-4");
	Scanner in = new Scanner(System.in);
	return playerID.getFigure(in.nextInt());
    }
    
    public void runningGame(){
	
	for(int i= 1; i<=4;i++){
	    int roll;
	    roll = pg.getPlayer(i).rolling();
	    if( roll == 6){
		comingOut(i);
	    }else{
		moveForward(pickFigure(pg.getPlayer(i)),roll);
	    }
	}
    }
}
