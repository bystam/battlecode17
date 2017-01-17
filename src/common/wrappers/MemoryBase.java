package common.wrappers;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MemoryBase {

    public final class Offsets {

        public static final int COMMON = 0;
        public static final int MAPPING = 300;
        public static final int FORTRESS = 400;


        private Offsets() {}
    }

    protected final RobotController rc;
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
        if(x == -1 && y == -1){
            return null;
        }
        return new MapLocation(x, y);
    }

    protected void setLocation(MapLocation location, int i) throws GameActionException {
        if(location == null){
            location = new MapLocation(-1,-1);
        }
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

    protected int getInt(int i) throws GameActionException {
        i += offset;
        return rc.readBroadcast(i);
    }

    protected void setInt(int i, int data) throws GameActionException {
        i += offset;
        rc.broadcast(i, data);
    }
}
