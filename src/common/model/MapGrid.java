package common.model;

import battlecode.common.MapLocation;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MapGrid {

    public final Point origin;
    public final int width, height;

    public MapGrid(MapLocation topLeft, MapLocation bottomRight) {
        this.origin = new Point(Math.round(topLeft.x), Math.round(topLeft.y));
        this.width = Math.round(bottomRight.x - topLeft.x);
        this.height = Math.round(bottomRight.y - topLeft.y);
    }

    public static class Point {
        public final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
