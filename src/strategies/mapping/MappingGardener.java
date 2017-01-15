package strategies.mapping;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;
import common.robots.Gardener;
import strategies.maxproduction.WateringGardener;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MappingGardener extends WateringGardener {

    private final MappingMemory memory;

    public MappingGardener(RobotController r) {
        super(r);
        memory = new MappingMemory(r);
    }

    @Override
    public void step() throws GameActionException {
        if (!memory.hasScout()) {
            boolean success = buildInAnyDirection(RobotType.SCOUT);
            if (success) {
                memory.setHasScout(true);
            }
        } else {
            super.step();
        }
    }
}
