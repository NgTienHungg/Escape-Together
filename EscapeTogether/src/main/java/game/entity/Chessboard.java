/* Author: NgTienHungg */
package game.entity;

import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics2D;
import game.resource.AudioManager;
import static game.entity.Level.ROW;
import static game.entity.Level.COL;
import static game.entity.TileMap.DataMap;
import static game.entity.TileMap.isGround;

public class Chessboard extends GameObject {

    public static int[] dirRow = {-1, 0, 1, 0};
    public static int[] dirCol = {0, 1, 0, -1};

    private Chessman[][] board;
    private Chessman hero;
    private List<Chessman> chessNeedMove;

    public Chessboard(int[][] data) {
        initBoard(data);
        chessNeedMove = new ArrayList<>();
    }

    private void initBoard(int[][] data) {
        // 10 : red
        // 11 : blue
        // 12 : green
        // 13 : box
        // 14 : key

        board = new Chessman[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                switch (data[i][j]) {
                    case 10 ->
                        board[i][j] = new Character(i, j, Tag.RedCharacter);
                    case 11 ->
                        board[i][j] = new Character(i, j, Tag.BlueCharacter);
                    case 12 ->
                        board[i][j] = new Character(i, j, Tag.GreenCharacter);
                    case 13 ->
                        board[i][j] = new Box(i, j, Tag.Box);
                    case 14 ->
                        board[i][j] = new Key(i, j, Tag.Key);
                    default ->
                        board[i][j] = new Chessman(i, j, Tag.Default);
                }
            }
        }
    }

    private Chessman findHero(Tag tag) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].tag == tag) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    private void swapChess(Chessman a, Chessman b) {
        board[a.row][a.col] = b;
        board[b.row][b.col] = a;
        b.row = a.row;
        b.col = a.col;
    }

    //////////////////////////////////////////////////
    public void handleMoving(int dr, int dc) {
        // tìm nhân vật
        if (dc != 0) {
            hero = findHero(Tag.RedCharacter);
        } else if (dr != 0) {
            hero = findHero(Tag.BlueCharacter);
        }
        if (hero == null) {
            return;
        }

        // tìm tất cả những Chess đang connect với Hero
        chessNeedMove.clear();
        if (hero.connecting) {
            findConnects(hero.row, hero.col);
        } else {
            findObstacles(hero.row, hero.col, dr, dc);
        }
        if (canMove(dr, dc)) {
            moveChess(dr, dc);
        }
    }

    private void findConnects(int curRow, int curCol) {
        chessNeedMove.add(board[curRow][curCol]);
        if (board[curRow][curCol].connecting) {
            for (int i = 0; i < 4; i++) {
                int r = curRow + dirRow[i];
                int c = curCol + dirCol[i];
                if (board[r][c].tag != Tag.Default && !chessNeedMove.contains(board[r][c])) {
                    findConnects(r, c);
                }
            }
        }
    }

    private void findObstacles(int curRow, int curCol, int dr, int dc) {
        chessNeedMove.add(board[curRow][curCol]);
        curRow += dr;
        curCol += dc;
        while (board[curRow][curCol].tag != Tag.Default) {
            chessNeedMove.add(board[curRow][curCol]);
            curRow += dr;
            curCol += dc;
        }
    }

    private boolean canMove(int dr, int dc) {
        if (dr == -1) {
            flag:
            for (int j = 0; j < COL; j++) {
                for (int i = 0; i < ROW; i++) {
                    if (chessNeedMove.contains(board[i][j])) {
                        if (!isGround(i - 1, j)) {
                            return false;
                        } else {
                            continue flag;
                        }
                    }
                }
            }
        } else if (dr == 1) {
            flag:
            for (int j = 0; j < COL; j++) {
                for (int i = ROW - 1; i >= 0; i--) {
                    if (chessNeedMove.contains(board[i][j])) {
                        if (!isGround(i + 1, j)) {
                            return false;
                        } else {
                            continue flag;
                        }
                    }
                }
            }
        } else if (dc == -1) {
            flag:
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (chessNeedMove.contains(board[i][j])) {
                        if (!isGround(i, j - 1)) {
                            return false;
                        } else {
                            continue flag;
                        }
                    }
                }
            }
        } else if (dc == 1) {
            flag:
            for (int i = 0; i < ROW; i++) {
                for (int j = COL - 1; j >= 0; j--) {
                    if (chessNeedMove.contains(board[i][j])) {
                        if (!isGround(i, j + 1)) {
                            return false;
                        } else {
                            continue flag;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void moveChess(int dr, int dc) {
        Level.LockMove = true;
        if (dr == 1) {
            System.out.println("-> DOWN");
            AudioManager.getInstance().getAudio("MoveA").play();
            for (int j = 0; j < COL; j++) {
                for (int i = ROW - 1; i >= 0; i--) {
                    if (chessNeedMove.contains(board[i][j])) {
                        swapChess(board[i][j], board[i + dr][j + dc]);
                        board[i + dr][j + dc].move(dr, dc);
                    }
                }
            }
        } else if (dr == -1) {
            System.out.println("-> UP");
            AudioManager.getInstance().getAudio("MoveB").play();
            for (int j = 0; j < COL; j++) {
                for (int i = 0; i < ROW; i++) {
                    if (chessNeedMove.contains(board[i][j])) {
                        swapChess(board[i][j], board[i + dr][j + dc]);
                        board[i + dr][j + dc].move(dr, dc);
                    }
                }
            }
        } else if (dc == 1) {
            System.out.println("-> RIGHT");
            AudioManager.getInstance().getAudio("MoveA").play();
            for (int i = 0; i < ROW; i++) {
                for (int j = COL - 1; j >= 0; j--) {
                    if (chessNeedMove.contains(board[i][j])) {
                        swapChess(board[i][j], board[i + dr][j + dc]);
                        board[i + dr][j + dc].move(dr, dc);
                    }
                }
            }
        } else if (dc == -1) {
            System.out.println("-> LEFT");
            AudioManager.getInstance().getAudio("MoveB").play();
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (chessNeedMove.contains(board[i][j])) {
                        swapChess(board[i][j], board[i + dr][j + dc]);
                        board[i + dr][j + dc].move(dr, dc);
                    }
                }
            }
        }
    }

    //////////////////////////////////////////////////
    public void handleConnecting() {
        hero = findHero(Tag.GreenCharacter);
        if (hero != null) {
            if (!hero.connecting) {
                connectChess(hero.row, hero.col);
            } else {
                disconnectChess(hero.row, hero.col);
            }
            AudioManager.getInstance().getAudio("Grab").play();
        }
    }

    private void connectChess(int row, int col) {
        // duyệt 4 hướng kề nó xem có Chessman nào không
        // có thì set connect, và đi sâu vào kiểm tra tiếp Chess vừa set connect đó.

        board[row][col].connect();
        for (int i = 0; i < 4; i++) {
            int r = row + dirRow[i];
            int c = col + dirCol[i];
            if (board[r][c].tag != Tag.Default) {
                board[row][col].neighbors.add(board[r][c]);
                if (!board[r][c].connecting) {
                    connectChess(r, c);
                }
            }
        }
    }

    private void disconnectChess(int row, int col) {
        board[row][col].disconnect();
        for (int i = 0; i < 4; i++) {
            int r = row + dirRow[i];
            int c = col + dirCol[i];
            if (board[r][c].tag != Tag.Default && board[r][c].connecting) {
                disconnectChess(r, c);
            }
        }
    }

    private void reloadConnect() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].tag != Tag.Default && !board[i][j].connecting) {
                    for (int k = 0; k < 4; k++) {
                        int r = i + dirRow[k];
                        int c = j + dirCol[k];
                        if (board[r][c].connecting) {
                            AudioManager.getInstance().getAudio("Grab").play();
                            board[i][j].connect();
                            board[i][j].neighbors.add(board[r][c]);
                            board[r][c].neighbors.add(board[i][j]);
                        }
                    }
                }
            }
        }
    }

    private void checkUnlockSwitchDoor() {
        int numSwitch = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (DataMap[i][j] == 6 && board[i][j].tag == Tag.Default) {
                    numSwitch++;
                }
            }
        }
        if (numSwitch == 0) {
            Level.OpenSwitchDoor = true;
            AudioManager.getInstance().getAudio("OpenDoor").play();
        }
    }

    //////////////////////////////////////////////////
    @Override
    public void update() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j].update();
            }
        }
        if (Level.FinishMove) {
            Level.FinishMove = false;
            reloadConnect();
        }

        if (Level.StandOnSwitch) {
            Level.StandOnSwitch = false;
            checkUnlockSwitchDoor();
        }
    }

    @Override
    public void render(Graphics2D g) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j].render(g);
            }
        }
    }
}
