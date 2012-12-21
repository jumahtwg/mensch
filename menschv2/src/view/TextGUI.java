package view;

import java.util.Scanner;

import model.Figure;
import model.Player;

public class TextGUI {

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
			}else
				System.out.print("[ ]");
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
		case 1:
			System.out.println(" -------");
			System.out.println("|       |");
			System.out.println("|   O   |");
			System.out.println("|       |"); 
			System.out.println(" ------- ");
			break;

		case 2:
			System.out.println(" -------");
			System.out.println("|     O |");
			System.out.println("|       |");
			System.out.println("| O     |");
			System.out.println(" ------- ");
			break;
		case 3:
			System.out.println(" -------");
			System.out.println("|     O |");
			System.out.println("|   O   |");
			System.out.println("| O     |");
			System.out.println(" ------- ");
			break;
		case 4:
			System.out.println(" -------");
			System.out.println("| O   O |");
			System.out.println("|       |");
			System.out.println("| O   O |");
			System.out.println(" ------- ");
			break;
		case 5:
			System.out.println(" -------");
			System.out.println("| O   O |");
			System.out.println("|   O   |");
			System.out.println("| O   O |");
			System.out.println(" ------- ");
			break;
		case 6:
			System.out.println(" -------");
			System.out.println("| O   O |");
			System.out.println("| O   O |");
			System.out.println("| O   O |");
			System.out.println(" ------- ");
			break;
		}
	}
}
