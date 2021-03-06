package bystam;

import battlecode.common.*;
import common.robots.Gardener;
import strategies.coward.LimitedArchon;
import strategies.fortress.FortressMurderer;
import strategies.jungler.JunglerGardener;
import strategies.mapping.MappingGardener;
import strategies.mapping.MappingMemory;
import strategies.mapping.MappingScout;
import strategies.maxproduction.PlantingGardener;
import strategies.maxproduction.WateringGardener;
import strategies.sixpool.SixPoolGardener;
import strategies.sixpool.SixPoolSoldier;
import strategies.sixpool.SixPoolTank;

public strictfp class RobotPlayer {

    private static MappingMemory mappingMemory;

    public static void run(RobotController rc) throws GameActionException {
        mappingMemory = new MappingMemory(rc);

        switch (rc.getType()) {
            case ARCHON:
                new LimitedArchon(rc, 2).run();
                break;
            case GARDENER:
                new SixPoolGardener(rc).run();
                break;
            case LUMBERJACK:
                new FortressMurderer(rc).run();
                break;
            case SCOUT:
                new MappingScout(rc).run();
                break;
            case SOLDIER:
                new SixPoolSoldier(rc).run();
                break;
            case TANK:
                new SixPoolTank(rc).run();
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
