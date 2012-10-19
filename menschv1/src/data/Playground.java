package data;

import java.util.*;

public class Playground {

	private int anzMit;
	private Field fields[];
	
	private List<Player> players = new LinkedList<Player>();
	private Field storage1[];
	private Field storage2[];
	private Field storage3[];
	private Field storage4[];
	
		
	public Playground(int anzMit) {
		this.anzMit = anzMit;
		this.fields = new Field[40];
		this.storage1 = new Field[4];
		this.storage2 = new Field[4];
		this.storage3 = new Field[4];
		this.storage4 = new Field[4];
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
	
	public Field getFieldStatus(int fieldNr){
		return this.fields[fieldNr];
	}
	
	public Field getStorage1Status(int storageNr){
		return this.storage1[storageNr];
	}
	public Field getStorage2Status(int storageNr){
		return this.storage2[storageNr];
	}
	public Field getStorage3Status(int storageNr){
		return this.storage3[storageNr];
	}
	public Field getStorage4Status(int storageNr){
		return this.storage4[storageNr];
	}
	


	
}
