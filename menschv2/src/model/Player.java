package model;

import java.util.*;

import model.Dice;
import model.Figure;

public class Player {

	private final int playerID;
	private Dice dice;
	private int startField;
	private static final int SPIELFIGUREN = 4;
	private static final int MAXSPIELFIGUREN = 4;
	private static final int STARTFELDSPIELER1 = 0;
	private static final int STARTFELDSPIELER2 = 10;
	private static final int STARTFELDSPIELER3 = 20;
	private static final int STARTFELDSPIELER4 = 30;
	public static final int SPIELER1 = 0;
	public static final int SPIELER2 = 1;
	public static final int SPIELER3 = 2;
	public static final int SPIELER4 = 3;

	private Deque<Figure> startStack = new ArrayDeque<Figure>();
	private Figure[] pgFigureArray = new Figure[SPIELFIGUREN];

	public Player(int playerID) {
		this.playerID = playerID;
		for (int i = SPIELFIGUREN-1; i >= 0; i--) {
			startStack.push(new Figure(i, this));
		}
		this.dice = new Dice();

		switch (playerID) {
		case SPIELER1:
			this.startField = STARTFELDSPIELER1;
			break;
		case SPIELER2:
			this.startField = STARTFELDSPIELER2;
			break;
		case SPIELER3:
			this.startField = STARTFELDSPIELER3;
			break;
		case SPIELER4:
			this.startField = STARTFELDSPIELER4;
			break;
		default:
		}
	}

	public int getPlayerID() {
		return this.playerID;
	}

	public Figure popFigure() {
		Figure tmp;
		//Keine Figuren mehr im Startfeld!
		if (startStack.isEmpty()) {
			return null;
		}
		tmp = startStack.pop();
		// Figur " + tmp.getFigureID() + " wurde vom Stack geholt!
		pgFigureArray[tmp.getFigureID()] = tmp;
		return tmp;
	}

	public void pushFigure(Figure figure) {
		if (startStack.size() == MAXSPIELFIGUREN) {
			System.out
					.println("Alle Figuren des Spielers sind bereits im Startfeld");
			return;
		}
		figure.resetWegLaenge();
		pgFigureArray[figure.getFigureID()] = null;
		startStack.push(figure);
		return;
	}

	public boolean figureStackEmpty() {
		if (startStack.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean figureArrayEmpty(){
		for(int i = 0; i< pgFigureArray.length; i++){
			if(pgFigureArray[i] == null) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public int getStackSize() {
		return startStack.size();
	}

	public int rolling() {
		return dice.roll();
	}

	public int getStartField() {
		return this.startField;
	}

	public Figure getFigure(int figureID) {
		return pgFigureArray[figureID];
	}

	public void storeFigure(Figure fig) {
		for (int i = 0; i < pgFigureArray.length; i++) {
			if (pgFigureArray[i] == null) {
				pgFigureArray[i] = fig;
				return;
			}
		}
	}
	public void removeFigureFromActiveSoldiers(Figure fig){
		pgFigureArray[fig.getFigureID()] = null;
		return;
	}
	public Figure[] getPgFigureArray() {
		return pgFigureArray;
	}
	public boolean isFigureAvailable(int figID){
		if(figID > SPIELFIGUREN-1 || pgFigureArray[figID] == null){
			return false;
		}else{
			return true;
		}
	}
}
