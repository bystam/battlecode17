package strategies.mapping;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import common.model.MapGrid;
import common.wrappers.MemoryBase;

/**
 * Created by fredrikbystam on 15/01/17.
 */
public class MappingMemory extends MemoryBase {

    // booleans
    private static final int BOOL_FIELD = 16;
    private static final int HAS_SCOUT_BIT = 0;
    private static final int HAS_MAPPING_GARDENER_BIT = 1;

    private static final int GRID_ORIGIN_X = 4;
    private static final int GRID_ORIGIN_Y = 5;
    private static final int GRID_WIDTH = 6;
    private static final int GRID_HEIGHT = 7;

    public MappingMemory(RobotController rc) {
        super(rc, Offsets.MAPPING);
    }

    public boolean hasScout() throws GameActionException {
        return isBitSet(BOOL_FIELD, HAS_SCOUT_BIT);
    }

    public void setHasScout(boolean hasScout) throws GameActionException {
        setBit(BOOL_FIELD, HAS_SCOUT_BIT, hasScout);
    }

    public boolean hasMappingGardener() throws GameActionException {
        return isBitSet(BOOL_FIELD, HAS_MAPPING_GARDENER_BIT);
    }

    public void setHasMappingGardener(boolean hasMappingGardener) throws GameActionException {
        setBit(BOOL_FIELD, HAS_MAPPING_GARDENER_BIT, hasMappingGardener);
    }

    public void setGrid(MapGrid grid) throws GameActionException {
        rc.broadcast(GRID_ORIGIN_X, grid.origin.x);
        rc.broadcast(GRID_ORIGIN_Y, grid.origin.y);
        rc.broadcast(GRID_WIDTH, grid.width);
        rc.broadcast(GRID_HEIGHT, grid.height);
    }

    public MapGrid getGrid() throws GameActionException {
        return new MapGrid(rc.readBroadcast(GRID_ORIGIN_X), rc.readBroadcast(GRID_ORIGIN_Y),
                           rc.readBroadcast(GRID_WIDTH), rc.readBroadcast(GRID_HEIGHT));
    }
}
