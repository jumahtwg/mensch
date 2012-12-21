package view;

import java.util.Scanner;

import model.Figure;
import model.Player;

public class TextGUI {
	private static final int eins = 1;
	private static final int zwei = 2;
	private static final int drei = 3;
	private static final int vier = 4;
	private static final int fuenf = 5;
	private static final int sechs = 6;
	

	public static void printArrayInformation(Figure array[], int i) {
		switch(i){
		case 1:
			System.out.println("Spielfeld Array: ");
			printArrays(array);
			break;
		case 2:
			System.out.println("Ziel-Box Arrays: ");

		}
	}
	
	
	
	public static void printArrays(Figure array[]) {

		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				System.out.print("[" + array[i].hasPlayer().getPlayerID()
						+ ":" + array[i].getFigureID() + "]");
			} else {
				System.out.print("[ ]");
			}
		}
		System.out.println();
		System.out.println();
	}
	
	public static void printActiveFigures(Figure array[]){
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				return;
			}else{
				printFigureStatus(array[i]);
			}
		}
		System.out.println();
	}
	

	
	public static void printFigureStatus(Figure fig){
		System.out.println("-----------------------------------------------");
		System.out.println("PlayerID: " + fig.getPlayerID());
		System.out.println("FigurID: " + fig.getFigureID());
		System.out.println("    Figurposition im Spielfeld: " + fig.getFigurePos());
		System.out.println("    FigurWegLaenge: " + fig.getWeglaenge());
		System.out.println("-----------------------------------------------");
	}
	
	public static void printMoveForward(Figure fig, int roll ){
		System.out.println("Spieler " + fig.getPlayerID() + ":Figur " + fig.getFigureID() + "wurde ausgewählt");
		System.out.println("    Bewegt sich um " + roll + " Schritte nach vorne");
	}
	
	public static void printDice(int playerID, int value){
		System.out.println("Player " + playerID + " würfel: ");

		switch (value){
		case eins:
			System.out.println(" -------");
			System.out.println("|       |");
			System.out.println("|   O   |");
			System.out.println("|       |"); 
			System.out.println(" ------- ");
			break;

		case zwei:
			System.out.println(" -------");
			System.out.println("|     O |");
			System.out.println("|       |");
			System.out.println("| O     |");
			System.out.println(" ------- ");
			break;
		case drei:
			System.out.println(" -------");
			System.out.println("|     O |");
			System.out.println("|   O   |");
			System.out.println("| O     |");
			System.out.println(" ------- ");
			break;
		case vier:
			System.out.println(" -------");
			System.out.println("| O   O |");
			System.out.println("|       |");
			System.out.println("| O   O |");
			System.out.println(" ------- ");
			break;
		case fuenf:
			System.out.println(" -------");
			System.out.println("| O   O |");
			System.out.println("|   O   |");
			System.out.println("| O   O |");
			System.out.println(" ------- ");
			break;
		case sechs:
			System.out.println(" -------");
			System.out.println("| O   O |");
			System.out.println("| O   O |");
			System.out.println("| O   O |");
			System.out.println(" ------- ");
			break;
		}
	}
}
