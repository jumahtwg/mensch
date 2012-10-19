
package test;
import java.awt.Color;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import data.Player;


public class PlayerTest {
	
	Player player1;
	@Before
	public void setUp() {
		player1 = new Player("Math",Color.BLUE,1);
	}
	
	@Test
	public void test() {
		
		player1.getFig1();
		
	}
}
