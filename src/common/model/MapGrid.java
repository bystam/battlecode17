package common.model;

import battlecode.common.MapLocation;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MapGrid {

    public final Point origin;
    public final int width, height;

    public MapGrid(MapLocation bottomLeft, MapLocation topRight) {
        this.origin = new Point(bottomLeft);
        this.width = (int)(topRight.x - bottomLeft.x);
        this.height = (int)(topRight.y - bottomLeft.y);
    }

    public MapGrid(int originX, int originY, int width, int height) {
        this.origin = new Point(originX, originY);
        this.width = width;
        this.height = height;
    }

    public MapLocation getCenter() {
        return new MapLocation(origin.x + width/2, origin.y + height/2);
    }
}
