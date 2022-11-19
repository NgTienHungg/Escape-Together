/* Author: NgTienHungg */
package game.entity;

import java.awt.Graphics2D;
import static game.entity.Level.ROW;
import static game.entity.Level.COL;

public class TileMap extends GameObject {

    public static int[][] DataMap;

    private Tile[][] map;

    public TileMap(int[][] data) {
        TileMap.DataMap = data;
        map = new Tile[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (DataMap[i][j] == -1) {
                    map[i][j] = null;
                } else {
                    map[i][j] = new Tile(i, j, DataMap[i][j]);
                }
            }
        }
    }

    public static boolean isGround(int r, int c) {
        return DataMap[r][c] == 0 || DataMap[r][c] == 3 || DataMap[r][c] == 6;
    }

    private void unlockKeyDoor() {
        Level.OpenKeyDoor = false;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (DataMap[i][j] == 4) {
                    DataMap[i][j] = 0;
                    map[i][j] = new Tile(i, j, DataMap[i][j]);
                    return;
                }
            }
        }
    }

    private void unlockSwitchDoor() {
        Level.OpenSwitchDoor = false;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (DataMap[i][j] == 5 || DataMap[i][j] == 6) {
                    DataMap[i][j] = 0;
                    map[i][j] = new Tile(i, j, DataMap[i][j]);
                }
            }
        }
    }

    @Override
    public void update() {
        if (Level.OpenKeyDoor) {
            unlockKeyDoor();
        }
        if (Level.OpenSwitchDoor) {
            unlockSwitchDoor();
        }

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (map[i][j] != null) {
                    map[i][j].update();
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (map[i][j] != null) {
                    map[i][j].render(g);
                }
            }
        }
    }
}
