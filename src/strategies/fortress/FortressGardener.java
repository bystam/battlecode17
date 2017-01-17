package strategies.fortress;

import battlecode.common.*;
import common.robots.Gardener;

/**
 * Created by jens on 2017-01-15.
 */
public class FortressGardener extends Gardener{

    private final FortressSharedMemory memory;
    private float y;
    private float GARDEN_MARGIN = 10f;
    private boolean startedPlanting = false;
    private int lineLength = 0;
    private boolean horizontal = false;

    public FortressGardener(RobotController r, int yIndex) {
        super(r);
        memory = new FortressSharedMemory(r);
        horizontal = map.shouldPlantHorizontal();
        this.y = this.getLocation().y - ((3-yIndex) * GARDEN_MARGIN);
    }

    @Override
    public void step() throws GameActionException {
        // go to your starting position
        if(goToStartingPosition()){
            return;
        }
        startedPlanting = true;


        //now at westmost point. start planting trees until some x
        if(canPlantTree(Direction.getSouth()) && lineLength < 8){
            plantTree(Direction.getSouth());
            lineLength++;
            return;
        }

        TreeInfo[] nearbyTrees = senseNearbyTrees(getLocation(), -1, getTeam());
        if(nearbyTrees != null && nearbyTrees.length > 0){
            TreeInfo nearbyTree = nearbyTrees[0];
            float missingHealth = nearbyTree.getMaxHealth() - nearbyTree.getHealth();
            if (nearbyTree != null && missingHealth > 10 && canWater(nearbyTree.getID())){
                water(nearbyTree.getID());
                return;
            }
        }

        boolean canMoveAlongLine = canMove(Direction.getEast());
        boolean treesBlockingLine = nearbyTrees != null && nearbyTrees.length > 0;

        if(isBuildReady() && hasTreeBuildRequirements() && canMoveAlongLine && lineLength < 8){
            move(Direction.getEast());
        }
        if(lineLength > 8 && canMove(Direction.getWest())){
            move(Direction.getWest());
            return;
        }
        //System.out.println("canmovealong: " + canMoveAlongLine + ", treesblocking: " + treesBlockingLine);
        if(!canMoveAlongLine && treesBlockingLine){
            if(commonMemory.getRobotCount(RobotType.LUMBERJACK) < 1) {
                deployMurderer();
            } else{
                //System.out.println("calling murderer");
                memory.callMurderer(getLocation());
            }
        }
    }

    private boolean goToStartingPosition() throws GameActionException {
        if(startedPlanting){
            return false;
        }
        if(Math.abs(getLocation().y - y) > 0.05f){
            MapLocation loco = new MapLocation(getLocation().x, y);
            if(canMove(loco)){
                move(loco);
                return true;
            }
        }
        if(canMove(Direction.getWest())){
            move(Direction.getWest());
            return true;
        }
        if(memory.getLumberjackCount() < 1) {
            deployMurderer();
        }
        return false;
    }

    private void deployMurderer() throws GameActionException {
        if(buildInAnyDirection(RobotType.LUMBERJACK)){
            memory.getAndSetLumberjackIndex();
        }
    }
}
