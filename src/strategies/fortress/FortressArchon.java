package strategies.fortress;

import battlecode.common.*;
import common.robots.Archon;

/**
 * Created by jens on 2017-01-15.
 */
public class FortressArchon extends Archon{

    Direction corner;
    FortressSharedMemory memory;

    public FortressArchon(RobotController r, Direction corner) {
        super(r);
        memory = new FortressSharedMemory(r);
        this.corner = corner;

    }


    @Override
    public void step() throws GameActionException {

        if(!moveTowardsCorner(corner)){
            return;
        }

        //we're at the corner now, start by building 3 gardeners
        if(memory.getGardenerCount() < 1){
            hireInAnyDirection();
        }
    }


}
