package strategies.sixpool;

import battlecode.common.*;
import common.model.MapGrid;
import common.robots.Soldier;
import strategies.mapping.MappingMemory;

/**
 * Created by fredrikbystam on 17/01/17.
 */
public class SixPoolSoldier extends Soldier {

    private final MappingMemory mappingMemory;
    private MapGrid grid;

    private final MapLocation enemyLocation;

    public SixPoolSoldier(RobotController r) {
        super(r);
        mappingMemory = new MappingMemory(r);
        enemyLocation = map.getInitialArchonLocations(getTeam().opponent())[0];
    }

    @Override
    public void step() throws GameActionException {
        if (awaitGrid()) return;

        if (fireAtAnyOf(senseNearbyRobots(-1, getTeam().opponent()))) return;
        if (fireAtAnyOf(senseNearbyTrees(-1, getTeam().opponent()))) return;

        pathFindingAlgorithmMoveTowards(enemyLocation);
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
