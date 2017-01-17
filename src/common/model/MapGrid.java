package common.model;

import battlecode.common.MapLocation;
import battlecode.common.TreeInfo;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MapGrid {

    public final Point origin;
    public final int width, height;

    //private final TreeInfo[][] treeGrid;

    public MapGrid(MapLocation bottomLeft, MapLocation topRight) {
        this.origin = new Point(bottomLeft);
        this.width = (int)(topRight.x - bottomLeft.x);
        this.height = (int)(topRight.y - bottomLeft.y);
        //treeGrid = new TreeInfo[width][height];
    }

    public MapGrid(int originX, int originY, int width, int height) {
        this.origin = new Point(originX, originY);
        this.width = width;
        this.height = height;
    }

    public void registerTrees(TreeInfo[] trees) {
        /*for (TreeInfo tree : trees) {
            Point p = new Point(tree.getLocation());
            treeGrid[p.x - origin.x][p.y - origin.y] = tree;
        }*/
    }

}
