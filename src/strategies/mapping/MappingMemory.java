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
        setInt(GRID_ORIGIN_X, grid.origin.x);
        setInt(GRID_ORIGIN_Y, grid.origin.y);
        setInt(GRID_WIDTH, grid.width);
        setInt(GRID_HEIGHT, grid.height);
    }

    public MapGrid getGrid() throws GameActionException {
        int x = getInt(GRID_ORIGIN_X);
        int y = getInt(GRID_ORIGIN_Y);
        int width = getInt(GRID_WIDTH);
        int height = getInt(GRID_HEIGHT);

        if (width < 3 || height < 3) { // there probably is no grid
            return null;
        }

        return new MapGrid(x, y, width, height);
    }
}
