/* Author: NgTienHungg */
package game.component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import game.manager.Game;
import game.entity.GameObject;

public class Animation {

    // tạo ra 1 SpriteRenderer mới, và vẽ dựa trên vị trí của parent chứa Animation
    private SpriteRenderer sprRender;
    private BufferedImage[] spriteSheet;
    private int frameCount, currentFrame = 0;
    private float frameTime, currentTime = 0f;

    public Animation(GameObject parent, BufferedImage[] listSprite, float frameTime) {
        this.sprRender = new SpriteRenderer(parent);
        this.spriteSheet = listSprite;
        this.frameCount = listSprite.length;
        this.frameTime = frameTime;
    }

    public void renew() {
        currentFrame = 0;
        currentTime = 0f;
    }

    public void update() {
        currentTime += Game.DELTA_TIME;
        if (currentTime >= frameTime) {
            currentTime -= frameTime;
            currentFrame = (currentFrame + 1) % frameCount;
        }
    }

    public void render(Graphics2D g) {
        sprRender.sprite = spriteSheet[currentFrame];
        sprRender.render(g);
    }
}
