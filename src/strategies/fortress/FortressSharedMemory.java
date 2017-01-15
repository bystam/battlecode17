package strategies.fortress;

import battlecode.common.RobotController;
import common.wrappers.MemoryBase;

/**
 * Created by jens on 2017-01-15.
 */
public class FortressSharedMemory extends MemoryBase {

    private static final int ROBOT_COUNT_INDEX = 0;


    public FortressSharedMemory(RobotController rc) {
        super(rc, Offsets.FORTRESS);
    }

    public int getAndIncrementCurrentIndex() {
        try{
            int index = this.getInt(ROBOT_COUNT_INDEX);
            this.setInt(ROBOT_COUNT_INDEX, index+1);
            return index;
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
