/* Author: NgTienHungg */
package game.entity;

import java.awt.Graphics2D;
import game.manager.Util;
import game.component.Animation;

import static game.resource.ImageManager.*;

public class Tile extends GameObject {

    public static final float SIZE = 50;

    private Animation animation;

    public Tile(int row, int col, int id) {
        super(Level.X + col * SIZE, Level.Y + row * SIZE, SIZE, SIZE);
        initTile(id);
    }

    private void initTile(int id) {
        switch (id) {
            case 0 ->
                sprRender.sprite = SPR_ground[Util.random(SPR_ground.length)];
            case 1 ->
                sprRender.sprite = SPR_wall[Util.random(SPR_wall.length)];
            case 2 -> {
                sprRender.sprite = SPR_wall[Util.random(SPR_wall.length)];
                animation = new Animation(this, SPR_fire_lamp, 0.15f);
            }
            case 3 ->
                sprRender.sprite = SPR_stairs;
            case 4 ->
                sprRender.sprite = SPR_key_door;
            case 5 ->
                sprRender.sprite = SPR_switch_door;
            case 6 ->
                sprRender.sprite = SPR_switch;
        }
    }

    @Override
    public void update() {
        if (animation != null) {
            animation.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        sprRender.render(g);
        if (animation != null) {
            animation.render(g);
        }
    }
}
