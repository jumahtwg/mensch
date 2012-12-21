package model;

import java.util.*;

import model.Dice;
import model.Figure;

public class Player {

	private final int playerID;
	private Dice dice;
	private int startField;
	private int endField;

	private Deque<Figure> startStack = new ArrayDeque<Figure>();
	private Figure[] pgFigureArray = new Figure[4];

	public Player(int playerID) {
		this.playerID = playerID;
		for (int i = 3; i >= 0; i--) {
			startStack.push(new Figure(i, this));
		}
		this.dice = new Dice();

		switch (playerID) {
		case 0:
			this.startField = 0;
			break;
		case 1:
			this.startField = 10;
			break;
		case 2:
			this.startField = 20;
			break;
		case 3:
			this.startField = 30;
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
		if (startStack.size() == 4) {
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
			if(pgFigureArray[i] == null)
				continue;
			else
				return false;
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
		if(figID > 3 || pgFigureArray[figID] == null){
			return false;
		}else{
			return true;
		}
	}
}
