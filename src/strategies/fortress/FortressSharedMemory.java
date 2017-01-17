package strategies.fortress;

import battlecode.common.RobotController;
import common.wrappers.MemoryBase;

/**
 * Created by jens on 2017-01-15.
 */
public class FortressSharedMemory extends MemoryBase {

    private static final int GARDENER_COUNT_INDEX = 0;
    private static final int LUMBERJACK_COUNT_INDEX = 0;


    public FortressSharedMemory(RobotController rc) {
        super(rc, Offsets.FORTRESS);
    }


    public int getAndSetGardenerIndex() {
        try{
            int index = this.getInt(GARDENER_COUNT_INDEX);
            this.setInt(GARDENER_COUNT_INDEX, index+1);
            return index;
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
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

    public int getLumberjackCount(){
        return LUMBERJACK_COUNT_INDEX;
    }

    public int getGardenerCount(){
        return GARDENER_COUNT_INDEX;
    }
}
