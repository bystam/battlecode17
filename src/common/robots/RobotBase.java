package common.robots;

import battlecode.common.*;
import common.wrappers.MapState;
import common.Robot;
import common.wrappers.Tools;

/**
 * Created by jens on 2017-01-10.
 */
public abstract class RobotBase implements Robot {

    protected RobotController rc;
    protected MapState map;
    protected Tools tools;

    public RobotBase(RobotController r){
        rc = r;
        map = new MapState(rc);
        tools = new Tools(rc);
    }

    public int getID() {
        return rc.getID();
    }

    public RobotType getType() {
        return rc.getType();
    }

    public Team getTeam() {
        return rc.getTeam();
    }


    public MapLocation getLocation() {
        return rc.getLocation();
    }


    public float getHealth() {
        return rc.getHealth();
    }


    public int getAttackCount() {
        return rc.getAttackCount();
    }


    public int getMoveCount() {
        return rc.getMoveCount();
    }


    public boolean onTheMap(MapLocation mapLocation) throws GameActionException {
        return rc.onTheMap(mapLocation);
    }


    public boolean onTheMap(MapLocation mapLocation, float v) throws GameActionException {
        return rc.onTheMap(mapLocation, v);
    }


    public boolean canSenseLocation(MapLocation mapLocation) {
        return rc.canSenseLocation(mapLocation);
    }


    public boolean canSensePartOfCircle(MapLocation mapLocation, float v) {
        return rc.canSensePartOfCircle(mapLocation, v);
    }


    public boolean canSenseAllOfCircle(MapLocation mapLocation, float v) {
        return rc.canSenseAllOfCircle(mapLocation,v);
    }

    public boolean isCircleOccupiedExceptByThisRobot(MapLocation mapLocation, float v) throws GameActionException {
        return rc.isCircleOccupiedExceptByThisRobot(mapLocation, v);
    }

    public TreeInfo senseTreeAtLocation(MapLocation mapLocation) throws GameActionException {
        return rc.senseTreeAtLocation(mapLocation);
    }


    public RobotInfo senseRobotAtLocation(MapLocation mapLocation) throws GameActionException {
        return rc.senseRobotAtLocation(mapLocation);
    }


    public boolean canSenseTree(int id) {
        return rc.canSenseTree(id);
    }


    public boolean canSenseRobot(int id) {
        return rc.canSenseRobot(id);
    }


    public boolean canSenseBullet(int id) {
        return rc.canSenseBullet(id);
    }


    public TreeInfo senseTree(int id) throws GameActionException {
        return rc.senseTree(id);
    }


    public RobotInfo senseRobot(int id) throws GameActionException {
        return rc.senseRobot(id);
    }


    public BulletInfo senseBullet(int id) throws GameActionException {
        return rc.senseBullet(id);
    }


    public RobotInfo[] senseNearbyRobots() {
        return rc.senseNearbyRobots();
    }


    public RobotInfo[] senseNearbyRobots(float v) {
        return rc.senseNearbyRobots(v);
    }


    public RobotInfo[] senseNearbyRobots(float v, Team team) {
        return rc.senseNearbyRobots(v, team);
    }


    public RobotInfo[] senseNearbyRobots(MapLocation mapLocation, float v, Team team) {
        return rc.senseNearbyRobots(mapLocation, v, team);
    }


    public TreeInfo[] senseNearbyTrees() {
        return rc.senseNearbyTrees();
    }


    public TreeInfo[] senseNearbyTrees(float v) {
        return rc.senseNearbyTrees(v);
    }


    public TreeInfo[] senseNearbyTrees(float v, Team team) {
        return rc.senseNearbyTrees(v,team);
    }


    public TreeInfo[] senseNearbyTrees(MapLocation mapLocation, float v, Team team) {
        return rc.senseNearbyTrees(mapLocation, v, team);
    }


    public BulletInfo[] senseNearbyBullets() {
        return rc.senseNearbyBullets();
    }


