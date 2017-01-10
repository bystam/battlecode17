package examplefuncsplayer;

import static org.junit.Assert.*;

import battlecode.world.RobotControllerImpl;
import org.junit.Test;

public class RobotPlayerTest {

	@Test
	public void testSanity() {

		RobotPlayer player = new RobotPlayer();
		assertEquals(2, 1+1);
	}

}
