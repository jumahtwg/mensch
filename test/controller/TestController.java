package controller;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestController extends TestCase {

	Controller ctrl;
	
	@Before
	public void setUp() {
		ctrl = new Controller();
		ctrl.setPl(2);
		

	}
	@Test
	public void test() throws NumberFormatException, IOException {
		
		ctrl.init();
		
		assertEquals(2, ctrl.getPl()); 
		assertEquals(4, Controller.getMaxspieler());
		ctrl.comingOut(0);
	}

}
