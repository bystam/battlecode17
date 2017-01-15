package common.robots;

import battlecode.common.*;

/**
 * Created by jens on 2017-01-10.
 */
public abstract class Gardener extends RobotBase {

    public Gardener(RobotController r) {
        super(r);
    }

    public boolean canWater(MapLocation mapLocation) {
        return rc.canWater(mapLocation);
    }


    public boolean canWater(int i) {
        return rc.canWater(i);
    }


    public void water(MapLocation mapLocation) throws GameActionException {
        rc.water(mapLocation);
    }


    public void water(int i) throws GameActionException {
        rc.water(i);
    }

    public boolean canWater() {
        return rc.canWater();
    }


    public boolean hasTreeBuildRequirements() {
        return rc.hasTreeBuildRequirements();
    }

    public boolean canPlantTree(Direction direction) {
        return rc.canPlantTree(direction);
    }

    public void plantTree(Direction direction) throws GameActionException {
        rc.plantTree(direction);
    }

    public boolean canBuildRobot(RobotType robotType, Direction direction) {
        return rc.canBuildRobot(robotType, direction);
    }

    public void buildRobot(RobotType robotType, Direction direction) throws GameActionException {
        rc.buildRobot(robotType, direction);
    }

    public boolean hasRobotBuildRequirements(RobotType robotType) {
        return rc.hasRobotBuildRequirements(robotType);
    }

    public boolean isBuildReady() {
        return rc.isBuildReady();
    }
}
