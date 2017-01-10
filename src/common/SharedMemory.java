package common;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

/**
 * Used to wrap the GameConstants.TEAM_MEMORY_LENGTH integers
 * where information can be stored
 */
public final class SharedMemory {

    private static final int ARCHON_Y = 1;
    private static final int ARCHON_X = 0;

    private final RobotController rc;

    SharedMemory(RobotController rc) {
        this.rc = rc;
    }

    public MapLocation getArchonLocation() throws GameActionException {
        return new MapLocation(rc.readBroadcast(ARCHON_X), rc.readBroadcast(ARCHON_Y));
    }

    public void setArchonLocation(MapLocation location) throws GameActionException {
        rc.broadcast(ARCHON_X, (int)location.x);
        rc.broadcast(ARCHON_Y, (int)location.y);
    }
}
