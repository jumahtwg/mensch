package model;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import model.Dice;

import org.junit.Before;
import org.junit.Test;


public class Dicetest extends TestCase {

	
	private Dice dice;
	
	@Before
	public void setUp() {
		dice = new Dice();

	}
	
	@Test
	public void testRoll() {
		for (int i=0; i < 19900; i++) {
			int x = dice.roll();					
			assertTrue(x < 7 && x > 0);
		}
		
	}

}
