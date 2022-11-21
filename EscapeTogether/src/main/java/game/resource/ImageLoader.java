/* Author: NgTienHungg */
package game.resource;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

    private BufferedImage image;

    public BufferedImage load(String path) throws IOException {

        image = ImageIO.read(getClass().getResource(path));
        int w = image.getWidth(null);
        int h = image.getHeight(null);

        BufferedImage bufImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB); // ARGB để hỗ trợ ảnh trong suốt
        Graphics g = bufImg.getGraphics();
        g.drawImage(image, 0, 0, null);

        return bufImg;
    }
}
