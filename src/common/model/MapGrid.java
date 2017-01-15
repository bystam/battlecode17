package common.model;

import battlecode.common.MapLocation;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MapGrid {

    public final Point origin;
    public final int width, height;

    public MapGrid(MapLocation bottomLeft, MapLocation topRight) {
        this.origin = new Point(Math.round(bottomLeft.x), Math.round(bottomLeft.y));
        this.width = Math.round(topRight.x - bottomLeft.x);
        this.height = Math.round(topRight.y - bottomLeft.y);
    }

    public static class Point {
        public final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
