package controller;

import java.util.Scanner;

import model.Figure;
import model.Player;
import model.Playground;
import view.TextGUI;

public class Controller {

	private Playground pg;
	private static final int MAXSPIELER = 4;
	private static final int MAXGEFAHRENEWEGLAENGE = 39;
	private static final int GEWUERFELTESECHS = 6;
	private static final int VORHANDENEFIGUREN = 4;
	private static final int ERSTESZIELFELD = 40;
	private static final int ZWEITESZIELFELD = 41;
	private static final int DRITTESZIELFELD = 42;
	private static final int VIERTESZIELFELD = 43;
	private static final int NULL = 0;
	private static final int EINS = 1;
	private static final int ZWEI = 2;
	private static final int DREI = 3;

	

	public Controller() {
		this.pg = new Playground();
	}

	public void init() {

		Scanner in = new Scanner(System.in);
		System.out.println("Anzahl der Spieler eingeben");
		int pl = in.nextInt();
		System.out.println("pl ist " + pl);
		
		while (pl > MAXSPIELER ) {
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

	public void storeFigure(Figure fig, int oldPos){
		int c = fig.getWeglaenge();
		switch (c) {
		case ERSTESZIELFELD:
			pg.storeFigure(fig, NULL);
			pg.getPlayer(fig.getPlayerID()).removeFigureFromActiveSoldiers(fig);
			pg.setFigureOnPosition(null, oldPos);
			return;
		case ZWEITESZIELFELD:
			pg.storeFigure(fig, EINS);
			pg.getPlayer(fig.getPlayerID()).removeFigureFromActiveSoldiers(fig);
			pg.setFigureOnPosition(null, oldPos);
			return;
		case DRITTESZIELFELD:
			pg.storeFigure(fig, ZWEI);
			pg.getPlayer(fig.getPlayerID()).removeFigureFromActiveSoldiers(fig);
			pg.setFigureOnPosition(null, oldPos);
			return;
		case VIERTESZIELFELD:
			pg.storeFigure(fig, DREI);
			pg.getPlayer(fig.getPlayerID()).removeFigureFromActiveSoldiers(fig);
			pg.setFigureOnPosition(null, oldPos);
			return;
		default:
			fig.setFigurePos(oldPos);
			return;
		}
	}
	
	public void moveForward(Figure fig, int positions) {

		int oldPos = fig.getFigurePos();
		int newPos = oldPos + positions;
		fig.setWeglaenge(positions);
				
		/**
		 * wenn Figur mit aktuellen Wurf über eine Runde gelaufen ist -> store
		 * into Array
		 **/

		if (fig.getWeglaenge() > MAXGEFAHRENEWEGLAENGE) {
			storeFigure(fig,oldPos);
		}
		
		if( newPos > MAXGEFAHRENEWEGLAENGE){
			newPos = newPos % MAXGEFAHRENEWEGLAENGE;
			fig.setFigurePos(newPos);
		} else {
			fig.setFigurePos(newPos);
		}

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
			/** wï¿½hle andere Figur zum Laufen **/
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
		while(!pg.getPlayer(playerID).isFigureAvailable(tmp)) {
			tmp = in.nextInt();
		}
		return pg.getPlayer(playerID).getFigure(tmp);
			

		
	}

	public void runningGame(int pl) {
		int i = 0;
		while (i < pl) {
			int roll = 0;
			
			/**
			 * wenn erster Wurf keine 6 und Spieler hat noch alle Figuren auf
			 * Stack, wï¿½rfle 2 weitere Male
			 **/
			
			if(pg.getPlayer(i).getStackSize() == VORHANDENEFIGUREN){
				int diceCount = 0;
				while(roll != GEWUERFELTESECHS && diceCount < 3){
					roll = pg.getPlayer(i).rolling();
					TextGUI.printDice(pg.getPlayer(i).getPlayerID(), roll);
					diceCount++;
					
				}
				System.out.println("_________________");
				if (roll != GEWUERFELTESECHS) {
					i++;
					if (i == pl) {
						i = 0;
					}
					continue;
				}
			}else{
				roll = pg.getPlayer(i).rolling();
				TextGUI.printDice(pg.getPlayer(i).getPlayerID(), roll);
			}
					
//			if ((roll != GEWUERFELTESECHS && pg.getPlayer(i).getStackSize() == VORHANDENEFIGUREN) ) {
//				for (int k = 0; k < 2 && roll != GEWUERFELTESECHS; k++) {
//					roll = pg.getPlayer(i).rolling();
//					
//					TextGUI.printDice(pg.getPlayer(i).getPlayerID(), roll);
//
//					/** wenn Wurf immernoch keine 6 , nï¿½chster Spieler **/
//
//				}
//				
//				if (roll != GEWUERFELTESECHS) {
//					i++;
//					if (i == pl) {
//						i = 0;
//					}
//					continue;
//				}
//			}
//		     // wenn erster Wurf keine 6 und Spieler hat nicht mehr alle Figuren auf Stack
//			 (roll != GEWUERFELTESECHS && pg.getPlayer(i).getStackSize() != VORHANDENEFIGUREN 
//						&& pg.getPlayer(i).figureArrayEmpty()
//			

			/**
			 * wenn würfel 6 zeigt UND spieler noch figuren auf Stack hat UND
			 * sein Startfeld NICHT von seiner eigenen Figur besetzt ist
			 * 
			 */
			
			if (roll == GEWUERFELTESECHS && !pg.getPlayer(i).figureStackEmpty()) {
				System.out.println("Spieler " + i + " kommt raus!");
				comingOut(i);
			} else if (roll == GEWUERFELTESECHS && pg.getPlayer(i).figureStackEmpty()) {
				moveForward(pickFigure(i), roll);
			} else if (roll != GEWUERFELTESECHS) {
				moveForward(pickFigure(i), roll);
			}
			i++;
			if (i == pl) {
				i = 0;
			}
			TextGUI.printArrays(pg.getFieldArray());
			TextGUI.printArrays(pg.getTargetArray(NULL));
			TextGUI.printArrays(pg.getTargetArray(EINS));
			TextGUI.printArrays(pg.getTargetArray(ZWEI));
			TextGUI.printArrays(pg.getTargetArray(DREI));
		}
	}

}
