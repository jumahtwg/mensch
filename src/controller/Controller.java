package controller;

import java.io.FileNotFoundException;
import java.util.List;

import model.Figure;
import model.Player;
import model.Playground;
import observer.Observable;

public class Controller extends Observable {
	
	public enum GAME_STATE {
		CHOOSE_FIG, ROLL, CHOOSE_PLAYER_COUNT, GAME_STOP
	}

	private Playground pg;
	private int activePlayerID;
	private int activeFigureID;
	private GAME_STATE status;
	private int roll;
	private int pl;
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
	
	public void start() {
		try {
			pg.addCoordinates();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		status = GAME_STATE.CHOOSE_PLAYER_COUNT;
		notifyChoosePlayerCount();
	}
	
	public void inputPlayerCount(int playerCount) {
		pl = playerCount;
		//pg.setAnzMit(playerCount);
		for (int i = 0; i < playerCount; i++) {
			pg.addPlayer(new Player(i));
		}
		activePlayerID = 0;
		
		notifyShowGameFrame();
		status = GAME_STATE.ROLL;
		notifyObserversRoll();
	}
	
	private void incrementPlayerID(){
		if (roll == GEWUERFELTESECHS) {
			return;
		}
		
		activePlayerID++;
		if (activePlayerID == pl) {
			activePlayerID = 0;
		}
	}

	public void doDice() {
		roll = pg.getPlayer(activePlayerID).rolling();
		notifyShowGameFrame();
		notifyObserversPrintDice();
		
		if ((roll != GEWUERFELTESECHS && pg.getPlayer(activePlayerID).getStackSize() == VORHANDENEFIGUREN) 
				|| (roll != GEWUERFELTESECHS && pg.getPlayer(activePlayerID).getStackSize() != VORHANDENEFIGUREN 
				&& pg.getPlayer(activePlayerID).figureArrayEmpty() )) {
			for (int k = 0; k < 2 && roll != GEWUERFELTESECHS; k++) {
				roll = pg.getPlayer(activePlayerID).rolling();
			
				notifyObserversPrintDice();
				/** wenn Wurf immernoch keine 'GEWUERFELTESECHS' , naechster Spieler **/

			}
			if (roll != GEWUERFELTESECHS) {
				incrementPlayerID();
				status = GAME_STATE.ROLL;
				notifyObserversRoll();
				return;
			}
		}
		
		/**
		 * wenn wuerfel 'GEWUERFELTESECHS' zeigt UND spieler noch figuren auf Stack hat UND
		 * sein Startfeld NICHT von seiner eigenen Figur besetzt ist
		 * 
		 */
		
		if (roll == GEWUERFELTESECHS && pg.getPlayer(activePlayerID).figureStackEmpty() == false) {
			comingOut(activePlayerID);
			status = GAME_STATE.ROLL;
			notifyObserversRoll();
			notifyShowGameFrame();
			return;
			
		} else if (roll == GEWUERFELTESECHS && pg.getPlayer(activePlayerID).figureStackEmpty() == true) {
			status = GAME_STATE.CHOOSE_FIG;
			notifyChooseFigure();						
			return;
		} else if (roll != GEWUERFELTESECHS) {
			status = GAME_STATE.CHOOSE_FIG;
			notifyChooseFigure();
			return;
		}
		incrementPlayerID();
		notifyShowGameFrame();
		status = GAME_STATE.ROLL;
		notifyObserversRoll();		
	}
		

	public int getPl() {
		return pl;
	}

	public static int getMaxspieler() {
		return MAXSPIELER;
	}

	public void setPl(int pl) {
		this.pl = pl;
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
		 * wenn Figur mit aktuellen Wurf ueber eine Runde gelaufen ist -> store
		 * into Array
		 **/
		
		if (fig.getWeglaenge() > MAXGEFAHRENEWEGLAENGE) {
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
			/** waehle andere Figur zum Laufen **/
			notifyChooseFigure();
			return;

		}
		pg.setFigureOnPosition(null, oldPos);
		pg.setFigureOnPosition(fig, newPos);
		return;
	}
		
	public void setPickFigure(int figureID) {		
		if (pg.getPlayer(activePlayerID).isFigureAvailable(figureID)) {
			activeFigureID =  figureID;		
			moveForward(pg.getPlayer(activePlayerID).getFigure(activeFigureID), roll);
		}
		incrementPlayerID();
		notifyShowGameFrame();
		status = GAME_STATE.ROLL;
		notifyObserversRoll();			
		return;
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	public Player getActivePlayer() {
		return pg.getPlayer(activePlayerID);
	}
	
	public int getRoll() {
		return roll;
	}
	public Figure[] getPlayerFigures(){
		return pg.getPlayer(activePlayerID).getPgFigureArray();
	}
	public Figure[] getPgArray(){
		return pg.getFieldArray();
	}
	
	public List<String> getFieldCoords(){
		return pg.getFieldCoordnates();
	}
	
	public List<String> getTargetCoords(int player){
		return pg.getTargetCoordnates(player);
	}
	public List<String> getStackCoords() throws FileNotFoundException{
		return pg.getStackCoords();
	}
	public Figure getFigureOnPos(int k){
		return pg.getFigureOnPosition(k);
	}
	
	public int getStackSize(int playerID){
//		if(pg.getPlayer(playerID) == null)
//			return -1;
		return pg.getPlayer(playerID).getStackSize();
	}
	
	public int getAnzahlMitspieler(){
		return pl;
	}
	
	public Figure[] getTargetFigureArray(int playerID){
		return pg.getTargetArray(playerID);
	}

	public GAME_STATE getStatus() {
		return status;
	}

	public void setStatus(GAME_STATE status) {
		this.status = status;
	}
	
}
