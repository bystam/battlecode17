package common.robots;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

/**
 * Created by jens on 2017-01-10.
 */
public abstract class Lumberjack extends RobotBase {

    public Lumberjack(RobotController r) {
        super(r);
    }

    public boolean canChop(MapLocation mapLocation) {
        return rc.canChop(mapLocation);
    }


    public boolean canChop(int i) {
        return rc.canChop(i);
    }


    public void chop(MapLocation mapLocation) throws GameActionException {
        rc.chop(mapLocation);
    }


    public void chop(int i) throws GameActionException {
        rc.chop(i);
    }

    public boolean canStrike() {
        return rc.canStrike();
    }

    public void strike() throws GameActionException {
        rc.strike();
    }
}
