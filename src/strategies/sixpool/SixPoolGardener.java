package strategies.sixpool;

import battlecode.common.*;
import common.robots.Gardener;
import strategies.mapping.MappingMemory;

/**
 * Created by fredrikbystam on 17/01/17.
 */
public class SixPoolGardener extends Gardener {

    private final MappingMemory mappingMemory;

    private static final int STEP_AWAY_COUNT = 2;
    private int stepsTakenFromBirth;
    private boolean hasCreatedLumberjack;

    private MapLocation startLocation;
    private MapLocation nextTreeLocation;

    public SixPoolGardener(RobotController r) {
        super(r);
        mappingMemory = new MappingMemory(r);
    }

    @Override
    public void step() throws GameActionException {
//        if (!mappingMemory.hasScout()) {
//            boolean success = buildInAnyDirection(RobotType.SCOUT);
//            if (success) {
//                mappingMemory.setHasScout(true);
//            }
//            return;
//        }

        if (stepsTakenFromBirth < STEP_AWAY_COUNT) {
            Direction away = getLocation().directionTo(archonCreator.getLocation()).opposite();
            if (canMove(away)) {
                move(away);
                ++stepsTakenFromBirth;
            }
            if (stepsTakenFromBirth == STEP_AWAY_COUNT) {
                startLocation = nextTreeLocation = getLocation();
            }
            return;
        }

        if (getLocation().distanceTo(nextTreeLocation) > getType().strideRadius) {
            if (canMove(nextTreeLocation)) {
                move(nextTreeLocation);
            }
        } else {
            Direction treeDirection = Direction.getEast();
            if (canPlantTree(treeDirection)) {
                plantTree(treeDirection);
                nextTreeLocation = nextTreeLocation.translate(0, 2 * getType().strideRadius);
            }
        }

//        if (!hasCreatedLumberjack) {
//            boolean success = buildInAnyDirection(RobotType.LUMBERJACK);
//            if (success) {
//                hasCreatedLumberjack = true;
//            }
//            return;
//        }
    }
}
