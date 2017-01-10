package players.jens;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.robots.Gardener;
import strategies.maxproduction.MaxProductionArchon;
import strategies.maxproduction.PlantingGardener;
import strategies.maxproduction.WateringGardener;

/**
 * Created by jens on 2017-01-10.
 */
public class RobotPlayer {

    public static void run(RobotController rc) throws GameActionException {
        switch (rc.getType()) {
            case ARCHON:
                new MaxProductionArchon(rc).run();
                break;
            case GARDENER:
                getGardener(rc).run();
                break;
        }
    }

    private static Gardener getGardener(RobotController rc){
        return Math.random() > 0.3 ? new PlantingGardener(rc) : new WateringGardener(rc);
    }
}
