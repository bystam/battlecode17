package strategies.coward;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.robots.Archon;

/**
 * Created by fredrikbystam on 10/01/17.
 */
public class LimitedArchon extends Archon {

    private final int gardenerCount;
    private int gardenersBuilt;

    public LimitedArchon(RobotController r, int gardenerCount) {
        super(r);
        this.gardenerCount = gardenerCount;
    }

    @Override
    public void step() throws GameActionException {
        if (gardenersBuilt == gardenerCount) {
            return;
        }

        if (hireInAnyDirection()) {
            gardenersBuilt++;
        }
    }
}
