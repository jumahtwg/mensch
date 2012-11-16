package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Models.Figure;
import Models.Player;

public class FigureTest {

    Figure fig;
    Player play;
    
    @Before
    public void setUp(){
	play = new Player("Bernd", 1);
	fig = new Figure(1,play);
    }
    @Test
    public void test() {
	fig.setFigurePos(5);
	assertEquals(5,fig.getFigurePos());
	assertEquals(1,fig.getFigureID());
	assertEquals(play, fig.getFigurePlayer());
	fig.resetWegLaenge();
	assertEquals(0,fig.getFigurePos());
    }

}
