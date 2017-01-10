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
        while(!plantTreeInAnyDirection()){
            moveInAnyDirection();
        }

    }

    private void moveInAnyDirection() throws GameActionException{
        for(Direction d : tools.getDirections()){
            if(canMove(d)){
                move(d);
            }
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
