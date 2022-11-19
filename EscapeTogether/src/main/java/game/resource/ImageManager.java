/* Author: NgTienHungg */
package game.resource;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageManager {

    private ImageLoader loader;
    private String spritePath = "/sprites/";

    public static BufferedImage[] SPR_avatar;
    public static BufferedImage SPR_logo_pro;
    public static BufferedImage SPR_logo_it;

    public static BufferedImage SPR_mouse;
    public static BufferedImage SPR_cover;
    public static BufferedImage SPR_black;

    public static BufferedImage SPR_title_escape;
    public static BufferedImage SPR_title_together;
    public static BufferedImage SPR_title_congra;

    public static BufferedImage SPR_sound_on;
    public static BufferedImage SPR_sound_off;

    public static BufferedImage[] SPR_parallax_bg;
    public static BufferedImage[] SPR_background;

    public static BufferedImage[] SPR_blue_normal, SPR_blue_square;
    public static BufferedImage[] SPR_red_normal, SPR_red_square;
    public static BufferedImage[] SPR_green_normal, SPR_green_square;

    public static BufferedImage[] SPR_ground;
    public static BufferedImage[] SPR_wall;
    public static BufferedImage[] SPR_fire_lamp;
    public static BufferedImage SPR_stairs;
    public static BufferedImage SPR_box;
    public static BufferedImage SPR_key;
    public static BufferedImage SPR_key_door;
    public static BufferedImage SPR_switch_door;
    public static BufferedImage SPR_switch;
    public static BufferedImage SPR_cursor;

    public ImageManager() {
        loader = new ImageLoader();
        loadAllSprite();
    }

    private void loadAllSprite() {
        SPR_avatar = loadSprite("avatar", 5);
        SPR_logo_pro = loadSprite("/logo/PROPTIT");
        SPR_logo_it = loadSprite("/logo/ITPTIT");

        SPR_mouse = loadSprite("Mouse");
        SPR_cover = loadSprite("Cover");
        SPR_black = loadSprite("Black");

        SPR_title_escape = loadSprite("Escape");
        SPR_title_together = loadSprite("Together");
        SPR_title_congra = loadSprite("Congratulations");

        SPR_sound_on = loadSprite("SoundOn");
        SPR_sound_off = loadSprite("SoundOff");

        SPR_parallax_bg = loadSprite("/parallax_bg", 4);
        SPR_background = loadSprite("background", 3);

        SPR_blue_normal = loadSprite("/blue/normal", 8);
        SPR_blue_square = loadSprite("/blue/square", 8);

        SPR_red_normal = loadSprite("/red/normal", 8);
        SPR_red_square = loadSprite("/red/square", 8);

        SPR_green_normal = loadSprite("/green/normal", 8);
        SPR_green_square = loadSprite("/green/square", 10);

        SPR_ground = loadSprite("ground", 4);
        SPR_wall = loadSprite("wall", 8);
        SPR_fire_lamp = loadSprite("fire_lamp", 5);

        SPR_stairs = loadSprite("Stairs");
        SPR_box = loadSprite("Box");
        SPR_key = loadSprite("Key");
        SPR_key_door = loadSprite("KeyDoor");
        SPR_switch_door = loadSprite("SwitchDoor");
        SPR_switch = loadSprite("Switch");
        SPR_cursor = loadSprite("Cursor");
    }

    private BufferedImage loadSprite(String name) {
        BufferedImage image = null;
        try {
            image = loader.load(spritePath + name + ".png");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return image;
    }

    private BufferedImage[] loadSprite(String name, int count) {
        BufferedImage[] list = new BufferedImage[count];
        for (int i = 0; i < count; i++) {
            try {
                list[i] = loader.load(spritePath + name + "/" + i + ".png");
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        return list;
    }

    public static BufferedImage pixelImage(BufferedImage image, int xPixel, int yPixel) {
        BufferedImage bufferImg = new BufferedImage(xPixel, yPixel, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) bufferImg.getGraphics();
        AffineTransform at = new AffineTransform();
        at.scale((float) xPixel / image.getWidth(), (float) yPixel / image.getHeight());
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(image, op, 0, 0);
        return bufferImg;
    }

    public static BufferedImage alphaImage(BufferedImage image, int a) {
        if (image == null) {
            return null;
        }
        BufferedImage tmp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = tmp.getGraphics();
        g.drawImage(image, 0, 0, null);
        int width = tmp.getWidth();
        int height = tmp.getHeight();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int p, A, R, G, B;
                p = tmp.getRGB(xx, yy);
                A = (p >> 24) & 0xff;
                if (A == 0) {
                    continue;
                }
                R = (p >> 16) & 0xff;
                G = (p >> 8) & 0xff;
                B = p & 0xff;
                p = (a << 24) | (R << 16) | (G << 8) | B;
                tmp.setRGB(xx, yy, p);
            }
        }
        return tmp;
    }

    public static BufferedImage colorImage(BufferedImage image, int R, int G, int B) {
        if (image == null) {
            return null;
        }
        BufferedImage tmp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = tmp.getGraphics();
        g.drawImage(image, 0, 0, null);

        int width = tmp.getWidth();
        int height = tmp.getHeight();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int p, a;
                p = tmp.getRGB(xx, yy);
                a = (p >> 24) & 0xff;
                p = (a << 24) | (R << 16) | (G << 8) | B;
                tmp.setRGB(xx, yy, p);
            }
        }
        return tmp;
    }

    public static BufferedImage colorImage(BufferedImage image, Color color) {
        int R = color.getRed(), G = color.getGreen(), B = color.getBlue();
        return colorImage(image, R, G, B);
    }
}
