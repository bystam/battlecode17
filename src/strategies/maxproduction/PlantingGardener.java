package strategies.maxproduction;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.robots.Gardener;

/**
 * Created by jens on 2017-01-10.
 */
public class PlantingGardener extends Gardener{

    public PlantingGardener(RobotController r) {
        super(r);
    }

    @Override
    public void step() throws GameActionException {
        if(!plantTreeInAnyDirection()){
            moveInAnyDirection();
            plantTreeInAnyDirection();
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

    @Override
    public int[] getColor() {
        return new int[] { 0, 255, 0 };
    }
}
