package strategies.mapping;

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

    private final MappingMemory memory;

    private MapLocation bottomLeftCorner;
    private MapGrid grid;


    public MappingScout(RobotController r) {
        super(r);
        memory = new MappingMemory(r);
    }

    @Override
    public void step() throws GameActionException {

        memory.setHasScout(true);

        if (bottomLeftCorner == null) {
            Direction southWest = new Direction(-1, -1);
            boolean foundCorner = moveTowardsCorner(southWest);
            if (foundCorner) {
                bottomLeftCorner = getLocation();
            }
        } else if (grid == null) {
            Direction northEast = new Direction(1, 1);
            boolean foundCorner = moveTowardsCorner(northEast);
            if (foundCorner) {
                MapLocation bottomRightCorner = getLocation();
                grid = new MapGrid(bottomLeftCorner, bottomRightCorner);
            }
        } else {

        }
    }
}
