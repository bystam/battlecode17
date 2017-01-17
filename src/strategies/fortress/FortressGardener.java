package strategies.fortress;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import common.robots.Gardener;

/**
 * Created by jens on 2017-01-15.
 */
public class FortressGardener extends Gardener{

    private float y;
    private float GARDEN_MARGIN = 10f;
    private boolean startedPlanting = false;

    public FortressGardener(RobotController r, int yIndex) {
        super(r);
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

        if(hasTreeBuildRequirements() && canMove(Direction.getEast())){
            move(Direction.getEast());
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
