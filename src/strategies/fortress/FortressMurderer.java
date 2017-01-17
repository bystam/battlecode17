package strategies.fortress;

import battlecode.common.*;
import common.robots.Lumberjack;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by jens on 2017-01-17.
 */
public class FortressMurderer extends Lumberjack {

    private final FortressSharedMemory memory;
    private static final float CLOSE = 15.0f;

    public FortressMurderer(RobotController r) {
        super(r);
        memory = new FortressSharedMemory(r);
    }

    @Override
    public void step() throws GameActionException {
        TreeInfo[] nearbyTrees = Arrays.asList(senseNearbyTrees(-1, Team.NEUTRAL)).stream().filter(tree-> tree.getLocation().distanceSquaredTo(this.getLocation()) < CLOSE).toArray(TreeInfo[]::new);

        if(nearbyTrees != null && nearbyTrees.length > 0){
            System.out.println("nearbytrees");
            if(murderTrees(nearbyTrees)) {
                return;
            }
            MapLocation l = nearbyTrees[0].getLocation();
            pathFindingAlgorithmMoveTowards(l);
            return;
        }

        MapLocation job = memory.popMurdererJob();
        if(job == null){
            return;
        }
        System.out.println("hasajob: " + job);
        pathFindingAlgorithmMoveTowards(job);
    }

    private boolean murderTrees(TreeInfo[] nearbyTrees) throws GameActionException {
        boolean murdered = false;
        for(TreeInfo tree : nearbyTrees){
            int id = tree.getID();
            if(canChop(id)){
                chop(id);
                murdered = true;
                break;
            }

        }
        return murdered;
    }
}
