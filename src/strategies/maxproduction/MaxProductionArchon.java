package strategies.maxproduction;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.world.RobotControllerImpl;
import common.robots.Archon;

import java.util.Arrays;

/**
 * Created by fredrikbystam on 10/01/17.
 */
public class MaxProductionArchon extends Archon {

    enum DIR {
        EAST(),
        WEST
    }

    public MaxProductionArchon(RobotController r) {
        super(r);
    }

    @Override
    public void step() throws GameActionException{
        /*if(map.getTeamBullets() > 120){
            tools.donate(map.getTeamBullets());
        }*/
        while(!hireInAnyDirection() && !hasMoved()){
            moveInAnyDirection();
        }
    }
}
