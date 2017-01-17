package strategies.fortress;

import battlecode.common.*;
import common.robots.Lumberjack;
import common.robots.SuicideException;

import java.util.Arrays;

/**
 * Created by jens on 2017-01-17.
 */
public class FortressMurderer extends Lumberjack {

    private final FortressSharedMemory memory;
    private static final float CLOSE = 20.0f;
    MapLocation job;

    public FortressMurderer(RobotController r) {
        super(r);
        memory = new FortressSharedMemory(r);
    }

    @Override
    public void step() throws GameActionException {
        if(map.getRoundNum() % 50 == 0){
            throw new SuicideException();
        }
        if(job != null && job.distanceTo(getLocation()) > CLOSE){
            System.out.println("moving towards job " + job);
            pathFindingAlgorithmMoveTowards(job);
            return;
        }
        TreeInfo[] nearbyTrees = Arrays.asList(senseNearbyTrees(-1, Team.NEUTRAL)).stream().filter(tree-> tree.getLocation().distanceTo(this.getLocation()) < CLOSE).toArray(TreeInfo[]::new);

        if(nearbyTrees != null && nearbyTrees.length > 0){
            if(murderTrees(nearbyTrees)) {
                return;
            }
            MapLocation l = nearbyTrees[0].getLocation();
            pathFindingAlgorithmMoveTowards(l);
            return;
        }

        job = memory.popMurdererJob();
        if(job == null){
            return;
        }
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
