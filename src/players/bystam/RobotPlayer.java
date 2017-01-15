package players.bystam;

import battlecode.common.*;
import common.robots.Gardener;
import strategies.jungler.JunglerGardener;
import strategies.jungler.JunglerLumberjack;
import strategies.mapping.MappingGardener;
import strategies.mapping.MappingMemory;
import strategies.maxproduction.MaxProductionArchon;
import strategies.maxproduction.PlantingGardener;
import strategies.maxproduction.WateringGardener;

public strictfp class RobotPlayer {

    private static MappingMemory mappingMemory;

    public static void run(RobotController rc) throws GameActionException {
        mappingMemory = new MappingMemory(rc);

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

    private static Gardener getGardener(RobotController rc) throws GameActionException {
        if (!mappingMemory.hasMappingGardener()) {
            mappingMemory.setHasMappingGardener(true);
            return new MappingGardener(rc);
        }

        int id = rc.getID() % 3;
        if (id == 1) {
            return new WateringGardener(rc);
        } else if (id == 2) {
            return new JunglerGardener(rc);
        }
        return new PlantingGardener(rc);
    }
}
