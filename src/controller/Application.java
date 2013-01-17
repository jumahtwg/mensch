package controller;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import view.GUI;
import view.TextGUI;

public class Application {

    /**
     * @param args
     * @throws IOException 
     * @throws NumberFormatException 
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
    	PropertyConfigurator.configure("log4j.properties");
    	

    	   	
    	Controller game = new Controller();

    	
    	TextGUI tui = new TextGUI(game);
    	GUI gui = new GUI(game);
		Scanner in = new Scanner(System.in);
		System.out.println("Anzahl der Spieler eingeben");
		Controller.setPl(in.nextInt());
		System.out.println("pl ist " + Controller.getPl());
		
		while (Controller.getPl() > Controller.getMaxspieler() ) {
			System.out.println("Maximale Spieleranzahl: 4, bitte eingeben.");
			Controller.setPl(in.nextInt());
		}
    	game.addObserver(tui);
    	game.addObserver(gui);
    	game.init();
	
    }

}
