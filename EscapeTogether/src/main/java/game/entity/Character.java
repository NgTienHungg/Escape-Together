/* Author: NgTienHungg */
package game.entity;

import java.awt.Graphics2D;
import game.component.Animation;
import static game.entity.TileMap.DataMap;
import game.resource.AudioManager;
import static game.resource.ImageManager.*;

public class Character extends Chessman {

    private Animation normal, square;
    protected boolean underStairs = false;

    public Character(int row, int col, Tag tag) {
        super(row, col, tag);
        Level.NumOfHero++;
        initAnimation();
    }

    private void initAnimation() {
        switch (tag) {
            case BlueCharacter -> {
                normal = new Animation(this, SPR_blue_normal, 0.15f);
                square = new Animation(this, SPR_blue_square, 0.15f);
            }
            case RedCharacter -> {
                normal = new Animation(this, SPR_red_normal, 0.15f);
                square = new Animation(this, SPR_red_square, 0.15f);
            }
            case GreenCharacter -> {
                normal = new Animation(this, SPR_green_normal, 0.15f);
                square = new Animation(this, SPR_green_square, 0.15f);
            }
        }
        animation = normal;
    }

    protected void moveDownStairs() {
        underStairs = true;
        tag = Tag.Default;
        Level.NumOfHero--;

        if (connecting) {
            connecting = false;
            while (!neighbors.isEmpty()) {
                Chessman chessman = neighbors.remove(0);
                if (chessman instanceof Character character && !character.underStairs) {
                    character.moveDownStairs();
                }
            }
        }
    }

    @Override
    protected void stop() {
        super.stop();
        if (DataMap[row][col] == 3) {
            moveDownStairs();
            AudioManager.getInstance().getAudio("MoveDownStairs").play();
        }
    }

    @Override
    protected void connect() {
        if (!underStairs) {
            super.connect();
            animation = square;
        }
    }

    @Override
    protected void disconnect() {
        super.disconnect();
        animation = normal;
    }

    @Override
    public void update() {
        if (!underStairs) {
            super.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (!underStairs) {
            super.render(g);
        }
    }
}
