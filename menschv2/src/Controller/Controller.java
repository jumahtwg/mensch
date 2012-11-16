package Controller;

import java.util.Scanner;

import model.Figure;
import model.Player;
import model.Playground;


public class Controller {

    Playground pg = new Playground();
    
    public int init() {

	Scanner in = new Scanner(System.in);
	System.out.println("Anzahl der Spieler eingeben");
	int pl = in.nextInt();
	for (int i = 1; i <= pl; i++) {
	    System.out.println("Geben sie den Namen des " + i
		    + ". Spielers ein");
	    String name = in.next();
	    this.pg.addPlayer(new Player(name, i));
	}
	return pl;
    }
    
    public void comingOut(int playerID) {

	int startField = pg.getPlayer(playerID).getStartField();
	
	/** if Player still got Figures on startStack **/
	if(pg.getPlayer(playerID).figureStackEmpty() != true){
	    Figure tmp = pg.getPlayer(playerID).popFigure();
	    /**  if startField is Occupied AND figure on startField is not the players Figure **/
	    if(pg.isOccupied(startField) && pg.getFigureOnPosition(startField).getFigurePlayer().getPlayerID() != playerID){
		kickEnemyFigure(tmp, startField);
	    }
	    pg.setFigureOnPosition(tmp, startField);
	    /** roll again **/
	}
    }
    
    
    public void kickEnemyFigure(Figure newFigure, int position){
	Figure old = pg.getFigureOnPosition(position);
	old.getFigurePlayer().pushFigure(old);
	old.resetWegLaenge();
	pg.setFigureOnPosition(newFigure, position);
    }
    
    public void moveForward(Figure fig, int positions){
	    int current = fig.getFigurePos();
	    int newPos = current + positions;
	    if(pg.isOccupied(newPos)){
		kickEnemyFigure(fig, newPos);
		return;
	    }
	    pg.setFigureOnPosition(fig, newPos);
	    return;
	}
    
    public void runningGame(){
	
    }
}
