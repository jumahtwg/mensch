package test;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import data.Grid;

public class GridTest extends TestCase {

	Grid grid;
	
	@Before
	public void setUp() {
		grid = new Grid(4);
	}
	
	@Test
	public void test() {
		assertEquals(4, grid.getAnzMit());
		grid.setAnzMit(2);
		assertEquals(2, grid.getAnzMit());
	}

}
