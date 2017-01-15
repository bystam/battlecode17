package strategies.mapping;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;
import common.robots.Gardener;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MappingGardener extends Gardener {

    public MappingGardener(RobotController r) {
        super(r);
    }

    @Override
    public void step() throws GameActionException {
        if (!memory.hasScout()) {
            boolean success = buildInAnyDirection(RobotType.SCOUT);
            if (success) {
                memory.setHasScout(true);
            }
        }
    }
}
