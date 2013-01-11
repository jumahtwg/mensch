package controller;

import org.apache.log4j.PropertyConfigurator;

public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
    	PropertyConfigurator.configure("log4j.properties");
    	Controller game = new Controller();
	game.init();
	
    }

}
