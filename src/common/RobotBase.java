package common;

import battlecode.common.*;

/**
 * Created by jens on 2017-01-10.
 */
public abstract class RobotBase implements Robot {

    protected RobotController rc;
    protected MapState map;
    protected SharedMemory memory;
    protected Tools tools;

    public RobotBase(RobotController r){
        rc = r;
    }

    public int getID() {
        return rc.getID();
    }
    
    public RobotType getType() {
        return rc.getType();
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

    
    public boolean canMove(Direction direction, float v) {
        return rc.canMove(direction, v);
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


    public boolean canInteractWithRobot(MapLocation mapLocation) {
        return rc.canInteractWithRobot(mapLocation);
    }

    
    public boolean canInteractWithRobot(int id) {
        return rc.canInteractWithRobot(id);
    }

    
    public void disintegrate() {
        rc.disintegrate();
    }

    @Override
    public void run() {
        while (true) {
            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {
                step();
                Clock.yield();
            } catch (Exception e) {
                System.out.println("Archon Exception");
                e.printStackTrace();
            }
        }
    }

    public abstract void step() throws GameActionException;
}
