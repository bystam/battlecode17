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
    private boolean hasBuiltMurderer = false;

    public FortressGardener(RobotController r, int yIndex) {
        super(r);
        memory = new FortressSharedMemory(r);
        this.y = this.getLocation().y - ((3-yIndex) * GARDEN_MARGIN);
        System.out.println("Y coord for this gardener is " + this.y);
    }

    @Override
    public void step() throws GameActionException {
        if(!startedPlanting && Math.abs(getLocation().y - y) > 0.05f){
            MapLocation loco = new MapLocation(getLocation().x, y);
            if(canMove(loco)){
                move(loco);
                return;
            }
        }
        if(!startedPlanting && canMove(Direction.getWest())){
            move(Direction.getWest());
            return;
        }
        startedPlanting = true;

        //now at westmost point. start planting trees until some x
        if(canPlantTree(Direction.getSouth())){
            plantTree(Direction.getSouth());
            return;
        }

        boolean canMoveAlongLine = canMove(Direction.getEast());
        TreeInfo[] nearbyTrees = senseNearbyTrees();
        boolean treesBlockingLine = nearbyTrees != null && nearbyTrees.length > 0;

        if(isBuildReady() && hasTreeBuildRequirements() && canMoveAlongLine){
            move(Direction.getEast());
        }
        //System.out.println("canmovealong: " + canMoveAlongLine + ", treesblocking: " + treesBlockingLine);
        if(!canMoveAlongLine && treesBlockingLine){
            if(memory.getLumberjackCount() < 1) {
                deployMurderer();
            } else{
                //System.out.println("calling murderer");
                memory.callMurderer(getLocation());
            }
        }
    }

    private void deployMurderer() throws GameActionException {
        if(buildInAnyDirection(RobotType.LUMBERJACK)){
            hasBuiltMurderer = true;
            memory.getAndSetLumberjackIndex();
        }
    }

    private boolean plantTreeInAnyDirection() throws GameActionException{
        boolean hasPlanted = false;
        for(Direction d : tools.getDirections()){

            if(this.canPlantTree(d)){
                hasPlanted = true;
                this.plantTree(d);
                break;
            }
        }
        return hasPlanted;
    }
}
