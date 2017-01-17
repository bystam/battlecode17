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
        int count = getInt(getIndexForType(type));
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

    private int getHeartbeatIndexForType(RobotType type) {
        switch (type) {
            case ARCHON: return 6;
            case GARDENER: return 8;
            case LUMBERJACK: return 10;
            case SOLDIER: return 12;
            case TANK: return 14;
            case SCOUT: return 16;
        }
        return -1;
    }

    public int getRobotCount(RobotType type) throws GameActionException {
        return getInt(getHeartbeatIndexForType(type) + 1);
    }

    public void wipeAndSaveHeartbeats() throws GameActionException {
        for(RobotType type : RobotType.values()){
            setInt(getHeartbeatIndexForType(type)+1, getInt(getHeartbeatIndexForType(type)));
            setInt(getHeartbeatIndexForType(type), 0);
        }
    }

    public void incrementHeartbeat(RobotType type) throws GameActionException {
        int i = getHeartbeatIndexForType(type);
        setInt(i, getInt(i)+1);
    }
}
