package model;

import static org.junit.Assert.*;

import model.Dice;
import model.Player;

import org.junit.Before;
import org.junit.Test;


public class Playertest {

	Player plyr;
	
	@Before
	public void setUp() {
		plyr = new Player("Bob", 2);

	}
	
	
	@Test
	public void test() {
		assertEquals("Bob" , plyr.getName());
		assertEquals(2, plyr.getPlayerID());
		
	}

}
