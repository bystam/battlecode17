package strategies.sixpool;

import battlecode.common.*;
import common.model.MapGrid;
import common.robots.Soldier;
import strategies.mapping.MappingMemory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fredrikbystam on 17/01/17.
 */
public class SixPoolSoldier extends Soldier {

    private final MappingMemory mappingMemory;
    private MapGrid grid;

    private final List<MapLocation> enemyLocations;
    private Direction roamingDirection;

    public SixPoolSoldier(RobotController r) {
        super(r);
        mappingMemory = new MappingMemory(r);
        enemyLocations = new ArrayList<>(Arrays.asList(map.getInitialArchonLocations(getTeam().opponent())));
        roamingDirection = Direction.getNorth();
    }

    @Override
    public void step() throws GameActionException {
        if (awaitGrid()) return;

        if (fireAtAnyOf(senseNearbyRobots(-1, getTeam().opponent()))) return;
        if (fireAtAnyOf(senseNearbyTrees(-1, getTeam().opponent()))) return;

        roam();
    }

    private void roam() throws GameActionException {
        MapLocation target = getTargetLocation();
        if (target != null) {
            pathFindingAlgorithmMoveTowards(getTargetLocation());
        } else {
            if (canMove(roamingDirection)) {
                move(roamingDirection);
                roamingDirection = roamingDirection.rotateLeftDegrees(2);
            } else {
                roamingDirection = roamingDirection.rotateLeftDegrees(45);
                if (canMove(roamingDirection)) {
                    move(roamingDirection);
                    roamingDirection = roamingDirection.rotateLeftDegrees(2);
                }
            }
        }
    }

    private MapLocation getTargetLocation() {
        if (enemyLocations.isEmpty()) {
            return null;
        }

        MapLocation next = enemyLocations.get(0);
        if (getLocation().distanceTo(next) < 3) {
            enemyLocations.remove(0);
            return getTargetLocation();
        }
        return next;
    }

    private boolean fireAtAnyOf(BodyInfo[] targets) throws GameActionException {
        if (targets.length > 0) {
            BodyInfo target = targets[0];
            if (canFireSingleShot()) {
                fireSingleShot(getLocation().directionTo(target.getLocation()));
                return true;
            }
        }
        return false;
    }

    private boolean awaitGrid() throws GameActionException {
        if ((grid = mappingMemory.getGrid()) == null) {
            return true;
        }
        return false;
    }

}
