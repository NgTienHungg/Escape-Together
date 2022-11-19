/* Author: NgTienHungg */
package game.parallaxBG;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ParallaxBackground {

    private int numOfLayers;
    private Layer[] layers;

    public ParallaxBackground(BufferedImage[] images, float[] speeds) {
        numOfLayers = images.length;
        this.layers = new Layer[numOfLayers];
        for (int i = 0; i < numOfLayers; i++) {
            layers[i] = new Layer(images[i], speeds[i]);
        }
    }

    public void update() {
        for (int i = 0; i < numOfLayers; i++) {
            layers[i].update();
        }
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < numOfLayers; i++) {
            layers[i].render(g);
        }
    }
}