    public BulletInfo[] senseNearbyBullets(float v) {
        return rc.senseNearbyBullets(v);
    }


    public BulletInfo[] senseNearbyBullets(MapLocation mapLocation, float v) {
        return rc.senseNearbyBullets(mapLocation, v);
    }


    public MapLocation[] senseBroadcastingRobotLocations() {
        return rc.senseBroadcastingRobotLocations();
    }


    public boolean hasMoved() {
        return rc.hasMoved();
    }


    public boolean hasAttacked() {
        return rc.hasAttacked();
    }

    public boolean canMove(Direction direction) {
        return rc.canMove(direction);
    }


    public boolean canMove(Direction direction, float distance) {
        return rc.canMove(direction, distance);
    }


    public boolean canMove(MapLocation mapLocation) {
        return rc.canMove(mapLocation);
    }


    public void move(Direction direction) throws GameActionException {
        rc.move(direction);
    }


    public void move(Direction direction, float v) throws GameActionException {
        rc.move(direction, v);
    }


    public void move(MapLocation mapLocation) throws GameActionException {
        rc.move(mapLocation);
    }

    public boolean canShake(MapLocation mapLocation) {
        return rc.canShake(mapLocation);
    }


    public boolean canShake(int id) {
        return rc.canShake(id);
    }


    public void shake(MapLocation mapLocation) throws GameActionException {
        rc.shake(mapLocation);
    }


    public void shake(int id) throws GameActionException {
        rc.shake(id);
    }

    public boolean canShake() {
        return rc.canShake();
    }


    public boolean canInteractWithTree(MapLocation mapLocation) {
        return rc.canInteractWithTree(mapLocation);
    }


    public boolean canInteractWithTree(int id) {
        return rc.canInteractWithTree(id);
    }

    public void disintegrate() {
        rc.disintegrate();
    }

    public int[] getColor() {
        return null;
    }

    protected void moveInAnyDirection() throws GameActionException{
        while(true){
            Direction dir = tools.randomDirection();
            if(canMove(dir)){
                move(dir);
                return;
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {
                int[] color = getColor();
                if (color != null) {
                    tools.setIndicatorDot(getLocation(), color[0], color[1], color[2]);
                }
                step();

                Clock.yield();
            } catch (Exception e) {
                System.out.println(getType() + " Exception");
                e.printStackTrace();
            }
        }
    }

    public abstract void step() throws GameActionException;


    // **** Convenience methods ****

    /**
     * @param direction should be NW, NE, SE, SW
     * @return <code>true</code> if the corner was found, <code>false</code> otherwise
     */
    public boolean moveTowardsCorner(Direction direction) throws GameActionException {
        if (canMove(direction)) {
            move(direction);
            return false;
        }

        Direction horizontally = tools.getHorizontalPartOfDirection(direction);
        if (canMove(horizontally)) {
            move(horizontally);
            return false;
        }

        boolean didMoveToEdge = binarySearchMoveTowards(horizontally);
        if (didMoveToEdge) {
            return false;
        }

        Direction vertically = tools.getVerticalPartOfDirection(direction);
        if (canMove(vertically)) {
            move(vertically);
            return false;
        }

        didMoveToEdge = binarySearchMoveTowards(vertically);
        if (didMoveToEdge) {
            return false;
        }

        return true;
    }

    // return true if a move was successful, false otherwise
    private boolean binarySearchMoveTowards(Direction direction) throws GameActionException {
        final float tolerance = 0.05f;
        float high = getType().strideRadius;
        float low = tolerance;
        while (high - low > tolerance) {
            float middle = (high + low) / 2;
            if (canMove(direction, middle)) {
                low = middle;
            } else {
                high = middle;
            }
        }

        if (canMove(direction, high)) {
            move(direction, high);
            return true;
        } else if (canMove(direction, low)) {
            move(direction, low);
            return true;
        }

        return false;
    }

}
