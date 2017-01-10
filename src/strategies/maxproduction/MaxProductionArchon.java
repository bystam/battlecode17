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
        while(!hireInAnyDirection()){
            moveInAnyDirection();
        }
    }

    private void moveInAnyDirection() throws GameActionException{
        for(Direction d : tools.getDirections()){
            if(canMove(d)){
                move(d);
            }
        }
    }

    private boolean hireInAnyDirection() throws GameActionException{
        boolean hasHired = false;
        for(Direction d : tools.getDirections()){
            if(this.canHireGardener(d)){
                this.hireGardener(d);
                break;
            } else{
                System.out.println("CANT HIRE A GARDENER");
            }
        }
        return hasHired;
    }
}
