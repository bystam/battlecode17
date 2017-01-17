package strategies.fortress;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import common.wrappers.MemoryBase;

/**
 * Created by jens on 2017-01-15.
 */
public class FortressSharedMemory extends MemoryBase {

    private static final int GARDENER_COUNT_INDEX = 0;
    private static final int LUMBERJACK_COUNT_INDEX = 1;
    private static final int MURDERER_JOB_INDEX = 2;


    public FortressSharedMemory(RobotController rc) {
        super(rc, Offsets.FORTRESS);
    }


    public int getAndSetGardenerIndex() throws GameActionException{
        int index = this.getInt(GARDENER_COUNT_INDEX);
        this.setInt(GARDENER_COUNT_INDEX, index+1);
        return index;
    }

    public int getAndSetLumberjackIndex() {
        try{
            int index = this.getInt(LUMBERJACK_COUNT_INDEX);
            this.setInt(LUMBERJACK_COUNT_INDEX, index+1);
            return index;
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getLumberjackCount() throws GameActionException{
        return this.getInt(LUMBERJACK_COUNT_INDEX);
    }

    public int getGardenerCount() throws GameActionException{
        return this.getInt(GARDENER_COUNT_INDEX);
    }

    public MapLocation popMurdererJob() throws GameActionException {
        if(getCurrentMurdererJob() == null){
            return null;
        }
        MapLocation loc = this.getLocation(MURDERER_JOB_INDEX);
        this.setLocation(null, MURDERER_JOB_INDEX);
        return loc;
    }

    public MapLocation getCurrentMurdererJob() throws GameActionException {
        int current = this.getInt(MURDERER_JOB_INDEX);
        if(current == 0 || current == -1){
            return null;
        }
        return getLocation(MURDERER_JOB_INDEX);
    }

    public boolean callMurderer(MapLocation location) throws GameActionException {
        MapLocation current = getCurrentMurdererJob();
        if(current == null){
            setLocation(location, MURDERER_JOB_INDEX);
            return true;
        }
        return false;
    }

}
