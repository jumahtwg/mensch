package data;

import java.util.*;

public class Playground {

	private int anzMit;
	private Figure figure[];
	
	
	private Figure target1[];
	private Figure target2[];
	private Figure target3[];
	private Figure target4[];
	
	private List<Player> players = new LinkedList<Player>();
	
		
	public Playground() {
		this.figure = new Figure[40];
		this.target1 = new Figure[4];
		this.target2 = new Figure[4];
		this.target3 = new Figure[4];
		this.target4 = new Figure[4];
		
	}

	public void addPlayer(Player newPlayer){		
		players.add(newPlayer);		
	}
	public int getAnzMit() {
		return anzMit;
	}

	public void setAnzMit(int anzMit) {
		this.anzMit = anzMit;
	}
	
	public Figure getFieldStatus(int fieldNr){
		return this.figure[fieldNr];
	}
	
	public Figure getStorage1Status(int storageNr){
		return this.target1[storageNr];
	}
	public Figure getStorage2Status(int storageNr){
		return this.target2[storageNr];
	}
	public Figure getStorage3Status(int storageNr){
		return this.target3[storageNr];
	}
	public Figure getStorage4Status(int storageNr){
		return this.target4[storageNr];
	}
	


	
}
