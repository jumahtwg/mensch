package model;

import static org.junit.Assert.*;

import model.Player;
import model.Playground;

import org.junit.Before;
import org.junit.Test;


public class Playgroundtest {

	private Playground plygrnd;
	private Player plr;
	
	@Before
	public void setUp() {
		plygrnd = new Playground();
		plygrnd.addPlayer(plr);

	}
	@Test
	public void test() {
		assertEquals(plr, plr);
		
	}

}
