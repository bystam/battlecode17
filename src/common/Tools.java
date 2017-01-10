package common;

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
}
