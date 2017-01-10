package common.robots;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.RobotBase;

/**
 * Created by jens on 2017-01-10.
 */
public abstract class Soldier extends RobotBase {

    public Soldier(RobotController r) {
        super(r);
    }

    public boolean canFireSingleShot() {
        return rc.canFireSingleShot();
    }

    public boolean canFireTriadShot() {
        return rc.canFireTriadShot();
    }


    public boolean canFirePentadShot() {
        return rc.canFirePentadShot();
    }


    public void fireSingleShot(Direction direction) throws GameActionException {
        rc.fireSingleShot(direction);
    }


    public void fireTriadShot(Direction direction) throws GameActionException {
        rc.fireTriadShot(direction);
    }


    public void firePentadShot(Direction direction) throws GameActionException {
        rc.firePentadShot(direction);
    }
}
