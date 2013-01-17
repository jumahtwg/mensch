package view;

import java.util.Scanner;

import model.Figure;
import observer.IObserver;

import org.apache.log4j.Logger;

import controller.Controller;

public class TextGUI implements IObserver {
	
	private class TextInput implements Runnable {

		private Scanner scan;
		private Controller contr;
		
		public TextInput(Controller contr)  {
			this.contr = contr;
			scan = new Scanner(System.in);
		}
		
		public void run() {
			while (true) {		
				int input = scan.nextInt();
				
				switch (contr.getStatus()) {
				case CHOOSE_FIG:
					contr.setPickFigure(input);
					break;
				case CHOOSE_PLAYER_COUNT:
					contr.inputPlayerCount(input);
					break;
				case ROLL:
					contr.doDice();		
					break;
				default:
					
					break;
				}				
			}			
		}
		
	}
	
	private final static int wuerfelEINS = 1;
	private final static int wuerfelZWEI = 2;
	private final static int wuerfelDREI = 3;
	private final static int wuerfelVIER = 4;
	private final static int wuerfelFUENF = 5;
	private final static int wuerfelSECHS = 6;
	private final static String kante = " ------- ";
	private final static String leer =  "         ";
	
	private Logger logger = Logger.getLogger("view.TextGUI");

	private Thread inputThread;
	private Controller controller;
	
	public TextGUI(Controller controller) {
		super();
		this.controller = controller;
		
		this.inputThread = new Thread(new TextInput(controller));
		this.inputThread.setDaemon(true);
		this.inputThread.setName("InputThread");
		this.inputThread.start();
	}
	
	
	public void printArrayInformation(Figure array[], int i) {
		switch(i){
		case 1:
			println("Spielfeld Array: ");
			printArrays(array);
			break;
		case 2:
			println("Ziel-Box Arrays: ");

		}
	}
	
	public void printArrays(Figure array[]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				sb.append("[");
				sb.append(array[i].hasPlayer().getPlayerID());
				sb.append(":");
				sb.append(array[i].getFigureID());
				sb.append("]");
			}else{
				sb.append("[ ]");
			}
		}
		sb.append("\n");
		print(sb.toString());
	}
	
	public void printActiveFigures(Figure array[]){
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {			
				printFigureStatus(array[i]);
			}
		}
		println("");
	}
	
	public void printFigureStatus(Figure fig){
		println("-----------------------------------------------");
		println("PlayerID: " + fig.getPlayerID());
		println("FigurID: " + fig.getFigureID());
		println("    Figurposition im Spielfeld: " + fig.getFigurePos());
		println("    FigurWegLaenge: " + fig.getWeglaenge());
		println("-----------------------------------------------");
	}
	
	public void printMoveForward(Figure fig, int roll ){
		println("Spieler " + fig.getPlayerID() + ":Figur " + fig.getFigureID() + "wurde ausgewaehlt");
		println("    Bewegt sich um " + roll + " Schritte nach vorne");
	}
	
	public void printDice(int playerID, int value){
		println("Player " + playerID + " Wuerfel: ");
		switch (value){
		case wuerfelEINS:
			println(kante);
			println(leer);
			println("|   O   |");
			println(leer); 
			println(kante);
			break;

		case wuerfelZWEI:
			println(kante);
			println("|     O |");
			println(leer);
			println("| O     |");
			println(kante);
			break;
		case wuerfelDREI:
			println(kante);
			println("|     O |");
			println("|   O   |");
			println("| O     |");
			println(kante);
			break;
		case wuerfelVIER:
			println(kante);
			println("| O   O |");
			println(leer);
			println("| O   O |");
			println(kante);
			break;
		case wuerfelFUENF:
			println(kante);
			println("| O   O |");
			println("|   O   |");
			println("| O   O |");
			println(kante);
			break;
		case wuerfelSECHS:
			println(kante);
			println("| O   O |");
			println("| O   O |");
			println("| O   O |");
			println(kante);
			break;
		}
		
		
	}
	public void println(String sg){
		logger.info(sg );
	}
	public void print(String sg){
		logger.info(sg);
	}
	
	public void updatePrintDice() {		
		printDice(controller.getActivePlayer().getPlayerID(), controller.getRoll());
	}


	public void updateShowGameFrame() {
		printArrays(controller.getPgArray());
		for(int i=0; i < 4;i++){
		    printArrays(controller.getTargetFigureArray(i));
		}
	}
	
	public void updatePrintFigures(){
		printActiveFigures(controller.getPlayerFigures());
	}

	public void inputChoosePlayerCount() {

	}
	
	public void updateChooseFigure(){
		println("Spieler wählen: ");
	}


	public void updateInput() {
	
	}


	public void updateObserversRoll() {
		println("Bitte würfeln: ");
	}


	public void updatePlayerStatus() {
		int c = controller.getActivePlayer().getPlayerID();
		switch(c){
		case 0:
			println("Grüner Spieler ist dran!");
			break;
		case 1:
			println("Gelber Spieler ist dran!");
			break;
		case 2:
			println("Blauer Spieler ist dran!");
			break;
		case 3:
			println("Roter Spieler ist dran!");
			break;
		default:
			println("Grüner Spieler ist dran!");
			break;
		}
		
	}
}
