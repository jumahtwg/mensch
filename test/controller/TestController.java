package controller;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import controller.Controller.GAME_STATE;

public class TestController extends TestCase {

	Controller ctrl;
	
	@Before
	public void setUp() {
		ctrl = new Controller();
		ctrl.setPl(2);
		ctrl.start();
		

	}
	@Test
	public void test() throws NumberFormatException, IOException {
		ctrl.inputPlayerCount(ctrl.getPl());
		for ( int i = 0; i < 100; i++) {
			ctrl.doDice();
		}
		
		assertEquals(2, ctrl.getPl()); 
		assertEquals(2, ctrl.getAnzahlMitspieler());
		assertEquals(4, Controller.getMaxspieler());
		ctrl.comingOut(0);
		ctrl.setStatus(GAME_STATE.ROLL);
		assertEquals(GAME_STATE.ROLL, ctrl.getStatus());
		ctrl.comingOut(0);
		ctrl.moveForward(ctrl.getFigureOnPos(0), 40);
		ctrl.comingOut(0);
		ctrl.moveForward(ctrl.getFigureOnPos(0), 41);
		ctrl.comingOut(0);
		ctrl.moveForward(ctrl.getFigureOnPos(0), 42);
		ctrl.comingOut(0);
		ctrl.moveForward(ctrl.getFigureOnPos(0), 43);
		ctrl.getPlayerFigures();
		ctrl.getActivePlayer();
		ctrl.getPgArray();
		ctrl.getRoll();
		ctrl.getFieldCoords();
		ctrl.getTargetCoords(0);
		ctrl.getStackSize(0);
		ctrl.getStackCoords();
		ctrl.getTargetFigureArray(0);
		ctrl.setPickFigure(0);
		ctrl.update();
		
		
	}

}
