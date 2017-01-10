package common;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

/**
 * Used to wrap the GameConstants.TEAM_MEMORY_LENGTH integers
 * where information can be stored
 */
public final class SharedMemory {

    private static final int ARCHON_POS = 0;

    private final RobotController rc;

    SharedMemory(RobotController rc) {
        this.rc = rc;
    }

    public MapLocation getArchonLocation() throws GameActionException {
        return getLocation(ARCHON_POS);
    }

    public void setArchonLocation(MapLocation location) throws GameActionException {
        setLocation(location, ARCHON_POS);
    }

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
}
