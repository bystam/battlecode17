package strategies.jungler;

import battlecode.common.*;
import common.robots.Gardener;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by fredrikbystam on 10/01/17.
 */
public class JunglerGardener extends Gardener {

    public JunglerGardener(RobotController r) {
        super(r);
    }

    @Override
    public void step() throws GameActionException {
        System.out.println("paralyzed");
        TreeInfo[] trees = senseNearbyTrees();

        Optional<TreeInfo> neutralBulletTree = getNeutralBulletTree(trees);
        Optional<TreeInfo> neutralRobotTree = getNeutralRobotTree(trees);

        if (neutralBulletTree.isPresent()) {
            TreeInfo tree = neutralBulletTree.get();
            if (canShake() && tree.location.isWithinDistance(getLocation(), getType().strideRadius) && tree.team == null) {
                shake(tree.getID());
            } else if (canMove(tree.location)) {
                move(tree.location);
            }
        }

        if (neutralRobotTree.isPresent()) {
            TreeInfo tree = neutralRobotTree.get();


            for (Direction dir : tools.getDirections()) {
                if (canBuildRobot(RobotType.LUMBERJACK, dir)) {
                    buildRobot(RobotType.LUMBERJACK, dir);
                }
            }
        }

        if(!hasMoved()){
            moveInAnyDirection();
        }
    }

    Optional<TreeInfo> getNeutralBulletTree(TreeInfo[] trees) {
        return Arrays.stream(trees).filter( (t) -> t.containedBullets > 0 ).findFirst();
    }

    Optional<TreeInfo> getNeutralRobotTree(TreeInfo[] trees) {
        return Arrays.stream(trees).filter( (t) -> t.containedRobot != null ).findFirst();
    }

    @Override
    public int[] getColor() {
        return new int[] { 255, 0, 0 };
    }
}
