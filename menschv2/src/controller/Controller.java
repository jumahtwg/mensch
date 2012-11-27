package controller;

import java.util.Scanner;

import model.Figure;
import model.Player;
import model.Playground;

public class Controller {

	Playground pg;

	public Controller() {
		this.pg = new Playground();
	}

	public void init() {

		Scanner in = new Scanner(System.in);
		System.out.println("Anzahl der Spieler eingeben");
		int pl = in.nextInt();
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
		enemy.getFigurePlayer().pushFigure(enemy);
	}

	public void moveForward(Figure fig, int positions) {
		Figure tmp;
		System.out.println("pos: " + positions);
		int oldPos = fig.getFigurePos();
		int newPos = fig.getFigurePos() + positions;
		/** wenn neues Feld besetzt und Figur auf Feld ist NICHT die eigene **/
		if (pg.isOccupied(newPos)
				&& pg.getFigureOnPosition(newPos).getFigurePlayer() != fig
						.getFigurePlayer()) {
			kickEnemyFigure(newPos);
			pg.setFigureOnPosition(fig, newPos);
			/** wenn neues Feld besetzt und Figur auf Feld ist die eigene **/
		}else if(pg.isOccupied(newPos) && pg.getFigureOnPosition(newPos).getFigurePlayer() == fig
				.getFigurePlayer()){
			/** wähle andere Figur zum Laufen **/
			fig = pickFigure(fig.getFigurePlayer().getPlayerID());
			newPos = fig.getFigurePos() + positions;
			pg.setFigureOnPosition(fig, newPos);
		}
		/** wenn Figur mit aktuellen Wurf über eine Runde gelaufen ist -> store into Array **/
		else if((fig.getWeglaenge() + positions) > 39){
			fig.getFigurePlayer().storeFigure(fig);
		}
		return;
		// if feldende erreicht
	}

	public Figure pickFigure(int playerID) {
		System.out.print("Spieler " + playerID
				+ " Wählen sie eine Figur: zu verfügung stehen ");
		pg.getPlayer(playerID).printSoldiers();
		Scanner in = new Scanner(System.in);
		return pg.getPlayer(playerID).getFigure(in.nextInt());
	}

	public void runningGame(int pl) {
		int i = 0;
		while (i < pl) {
			int roll = 0;
			System.out.println("Spieler " + i);
			roll = pg.getPlayer(i).rolling();
			System.out.println("1er Wurf:"+roll);
			/** wenn erster Wurf keine 6 und Spieler hat noch alle Figuren auf Stack, würfle 2 weitere Male **/
			if (roll != 6 && pg.getPlayer(i).getStackSize() == 4) {
				for(int k = 0; k < 2 && roll != 6; k++){
					roll = pg.getPlayer(i).rolling();
					System.out.println(k+2 +"er Wurf: "+ roll);
				}
				/** wenn Wurf immernoch keine 6 , nächster Spieler **/
				if(roll != 6)
					i++;
				if (i == pl) {
					i = 0;
				}
					continue;
			}
			/**
			 * wenn würfel 6 zeigt UND spieler noch figuren auf Stack hat UND
			 * sein Startfeld NICHT von seiner eigenen Figur besetzt ist
			 */
			if (roll == 6 && pg.getPlayer(i).figureStackEmpty() == false) {
				comingOut(i);
				/**
				 * wenn würfel 6 zeigt UND spieler noch figuren auf Stack hat UND
				 * sein Startfeld von seiner eigenen Figur besetzt ist
				 * Bewege die Figur auf dem Startfeld um 6 nach vorne 
				 */
			}else if(roll == 6 && pg.getPlayer(i).figureStackEmpty() == false
					&& pg.getFigureOnPosition(pg.getPlayer(i).getStartField()).getFigurePlayer() == 
							pg.getPlayer(i)){
				moveForward(pg.getFigureOnPosition(pg.getPlayer(i).getStartField()), roll);
				/** wenn Würfel 6 und Spieler keine Figuren mehr im Stack, wähle irgendeine Figur und laufe 6 **/
			}else if(roll == 6 && pg.getPlayer(i).figureStackEmpty() == true){
				moveForward(pickFigure(i),roll);
			}else if(roll != 6)
				moveForward(pickFigure(i), roll);
			i++;
			if (i == pl) {
				i = 0;
			}
		}
	}
}
