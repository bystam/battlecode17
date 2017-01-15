package strategies.mapping;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.wrappers.MemoryBase;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MappingMemory extends MemoryBase {

    // booleans
    private static final int BOOL_FIELD = 16;
    private static final int HAS_SCOUT_BIT = 0;

    public MappingMemory(RobotController rc) {
        super(rc, Offsets.MAPPING);
    }

    public boolean hasScout() throws GameActionException {
        return isBitSet(BOOL_FIELD, HAS_SCOUT_BIT);
    }

    public void setHasScout(boolean hasScout) throws GameActionException {
        setBit(BOOL_FIELD, HAS_SCOUT_BIT, hasScout);
    }
}
