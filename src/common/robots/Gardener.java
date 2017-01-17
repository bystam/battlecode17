package common.robots;

import battlecode.common.*;

import java.util.Arrays;

/**
 * Created by jens on 2017-01-10.
 */
public abstract class Gardener extends RobotBase {

    protected final RobotInfo archonCreator;

    public Gardener(RobotController r) {
        super(r);
        archonCreator = getArchonCreator();
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

    private RobotInfo getArchonCreator() {
        return Arrays.stream(senseNearbyRobots())
                .filter( (r) -> {
                    return r.getType() == RobotType.ARCHON && r.getTeam() == getTeam();
                })
                .sorted( (a1, a2) -> {
                    return Float.compare(a1.getLocation().distanceTo(getLocation()),
                                         a2.getLocation().distanceTo(getLocation()));
                })
                .findFirst().get();
    }

    public boolean buildInAnyDirection(RobotType robotType) throws GameActionException {
        for (Direction direction : tools.getDirections()) {
            if (canBuildRobot(robotType, direction)) {
                buildRobot(robotType, direction);
                return true;
            }
        }
        return false;
    }
}
