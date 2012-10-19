package test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import data.Dice;
import data.Grid;

public class DiceTest extends TestCase {

	Dice dice;
	
	@Before
	public void setUp() {
		dice = new Dice();

	}
	
	@Test
	public void test() {
		for (int i=0; i < 19900; i++) {
			dice.createNewDice();					
			assertTrue(dice.getDice() < 7 && dice.getDice() > 0);
		}
		
	}

}
