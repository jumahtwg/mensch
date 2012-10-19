package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.Figure;

public class FigureTest {

	Figure fig1;
	Figure fig2;
	Figure fig3;
	Figure fig4;
	@Before
	public void setUp() {
		fig1 = new Figure();
		fig2 = new Figure();
		fig3 = new Figure();
		fig4 = new Figure();
		
	}
	
	@Test
	public void test() {
		fig1.setFigure1(0);
		assertEquals(0, fig1.getFigure1());
		
		fig2.setFigure2(10);
		assertEquals(10, fig2.getFigure2());
		
		fig3.setFigure3(5);
		assertEquals(5, fig3.getFigure3());
		
		fig4.setFigure4(20);
		assertEquals(20, fig4.getFigure4());
	}

}
