package view;

import java.util.Scanner;

import org.apache.log4j.Logger;

import observer.IObserver;

import model.Figure;
import model.Player;

public class TextGUI {
	private static final Logger logger = Logger.getLogger("view.TextGUI");

	
	public static void printArrayInformation(Figure array[], int i) {
		switch(i){
		case 1:
			println("Spielfeld Array: ");
			printArrays(array);
			break;
		case 2:
			println("Ziel-Box Arrays: ");

		}
	}
	
	public static void printArrays(Figure array[]) {

		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				print("[" + array[i].hasPlayer().getPlayerID()
						+ ":" + array[i].getFigureID() + "]");
			}else
				print("[ ]");
		}
		println("");
		println("");
	}
	
	public static void printActiveFigures(Figure array[]){
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
			}else{
				printFigureStatus(array[i]);
			}
		}
		println("");
	}
	
//	public static void printStatusLine(int i){
//		switch (i)
//		{
//		case 1:
//			System.out.println("Spieler");
//		
//		}
//				
//	}
	
	public static void printFigureStatus(Figure fig){
		println("-----------------------------------------------");
		println("PlayerID: " + fig.getPlayerID());
		println("FigurID: " + fig.getFigureID());
		println("    Figurposition im Spielfeld: " + fig.getFigurePos());
		println("    FigurWegLaenge: " + fig.getWeglaenge());
		println("-----------------------------------------------");
	}
	
	public static void printMoveForward(Figure fig, int roll ){
		println("Spieler " + fig.getPlayerID() + ":Figur " + fig.getFigureID() + "wurde ausgewählt");
		println("    Bewegt sich um " + roll + " Schritte nach vorne");
	}
	
	public static void printDice(int playerID, int value){
		println("Player " + playerID + " würfel: ");
		switch (value){
		case 1:
			println(" -------");
			println("|       |");
			println("|   O   |");
			println("|       |"); 
			println(" ------- ");
			break;

		case 2:
			println(" -------");
			println("|     O |");
			println("|       |");
			println("| O     |");
			println(" ------- ");
			break;
		case 3:
			println(" -------");
			println("|     O |");
			println("|   O   |");
			println("| O     |");
			println(" ------- ");
			break;
		case 4:
			println(" -------");
			println("| O   O |");
			println("|       |");
			println("| O   O |");
			println(" ------- ");
			break;
		case 5:
			println(" -------");
			println("| O   O |");
			println("|   O   |");
			println("| O   O |");
			println(" ------- ");
			break;
		case 6:
			println(" -------");
			println("| O   O |");
			println("| O   O |");
			println("| O   O |");
			println(" ------- ");
			break;
		}
		
	}
	public static void println(String sg){
		logger.info(sg + "\n");
	}
	public static void print(String sg){
		logger.info(sg);
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
