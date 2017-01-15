package strategies.fortress;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.robots.Gardener;

/**
 * Created by jens on 2017-01-15.
 */
public class FortressGardener extends Gardener{

    private int index;

    public FortressGardener(RobotController r, int yIndex) {
        super(r);
        this.index = yIndex;
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
}
