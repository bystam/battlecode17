package strategies.mapping;

import battlecode.common.*;
import common.model.MapGrid;
import common.robots.Scout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MappingScout extends Scout {

    private final MappingMemory memory;

    private MapLocation bottomLeftCorner;
    private MapGrid grid;

    private List<MapLocation> locationsToCover;

    public MappingScout(RobotController r) {
        super(r);
        memory = new MappingMemory(r);
    }

    @Override
    public void step() throws GameActionException {

        memory.setHasScout(true);

        if (bottomLeftCorner == null) {
            findBottomLeftCorner();
        } else if (grid == null) {
            findTopRightCorner();
        } else if (!locationsToCover.isEmpty()) {
            flyAcrossMapRegisteringTrees();
        } else {
            // run and hide?
        }
    }

    private void findTopRightCorner() throws GameActionException {
        Direction northEast = new Direction(1, 1);
        boolean foundCorner = moveTowardsCorner(northEast);
        if (foundCorner) {
            MapLocation bottomRightCorner = getLocation();
            grid = new MapGrid(bottomLeftCorner, bottomRightCorner);
            locationsToCover = getLocationsEnoughToCoverGrid(grid);
            memory.setGrid(grid);
        }
    }

    private void findBottomLeftCorner() throws GameActionException {
        Direction southWest = new Direction(-1, -1);
        boolean foundCorner = moveTowardsCorner(southWest);
        if (foundCorner) {
            bottomLeftCorner = getLocation();
        }
    }

    private void flyAcrossMapRegisteringTrees() throws GameActionException {

        MapLocation nextLocation = locationsToCover.get(0);
        if (canMove(nextLocation)) {
            move(nextLocation);
        } else {
            moveInAnyDirection();
            // randomly step a bit away from nextLocation to try and avoid being stuck
        }

        final float tolerance = 0.05f;
        if (nextLocation.distanceTo(getLocation()) < tolerance) {
            locationsToCover.remove(0);
        }
    }

    private List<MapLocation> getLocationsEnoughToCoverGrid(MapGrid grid) {

        System.out.println("getLocations");

        float radius = getType().sensorRadius;
        float minX = grid.origin.x;
        float maxX = minX + grid.width;
        float minY = grid.origin.y;
        float maxY = minY + grid.height;

        float horizontalDirection = -1.0f;

        // start top right corner
        float x = maxX - radius;
        float y = maxY - radius;

        List<MapLocation> locations = new ArrayList<>();
        while (y > minY) {
            locations.add(new MapLocation(x, y));

            float nextX = x + horizontalDirection * radius;
            if (nextX > minX && nextX < maxX) {
                x = nextX;
            } else {
                y = y - radius;
                horizontalDirection = -horizontalDirection;
            }
        }

        return locations;
    }
}
