package strategies.jungler;

import battlecode.common.*;
import common.robots.Lumberjack;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by fredrikbystam on 10/01/17.
 */
public class JunglerLumberjack extends Lumberjack {

    public JunglerLumberjack(RobotController r) {
        super(r);
    }

    @Override
    public void step() throws GameActionException {
        TreeInfo[] trees = senseNearbyTrees();

        Optional<TreeInfo> neutralRobotTree = getNeutralRobotTree(trees);

        if (neutralRobotTree.isPresent()) {
            TreeInfo tree = neutralRobotTree.get();
            //boolean second =  tree.location.isWithinDistance(getLocation(), getType().strideRadius);
            boolean first = canChop(tree.getID());
            //System.out.println("first: " + first);
            if (first) {
                System.out.println("chop");
                chop(tree.getID());
            } else if (canMove(tree.location)) {
                move(tree.location);
            }
        }
    }


    Optional<TreeInfo> getNeutralRobotTree(TreeInfo[] trees) {
        return Arrays.stream(trees).filter( (t) -> t.containedRobot != null ).findFirst();
    }
}
