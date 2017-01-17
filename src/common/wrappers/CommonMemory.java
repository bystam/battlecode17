package common.wrappers;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

/**
 * Created by fredrikbystam on 17/01/17.
 */
public class CommonMemory extends MemoryBase {

    public CommonMemory(RobotController rc) {
        super(rc, Offsets.COMMON);
    }


    public int getRobotBuildCount(RobotType type) throws GameActionException {
        return getInt(getIndexForType(type));
    }

    public int incrementAndGetRobotBuildCount(RobotType type) throws GameActionException {
        int count = getRobotBuildCount(type);
        setInt(getIndexForType(type), count + 1);
        return count;
    }

    private int getIndexForType(RobotType type) {
        switch (type) {
            case ARCHON: return 0;
            case GARDENER: return 1;
            case LUMBERJACK: return 2;
            case SOLDIER: return 3;
            case TANK: return 4;
            case SCOUT: return 5;
        }
        return -1;
    }
}
