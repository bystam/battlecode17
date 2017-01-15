package common.wrappers;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MemoryBase {

    public final class Offsets {
        // eller nått
        public static final int COMMON = 0;
        public static final int MAX_PRODCTION = 1000;
        public static final int JUNGLER = 2000;
        public static final int MAPPING = 3000;

        private Offsets() {}
    }

    private final RobotController rc;
    private final int offset;

    protected MemoryBase(RobotController rc, int offset) {
        this.rc = rc;
        this.offset = offset;
    }

    // locations

    protected MapLocation getLocation(int i) throws GameActionException {
        i += offset;

        int both = rc.readBroadcast(i);
        int x = both & 0xFFFF;
        int y = (both >> 16) & 0xFFFF;
        return new MapLocation(x, y);
    }

    protected void setLocation(MapLocation location, int i) throws GameActionException {
        i += offset;

        int x = (int)location.x;
        int y = (int)location.y;
        int both = (y << 16) | x;
        rc.broadcast(i, both);
    }

    protected boolean isBitSet(int i, int bit) throws GameActionException {
        i += offset;

        int data = rc.readBroadcast(i);
        int mask = (1 << bit);
        return (data & mask) != 0;
    }

    protected void setBit(int i, int bit, boolean set) throws GameActionException {
        i += offset;

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
