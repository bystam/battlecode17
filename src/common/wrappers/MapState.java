package common.wrappers;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.Team;

/**
 * Created by jens on 2017-01-10.
 */
public class MapState {

    private RobotController rc;

    public MapState(RobotController r){
        rc = r;
    }


    public int getRoundLimit() {
        return rc.getRoundLimit();
    }


    public int getRoundNum() {
        return rc.getRoundNum();
    }


    public float getTeamBullets() {
        return rc.getTeamBullets();
    }


    public int getTeamVictoryPoints() {
        return rc.getTeamVictoryPoints();
    }


    public int getRobotCount() {
        return rc.getRobotCount();
    }


    public int getTreeCount() {
        return rc.getTreeCount();
    }


    public MapLocation[] getInitialArchonLocations(Team team) {
        return rc.getInitialArchonLocations(team);
    }

    public Team getTeam() {
        return rc.getTeam();
    }



    public boolean isLocationOccupied(MapLocation mapLocation) throws GameActionException {
        return rc.isLocationOccupied(mapLocation);
    }


    public boolean isLocationOccupiedByTree(MapLocation mapLocation) throws GameActionException {
        return rc.isLocationOccupiedByTree(mapLocation);
    }


    public boolean isLocationOccupiedByRobot(MapLocation mapLocation) throws GameActionException {
        return rc.isLocationOccupiedByRobot(mapLocation);
    }


    public boolean isCircleOccupied(MapLocation mapLocation, float v) throws GameActionException {
        return rc.isCircleOccupied(mapLocation, v);
    }
}
