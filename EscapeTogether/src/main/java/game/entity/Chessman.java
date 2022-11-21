/* Author: NgTienHungg */
package game.entity;

import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import game.component.Animation;
import game.resource.AudioManager;
import static game.entity.TileMap.DataMap;
import static game.resource.ImageManager.*;
import static game.manager.Game.DELTA_TIME;

public class Chessman extends GameObject {

    protected BufferedImage image;
    protected Animation animation;

    // Lưu những chessman xung quanh có connect với nó
    protected List<Chessman> neighbors = new ArrayList<>();

    protected int row, col;
    protected float speed = 320;
    protected boolean moving = false;
    protected boolean connecting = false;

    public Chessman(int row, int col, Tag tag) {
        super(Level.X + col * Tile.SIZE, Level.Y + row * Tile.SIZE, Tile.SIZE, Tile.SIZE);
        this.tag = tag;
        this.row = row;
        this.col = col;
        initView();
    }

    private void initView() {
        switch (tag) {
            case Box -> {
                image = SPR_box;
            }
            case Key -> {
                image = SPR_key;
            }
        }
    }

    protected void move(int dr, int dc) {
        if (!moving) {
            moving = true;
            row += dr;
            col += dc;
        }
    }

    protected void stop() {
        moving = false;
        Level.LockMove = false;
        Level.FinishMove = true;

        transform.position.x = Level.X + Tile.SIZE * col;
        transform.position.y = Level.Y + Tile.SIZE * row;

        if (DataMap[row][col] == 6) {
            AudioManager.getInstance().getAudio("StandOnSwitch").play();
            Level.StandOnSwitch = true;
        }
    }

    protected void connect() {
        connecting = true;
    }

    protected void disconnect() {
        connecting = false;
    }

    @Override
    public void update() {
        if (moving) {
            float dx = transform.position.x - (Level.X + Tile.SIZE * col);
            float dy = transform.position.y - (Level.Y + Tile.SIZE * row);
            float ds = speed * DELTA_TIME; // quãng đường đi được trong 1 frame

            if (Math.abs(dx) <= ds && Math.abs(dy) <= ds) {
                stop();
                return;
            }

            if (dx != 0) {
                transform.position.x -= dx / Math.abs(dx) * speed * DELTA_TIME;
            }
            if (dy != 0) {
                transform.position.y -= dy / Math.abs(dy) * speed * DELTA_TIME;
            }
        }
        if (animation != null) {
            animation.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (image != null) {
            sprRender.sprite = image;
            sprRender.render(g);
        } else if (animation != null) {
            animation.render(g);
        }

        sprRender.sprite = image;
        sprRender.render(g);
    }
}
