package common.wrappers;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

/**
 * Used to wrap the GameConstants.TEAM_MEMORY_LENGTH integers
 * where information can be stored
 */
public final class SharedMemory {

    // booleans
    private static final int BOOL_FIELD = 16;
    private static final int HAS_SCOUT_BIT = 0;

    private final RobotController rc;

    public SharedMemory(RobotController rc) {
        this.rc = rc;
    }

    public boolean hasScout() throws GameActionException {
        return isBitSet(BOOL_FIELD, HAS_SCOUT_BIT);
    }

    public void setHasScout(boolean hasScout) throws GameActionException {
        setBit(BOOL_FIELD, HAS_SCOUT_BIT, hasScout);
    }

    // locations

    private MapLocation getLocation(int i) throws GameActionException {
        int both = rc.readBroadcast(i);
        int x = both & 0xFFFF;
        int y = (both >> 16) & 0xFFFF;
        return new MapLocation(x, y);
    }

    private void setLocation(MapLocation location, int i) throws GameActionException {
        int x = (int)location.x;
        int y = (int)location.y;
        int both = (y << 16) | x;
        rc.broadcast(i, both);
    }


    // booleans

    private boolean isBitSet(int i, int bit) throws GameActionException {
        int data = rc.readBroadcast(i);
        int mask = (1 << bit);
        return (data & mask) != 0;
    }

    private void setBit(int i, int bit, boolean set) throws GameActionException {
        int data = rc.readBroadcast(i);
        int mask = (1 << bit);
        if (set) {
            data = data | mask;
        } else {
            data = data & ~mask;
        }
        rc.broadcast(i, data);
    }
}
