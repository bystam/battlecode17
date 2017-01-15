package strategies.fortress;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import common.robots.Archon;

/**
 * Created by jens on 2017-01-15.
 */
public class FortressArchon extends Archon{

    Direction corner;

    public FortressArchon(RobotController r) {
        super(r);
        MapLocation[] enemyLocations = rc.getInitialArchonLocations(rc.getTeam().opponent());
        MapLocation[] myLocations = rc.getInitialArchonLocations(rc.getTeam());

        MapLocation enemyMax = getMaxLocation(enemyLocations);
        MapLocation myMax = getMaxLocation(myLocations);

        Direction horizontal = enemyMax.x > myMax.x ? Direction.getWest() : Direction.getEast();
        Direction vertical = enemyMax.y > myMax.y ? Direction.getNorth() : Direction.getSouth();

        float halfDiff = horizontal.degreesBetween(vertical) / 2;
        float smallest = Math.abs(vertical.radians) > Math.abs(horizontal.radians) ? Math.abs(horizontal.radians) : Math.abs(vertical.radians);

        corner = new Direction(smallest + halfDiff);
    }

    private MapLocation getMaxLocation(MapLocation[] locations){
        float maxX = Integer.MIN_VALUE;
        float maxY = Integer.MIN_VALUE;

        for(MapLocation location : locations){
            if(location.x > maxX){
                maxX = location.x;
            }
            if(location.y > maxY){
                maxY = location.y;
            }
        }

        return new MapLocation(maxX, maxY);
    }

    @Override
    public void step() throws GameActionException {
        moveTowardsCorner(corner);
    }
}
