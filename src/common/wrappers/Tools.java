package common.wrappers;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jens on 2017-01-10.
 */
public class Tools {

    protected RobotController rc;

    public Tools(RobotController r){
        rc = r;
    }

    public void resign() {
        rc.resign();
    }

    public void setIndicatorDot(MapLocation mapLocation, int i, int i1, int i2) throws GameActionException {
        rc.setIndicatorDot(mapLocation, i, i1, i2);
    }

    public void setIndicatorLine(MapLocation mapLocation, MapLocation mapLocation1, int i, int i1, int i2) throws GameActionException {
        rc.setIndicatorLine(mapLocation, mapLocation1, i, i1, i2);
    }

    public long getControlBits() {
        return rc.getControlBits();
    }

    public void donate(float v) throws GameActionException {
        rc.donate(v);
    }

    public void broadcast(int i, int i1) throws GameActionException {
        rc.broadcast(i, i1);
    }

    public int readBroadcast(int i) throws GameActionException {
        return rc.readBroadcast(i);
    }

    public List<Direction> getDirections(){
        return Arrays.asList(Direction.getEast(), Direction.getNorth(), Direction.getWest(), Direction.getSouth());
    }

    public Direction getHorizontalPartOfDirection(Direction direction) {
        boolean facingEast = direction.radians < Math.PI/2 && direction.radians > -Math.PI/2;
        float dx = facingEast ? 1.0f : -1.0f;
        return new Direction(dx, 0.0f);
    }

    public Direction getVerticalPartOfDirection(Direction direction) {
        boolean facingNorth = direction.radians > 0.0;
        float dy = facingNorth ? 1.0f : -1.0f;
        return new Direction(0.0f, dy);
    }

    public Direction randomDirection() {
        return new Direction((float)Math.random() * 2 * (float)Math.PI);
    }

    public Direction getArchonCornerDirection(){
        MapLocation[] enemyLocations = rc.getInitialArchonLocations(rc.getTeam().opponent());
        MapLocation[] myLocations = rc.getInitialArchonLocations(rc.getTeam());

        MapLocation enemyMax = getMaxLocation(enemyLocations);
        MapLocation myMax = getMaxLocation(myLocations);

        Direction horizontal = enemyMax.x > myMax.x ? Direction.getWest() : Direction.getEast();
        Direction vertical = enemyMax.y > myMax.y ? Direction.getNorth() : Direction.getSouth();

        float halfDiff = horizontal.degreesBetween(vertical) / 2;
        float smallest = Math.abs(vertical.radians) > Math.abs(horizontal.radians) ? Math.abs(horizontal.radians) : Math.abs(vertical.radians);

        return new Direction(smallest + halfDiff);
    }


    public static MapLocation getMaxLocation(MapLocation[] locations){
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
}
