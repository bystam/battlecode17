package common.model;

import battlecode.common.MapLocation;
import battlecode.common.TreeInfo;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MapGrid {

    public final Point origin;
    public final int width, height;

    private final TreeInfo[][] treeGrid;

    public MapGrid(MapLocation bottomLeft, MapLocation topRight) {
        this.origin = new Point(bottomLeft);
        this.width = (int)(topRight.x - bottomLeft.x);
        this.height = (int)(topRight.y - bottomLeft.y);
        treeGrid = new TreeInfo[width][height];
    }

    public void registerTrees(TreeInfo[] trees) {
        /*for (TreeInfo tree : trees) {
            Point p = new Point(tree.getLocation());
            treeGrid[p.x - origin.x][p.y - origin.y] = tree;
        }*/
    }

    public static class Point {
        public final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(MapLocation location) {
            this((int)location.x, (int)location.y);
        }
    }
}
