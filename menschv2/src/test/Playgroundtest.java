package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Models.Player;
import Models.Playground;

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
