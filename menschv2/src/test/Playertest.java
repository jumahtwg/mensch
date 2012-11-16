package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Models.Dice;
import Models.Player;

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
