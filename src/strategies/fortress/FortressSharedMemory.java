package strategies.fortress;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.wrappers.MemoryBase;

/**
 * Created by jens on 2017-01-15.
 */
public class FortressSharedMemory extends MemoryBase {

    private static final int GARDENER_COUNT_INDEX = 0;
    private static final int LUMBERJACK_COUNT_INDEX = 1;


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
}
