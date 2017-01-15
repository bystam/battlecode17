package players.jens;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.robots.Gardener;
import strategies.fortress.FortressArchon;
import strategies.fortress.FortressGardener;
import strategies.fortress.FortressSharedMemory;

/**
 * Created by jens on 2017-01-10.
 */
public class RobotPlayer {

    public static void run(RobotController rc) throws GameActionException {
        switch (rc.getType()) {
            case ARCHON:
                new FortressArchon(rc).run();
                break;
            case GARDENER:
                getGardener(rc).run();
                break;
        }
    }

    private static Gardener getGardener(RobotController rc){
        return new FortressGardener(rc, getRobotIndex(rc));
    }

    private static int getRobotIndex(RobotController rc){
        FortressSharedMemory memory = new FortressSharedMemory(rc);
        return memory.getAndIncrementCurrentIndex();
    }
}
