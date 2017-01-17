package strategies.sixpool;

import battlecode.common.BodyInfo;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.model.MapGrid;
import common.robots.Tank;
import strategies.mapping.MappingMemory;

/**
 * Created by jens on 2017-01-17.
 */
public class SixPoolTank extends Tank {

    private final MappingMemory mappingMemory;
    private MapGrid grid;


    public SixPoolTank(RobotController r) {
        super(r);
        mappingMemory = new MappingMemory(r);
    }

    @Override
    public void step() throws GameActionException {
        if (awaitGrid()) return;

        if (fireAtAnyOf(senseNearbyRobots(-1, getTeam().opponent()))) return;
        if (fireAtAnyOf(senseNearbyTrees(-1, getTeam().opponent()))) return;

        roam();
    }

    private boolean fireAtAnyOf(BodyInfo[] targets) throws GameActionException {
        if (targets.length > 0) {
            BodyInfo target = targets[0];
            if (canFireTriadShot()) {
                fireTriadShot(getLocation().directionTo(target.getLocation()));
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
