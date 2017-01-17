package strategies.sixpool;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
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


    }

    private boolean awaitGrid() throws GameActionException {
        if ((grid = mappingMemory.getGrid()) == null) {
            return true;
        }
        return false;
    }

}
