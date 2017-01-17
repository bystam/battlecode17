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
    private MapGrid grid;


    private final int initialStepCount;
    private int stepsTakenFromBirth;

    private static final int TREE_LIMIT = 5;
    private int treeBuildCount;
    private boolean hasCreatedLumberjack;

    private MapLocation topLocation;
    private MapLocation bottomLocation;
    private Direction moveDirection;

    public SixPoolGardener(RobotController r) {
        super(r);
        mappingMemory = new MappingMemory(r);
        initialStepCount = 8 * serialNumberOfType + 5;

        topLocation = getLocation();
        bottomLocation = getLocation();
    }

    @Override
    public void step() throws GameActionException {

        if (produceScout()) return;
        if (awaitGrid()) return;
        if (findInitialPosition()) {
            return;
        }

        if (plantTreeIfPossible()) return;

        buildSoldierIfAppropriate();
        buildLumberjackIfAppropriate();

        if (water()) return;

        if (isBuildReady() && hasTreeBuildRequirements()) {
            if (treeLimitIsReached()) {
                moveDirection = moveDirection.opposite();
            }

            if (canMove(moveDirection)) {
                move(moveDirection);
            } else {
                moveDirection = moveDirection.opposite();
            }
        }
    }

    private boolean treeLimitIsReached() {
        MapLocation location = getLocation();
        return treeBuildCount >= TREE_LIMIT && (location.y >= topLocation.y || location.y <= bottomLocation.y);
    }

    private void buildSoldierIfAppropriate() throws GameActionException {
        Direction soldierDirection = Direction.getWest();
        if (map.getTeamBullets() > 300 && canBuildRobot(RobotType.SOLDIER, soldierDirection)) {
            buildRobot(RobotType.SOLDIER, soldierDirection);
        }
    }

    private void buildLumberjackIfAppropriate() throws GameActionException {
        Direction lumberjackDirection = Direction.getWest();
        if (!hasCreatedLumberjack) {
            if (canBuildRobot(RobotType.LUMBERJACK, lumberjackDirection)) {
                buildRobot(RobotType.LUMBERJACK, lumberjackDirection);
                hasCreatedLumberjack = true;
            }
        }
    }

    private boolean water() throws GameActionException {
        TreeInfo nearbyTree = getTreeToTheRightOfMe();

        if (nearbyTree != null) {
            float missingHealth = nearbyTree.getMaxHealth() - nearbyTree.getHealth();
            if (missingHealth > 10 && canWater(nearbyTree.getID())) {
                water(nearbyTree.getID());
                return true;
            }
        }
        return false;
    }

    private boolean plantTreeIfPossible() throws GameActionException {
        if (treeLimitIsReached()) {
            return false;
        }

        Direction plantDirection = Direction.getEast();
        if (canPlantTree(plantDirection)) {
            plantTree(plantDirection);

            treeBuildCount++;

            MapLocation location = getLocation();
            if (location.y > topLocation.y) {
                topLocation = location;
            }
            if (location.y < bottomLocation.y) {
                bottomLocation = location;
            }

            return true;
        }
        return false;
    }

    private boolean produceScout() throws GameActionException {
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
        if (stepsTakenFromBirth < initialStepCount) {
            if (pathFindingAlgorithmMoveTowards(grid.getCenter())) {
                ++stepsTakenFromBirth;
            }

            if (stepsTakenFromBirth == initialStepCount) {
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
