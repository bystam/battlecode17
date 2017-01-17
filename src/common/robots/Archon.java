package common.robots;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

/**
 * Created by jens on 2017-01-10.
 */
public abstract class Archon extends RobotBase{

    public Archon(RobotController r) {
        super(r);
    }

    public boolean canBuildRobot(RobotType robotType, Direction direction) {
        return rc.canBuildRobot(robotType, direction);
    }


    public void buildRobot(RobotType robotType, Direction direction) throws GameActionException {
        rc.buildRobot(robotType, direction);
    }

    public boolean canHireGardener(Direction direction) {
        return rc.canHireGardener(direction);
    }


    public void hireGardener(Direction direction) throws GameActionException {
        rc.hireGardener(direction);
    }

    protected boolean hireInAnyDirection() throws GameActionException {
        for(Direction d : tools.getDirections()){
            if(canHireGardener(d)){
                hireGardener(d);
                return true;
            }
        }
        return false;
    }


    public boolean hasRobotBuildRequirements(RobotType robotType) {
        return rc.hasRobotBuildRequirements(robotType);
    }

    public boolean isBuildReady() {
        return rc.isBuildReady();
    }
}
