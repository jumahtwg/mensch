package test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import Models.Dice;

public class Dicetest extends TestCase {

	
	private Dice dice;
	
	@Before
	public void setUp() {
		dice = new Dice();

	}
	
	@Test
	public void test() {
		for (int i=0; i < 19900; i++) {
			int x = dice.roll();					
			assertTrue(x < 7 && x > 0);
		}
		
	}

}
