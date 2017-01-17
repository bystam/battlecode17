package players.jens;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.robots.Gardener;
import common.wrappers.Tools;
import strategies.fortress.FortressArchon;
import strategies.fortress.FortressGardener;
import strategies.fortress.FortressSharedMemory;

/**
 * Created by jens on 2017-01-10.
 */
public class RobotPlayer {

    public static void run(RobotController rc) throws GameActionException {
        Tools tools = new Tools(rc);
        switch (rc.getType()) {
            case ARCHON:
                new FortressArchon(rc, tools.getArchonCornerDirection()).run();
                break;
            case GARDENER:
                getGardener(rc).run();
                break;
        }
    }

    private static Gardener getGardener(RobotController rc) throws GameActionException{
        FortressSharedMemory memory = new FortressSharedMemory(rc);
        return new FortressGardener(rc, memory.getAndSetGardenerIndex());
    }
}
