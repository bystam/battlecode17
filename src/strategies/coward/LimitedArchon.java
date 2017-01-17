package strategies.coward;

import battlecode.common.*;
import common.Robot;
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

        if (buildGardenerTowardEnemy()) {
            gardenersBuilt++;
        }
    }

    private boolean buildGardenerTowardEnemy() throws GameActionException {
        MapLocation enemy = map.getInitialArchonLocations(getTeam().opponent())[0];
        Direction direction = getLocation().directionTo(enemy);
        if (canBuildRobot(RobotType.GARDENER, direction)) {
            buildRobot(RobotType.GARDENER, direction);
            return true;
        }

        return wiggleBuildInDirection(direction, 10);
    }

    private boolean wiggleBuildInDirection(Direction direction, int tries) throws GameActionException {
        for (int i = 0; i < tries; i++) {
            float randomDegrees = (float)(Math.random() - 0.5) * 90;
            Direction wiggledDirection = direction.rotateLeftDegrees(randomDegrees);

            if (canBuildRobot(RobotType.GARDENER, wiggledDirection)) {
                buildRobot(RobotType.GARDENER, wiggledDirection);
                return true;
            }
        }
        return false;
    }
}
