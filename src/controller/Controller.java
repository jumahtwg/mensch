package controller;

import java.util.Scanner;

import observer.IObserver;
import observer.Observable;

import model.Figure;
import model.Player;
import model.Playground;
import view.TextGUI;

public class Controller {

	Playground pg;

	public Controller() {
		this.pg = new Playground();
	}

	public void init() {

		Scanner in = new Scanner(System.in);
		System.out.println("Anzahl der Spieler eingeben");
		int pl = in.nextInt();
		System.out.println("pl ist " + pl);
		
		while (pl > 4 ) {
			System.out.println("Maximale Spieleranzahl: 4, bitte eingeben.");
			pl = in.nextInt();
		}
		for (int i = 0; i < pl; i++) {
			pg.addPlayer(new Player(i));
		}
		runningGame(pl);
	}

	public void comingOut(int playerID) {
		int startField = pg.getPlayer(playerID).getStartField();
		Figure newFig = pg.getPlayer(playerID).popFigure();
		/**
		 * if startField is Occupied => occupied by Enemy
		 **/
		if (pg.isOccupied(startField)) {
			kickEnemyFigure(startField);
		}
		pg.setFigureOnPosition(newFig, startField);
	}

	public void kickEnemyFigure(int position) {
		Figure enemy = pg.getFigureOnPosition(position);
		enemy.resetWegLaenge();
		enemy.hasPlayer().pushFigure(enemy);
	}

	public void moveForward(Figure fig, int positions) {

		int oldPos = fig.getFigurePos();
		int newPos = oldPos + positions;
		fig.setWeglaenge(positions);
				
		/**
		 * wenn Figur mit aktuellen Wurf �ber eine Runde gelaufen ist -> store
		 * into Array
		 **/
		
		if (fig.getWeglaenge() > 39) {
			int c = fig.getWeglaenge();
			switch (c) {
			case 40:
				pg.storeFigure(fig, 0);
				pg.getPlayer(fig.getPlayerID()).removeFigureFromActiveSoldiers(fig);
				pg.setFigureOnPosition(null, oldPos);
				return;
			case 41:
				pg.storeFigure(fig, 1);
				pg.getPlayer(fig.getPlayerID()).removeFigureFromActiveSoldiers(fig);
				pg.setFigureOnPosition(null, oldPos);
				return;
			case 42:
				pg.storeFigure(fig, 2);
				pg.getPlayer(fig.getPlayerID()).removeFigureFromActiveSoldiers(fig);
				pg.setFigureOnPosition(null, oldPos);
				return;
			case 43:
				pg.storeFigure(fig, 3);
				pg.getPlayer(fig.getPlayerID()).removeFigureFromActiveSoldiers(fig);
				pg.setFigureOnPosition(null, oldPos);
				return;
			default:
				fig.setFigurePos(oldPos);
				return;
			}

		}
		
		if( newPos > 39){
			newPos = newPos % 39;
			fig.setFigurePos(newPos);
		}else
			fig.setFigurePos(newPos);

		/** wenn neues Feld besetzt und Figur auf Feld ist NICHT die eigene **/
		if (pg.isOccupied(newPos)
				&& pg.getFigureOnPosition(newPos).hasPlayer() != fig
						.hasPlayer()) {
			kickEnemyFigure(newPos);
			pg.setFigureOnPosition(null, oldPos);
			pg.setFigureOnPosition(fig, newPos);
			/** wenn neues Feld besetzt und Figur auf Feld ist die eigene **/
		} else if (pg.isOccupied(newPos)
				&& pg.getFigureOnPosition(newPos).hasPlayer() == fig
						.hasPlayer()) {
			/** w�hle andere Figur zum Laufen **/
			fig = pickFigure(fig.hasPlayer().getPlayerID());
			oldPos = fig.getFigurePos();
			newPos = fig.getFigurePos() + positions;
			pg.setFigureOnPosition(null, oldPos);
			pg.setFigureOnPosition(fig, newPos);
		}
		pg.setFigureOnPosition(null, oldPos);
		pg.setFigureOnPosition(fig, newPos);
		return;
		// if feldende erreicht
	}

	public Figure pickFigure(int playerID) {
		int tmp;
		TextGUI.printActiveFigures(pg.getPlayer(playerID).getPgFigureArray());
		Scanner in = new Scanner(System.in);
		tmp = in.nextInt();
		while(!pg.getPlayer(playerID).isFigureAvailable(tmp))
			tmp = in.nextInt();
	
		return pg.getPlayer(playerID).getFigure(tmp);
			
		
	}

	public void runningGame(int pl) {
		int i = 0;
		while (i < pl) {
			int roll = 0;
			
			roll = pg.getPlayer(i).rolling();
			TextGUI.printDice(pg.getPlayer(i).getPlayerID(), roll);
			/**
			 * wenn erster Wurf keine 6 und Spieler hat noch alle Figuren auf
			 * Stack, w�rfle 2 weitere Male
			 **/
			if ((roll != 6 && pg.getPlayer(i).getStackSize() == 4) 
					|| (roll != 6 && pg.getPlayer(i).getStackSize() != 4 
					&& pg.getPlayer(i).figureArrayEmpty() )) {
				for (int k = 0; k < 2 && roll != 6; k++) {
					roll = pg.getPlayer(i).rolling();
					
					TextGUI.printDice(pg.getPlayer(i).getPlayerID(), roll);

					/** wenn Wurf immernoch keine 6 , n�chster Spieler **/

				}
				if (roll != 6) {
					i++;
					if (i == pl) {
						i = 0;
					}
					continue;
				}
			}
			

			/**
			 * wenn w�rfel 6 zeigt UND spieler noch figuren auf Stack hat UND
			 * sein Startfeld NICHT von seiner eigenen Figur besetzt ist
			 * 
			 */
			
			if (roll == 6 && pg.getPlayer(i).figureStackEmpty() == false) {
				System.out.println("Spieler " + i + " kommt raus!");
				comingOut(i);
			} else if (roll == 6 && pg.getPlayer(i).figureStackEmpty() == true) {
				moveForward(pickFigure(i), roll);
			} else if (roll != 6)
				moveForward(pickFigure(i), roll);
			i++;
			if (i == pl) {
				i = 0;
			}
			TextGUI.printArrays(pg.getFieldArray());
			TextGUI.printArrays(pg.getTargetArray(0));
			TextGUI.printArrays(pg.getTargetArray(1));
			TextGUI.printArrays(pg.getTargetArray(2));
			TextGUI.printArrays(pg.getTargetArray(3));
//			for(int j = 0; j < pl; j++){
//				System.out.println("Ziel-Box von Spieler " + pg.getPlayer(j).getPlayerID());
//				TextGUI.printArrays(pg.getTargetArray(i));
//			}
		}
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

}
