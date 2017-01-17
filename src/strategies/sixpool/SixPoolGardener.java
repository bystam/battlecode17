package strategies.sixpool;

import battlecode.common.*;
import common.model.MapGrid;
import common.robots.Gardener;
import strategies.mapping.MappingMemory;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by fredrikbystam on 17/01/17.
 */
public class SixPoolGardener extends Gardener {

    private final MappingMemory mappingMemory;

    private static final int STEP_AWAY_COUNT = 5;
    private int stepsTakenFromBirth;
    private boolean hasCreatedLumberjack;

    private MapGrid grid;

    private MapLocation startLocation;
    private Direction moveDirection;

    public SixPoolGardener(RobotController r) {
        super(r);
        mappingMemory = new MappingMemory(r);
    }

    @Override
    public void step() throws GameActionException {

        if (procudeScout()) return;
        if (awaitGrid()) return;
        if (findInitialPosition()) return;

        Direction plantDirection = Direction.getEast();
        if (canPlantTree(plantDirection)) {
            plantTree(plantDirection );
            return;
        }

        TreeInfo nearbyTree = getTreeToTheRightOfMe();
        float missingHealth = nearbyTree.getMaxHealth() - nearbyTree.getHealth();
        if (nearbyTree != null && missingHealth < -10 && canWater(nearbyTree.getID())){
            water(nearbyTree.getID());
            return;
        }

        if (isBuildReady() && hasTreeBuildRequirements()) {
            if (canMove(moveDirection)) {
                move(moveDirection);
            } else {
                moveDirection = moveDirection.opposite();
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

    private boolean procudeScout() throws GameActionException {
        if (!mappingMemory.hasScout()) {
            boolean success = buildInAnyDirection(RobotType.SCOUT);
            if (success) {
                mappingMemory.setHasScout(true);
            }
            return true;
        }
        return false;
    }

    private boolean awaitGrid() throws GameActionException {
        if ((grid = mappingMemory.getGrid()) == null) {
            return true;
        }
        return false;
    }

    private boolean findInitialPosition() throws GameActionException {
        if (stepsTakenFromBirth < STEP_AWAY_COUNT) {
            if (canMove(grid.getCenter())) {
                move(grid.getCenter());
                ++stepsTakenFromBirth;
            }
            if (stepsTakenFromBirth == STEP_AWAY_COUNT) {
                startLocation = getLocation();
                moveDirection = Direction.getNorth();
            }
            return true;
        }
        return false;
    }

    private TreeInfo getTreeToTheRightOfMe() {
        Optional<TreeInfo> tree =
                Arrays.stream(senseNearbyTrees(-1, getTeam()))
                        .filter( (t) -> t.getLocation().x > getLocation().x )
                        .sorted( (t1, t2) -> {
                            return Float.compare(t1.getLocation().distanceTo(getLocation()),
                                                 t2.getLocation().distanceTo(getLocation()));
                        })
                        .findFirst();
        return tree.isPresent() ? tree.get() : null;
    }
}
