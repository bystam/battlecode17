package strategies.maxproduction;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.robots.Gardener;

/**
 * Created by jens on 2017-01-10.
 */
public class WateringGardener extends Gardener{

    public WateringGardener(RobotController r) {
        super(r);
    }

    @Override
    public void step() throws GameActionException {
        while(true){
            waterTree();
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

    private boolean waterTree() throws GameActionException{
        boolean hasWatered = false;

        if(this.canWater(this.getLocation())){
            hasWatered = true;
            this.water(this.getLocation());
        }
        return hasWatered;
    }
}
