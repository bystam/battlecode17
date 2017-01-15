package players.bystam;

import battlecode.common.*;
import common.robots.Gardener;
import strategies.jungler.JunglerGardener;
import strategies.jungler.JunglerLumberjack;
import strategies.maxproduction.MaxProductionArchon;
import strategies.maxproduction.PlantingGardener;
import strategies.maxproduction.WateringGardener;

import java.util.Random;

public strictfp class RobotPlayer {

    public static void run(RobotController rc) throws GameActionException {
        switch (rc.getType()) {
            case ARCHON:
                new MaxProductionArchon(rc).run();
                break;
            case GARDENER:
                getGardener(rc).run();
                break;
            case LUMBERJACK:
                new JunglerLumberjack(rc).run();
                break;
        }
    }

    private static Gardener getGardener(RobotController rc) {
        int id = rc.getID() % 3;
        if (id == 1) {
            return new WateringGardener(rc);
        } else if (id == 2) {
            return new JunglerGardener(rc);
        }
        return new PlantingGardener(rc);
    }
}
