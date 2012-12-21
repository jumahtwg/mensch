package model;

import java.util.*;

import model.Dice;
import model.Figure;

public class Player {

	private final int playerID;
	private Dice dice;
	private int startField;
	private int endField;
	private int spielfiguren = 4;
	private final int maxspielfiguren = 4;
	private int startfeldspieler1 = 0;
	private int startfeldspieler2 = 10;
	private int startfeldspieler3 = 20;
	private int startfeldspieler4 = 30;

	private Deque<Figure> startStack = new ArrayDeque<Figure>();
	private Figure[] pgFigureArray = new Figure[spielfiguren];

	public Player(int playerID) {
		this.playerID = playerID;
		for (int i = spielfiguren-1; i >= 0; i--) {
			startStack.push(new Figure(i, this));
		}
		this.dice = new Dice();

		switch (playerID) {
		case 0:
			this.startField = startfeldspieler1;
			break;
		case 1:
			this.startField = startfeldspieler2;
			break;
		case 2:
			this.startField = startfeldspieler3;
			break;
		case 3:
			this.startField = startfeldspieler4;
			break;
		default:
		}
		this.endField = startField - 1;
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
		if (startStack.size() == maxspielfiguren) {
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
		if(figID > spielfiguren-1 || pgFigureArray[figID] == null){
			return false;
		}else{
			return true;
		}
	}
}
