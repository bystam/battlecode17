package common;

import battlecode.common.*;

/**
 * Created by jens on 2017-01-10.
 */
public abstract class RobotBase implements Robot {

    RobotController rc;

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

    
    public boolean canSenseTree(int i) {
        return rc.canSenseTree(i);
    }

    
    public boolean canSenseRobot(int i) {
        return rc.canSenseRobot(i);
    }

    
    public boolean canSenseBullet(int i) {
        return rc.canSenseBullet(i);
    }

    
    public TreeInfo senseTree(int i) throws GameActionException {
        return rc.senseTree(i);
    }

    
    public RobotInfo senseRobot(int i) throws GameActionException {
        return rc.senseRobot(i);
    }

    
    public BulletInfo senseBullet(int i) throws GameActionException {
        return rc.senseBullet(i);
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

    
    public boolean isBuildReady() {
        return rc.isBuildReady();
    }

    
    public boolean canMove(Direction direction) {
        return rc.canMove(direction);
    }

    
    public boolean canMove(Direction direction, float v) {
        return false;
    }

    
    public boolean canMove(MapLocation mapLocation) {
        return false;
    }

    
    public void move(Direction direction) throws GameActionException {

    }

    
    public void move(Direction direction, float v) throws GameActionException {

    }

    
    public void move(MapLocation mapLocation) throws GameActionException {

    }

    
    public boolean canStrike() {
        return false;
    }

    
    public void strike() throws GameActionException {

    }

    
    public boolean canFireSingleShot() {
        return false;
    }

    
    public boolean canFireTriadShot() {
        return false;
    }

    
    public boolean canFirePentadShot() {
        return false;
    }

    
    public void fireSingleShot(Direction direction) throws GameActionException {

    }

    
    public void fireTriadShot(Direction direction) throws GameActionException {

    }

    
    public void firePentadShot(Direction direction) throws GameActionException {

    }

    
    public boolean canChop(MapLocation mapLocation) {
        return false;
    }

    
    public boolean canChop(int i) {
        return false;
    }

    
    public void chop(MapLocation mapLocation) throws GameActionException {

    }

    
    public void chop(int i) throws GameActionException {

    }

    
    public boolean canShake(MapLocation mapLocation) {
        return false;
    }

    
    public boolean canShake(int i) {
        return false;
    }

    
    public void shake(MapLocation mapLocation) throws GameActionException {

    }

    
    public void shake(int i) throws GameActionException {

    }

    
    public boolean canWater(MapLocation mapLocation) {
        return false;
    }

    
    public boolean canWater(int i) {
        return false;
    }

    
    public void water(MapLocation mapLocation) throws GameActionException {

    }

    
    public void water(int i) throws GameActionException {

    }

    
    public boolean canWater() {
        return false;
    }

    
    public boolean canShake() {
        return false;
    }

    
    public boolean canInteractWithTree(MapLocation mapLocation) {
        return false;
    }

    
    public boolean canInteractWithTree(int i) {
        return false;
    }

    
    public void broadcast(int i, int i1) throws GameActionException {

    }

    
    public int readBroadcast(int i) throws GameActionException {
        return 0;
    }

    
    public boolean hasRobotBuildRequirements(RobotType robotType) {
        return false;
    }

    
    public boolean hasTreeBuildRequirements() {
        return false;
    }

    
    public boolean canBuildRobot(RobotType robotType, Direction direction) {
        return false;
    }

    
    public void buildRobot(RobotType robotType, Direction direction) throws GameActionException {

    }

    
    public boolean canPlantTree(Direction direction) {
        return false;
    }

    
    public void plantTree(Direction direction) throws GameActionException {

    }

    
    public boolean canHireGardener(Direction direction) {
        return false;
    }

    
    public void hireGardener(Direction direction) throws GameActionException {

    }

    
    public void donate(float v) throws GameActionException {

    }

    
    public boolean canInteractWithRobot(MapLocation mapLocation) {
        return false;
    }

    
    public boolean canInteractWithRobot(int i) {
        return false;
    }

    
    public void disintegrate() {

    }

    
    public void resign() {

    }

    
    public void setIndicatorDot(MapLocation mapLocation, int i, int i1, int i2) throws GameActionException {

    }

    
    public void setIndicatorLine(MapLocation mapLocation, MapLocation mapLocation1, int i, int i1, int i2) throws GameActionException {

    }
    
    public long getControlBits() {
        return 0;
    }
}
