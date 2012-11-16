package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Models.Dice;
import Models.Figure;
import Models.Player;

public class Figuretest {

	private Figure fig;
	private Player plyr;
	
	@Before
	public void setUp() {
		fig = new Figure(10, plyr);

	}
	
	
	@Test
	public void test() {
		assertEquals(0, fig.getWegLaenge());
		assertEquals(10, fig.getFigureID());
		
	}

}
