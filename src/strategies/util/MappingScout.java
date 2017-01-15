package strategies.util;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import common.model.MapGrid;
import common.robots.Scout;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MappingScout extends Scout {

    private MapLocation topLeftCorner;
    private MapGrid grid;


    public MappingScout(RobotController r) {
        super(r);
    }

    @Override
    public void step() throws GameActionException {

        if (topLeftCorner == null) {
            Direction northWest = new Direction(-1, -1);
            boolean foundCorner = moveTowardsCorner(northWest);
            if (foundCorner) {
                topLeftCorner = getLocation();
            }
        } else if (grid == null) {
            Direction southEast = new Direction(1, 1);
            boolean foundCorner = moveTowardsCorner(southEast);
            if (foundCorner) {
                MapLocation bottomRightCorner = getLocation();
                grid = new MapGrid(topLeftCorner, bottomRightCorner);
            }
        } else {

        }
    }
}
