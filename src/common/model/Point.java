package common.model;

import battlecode.common.MapLocation;

/**
 * Created by fredrikbystam on 17/01/17.
 */
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(MapLocation location) {
        this((int)location.x, (int)location.y);
    }
}
