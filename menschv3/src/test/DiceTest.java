package test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import Models.Dice;

public class DiceTest {
    Dice d;
    
    @Before
    public void setUp(){
	d = new Dice();
    }
    
    @Test
    public void test() {
	int x = d.roll();
	assertTrue(((x >= 1) && (x <=6)));
    }

}
