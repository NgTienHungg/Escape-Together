/* Author: NgTienHungg */
package game.manager;

public class Util {

    public static int random(int n) {
        return (int) ((Math.random() * 100 + 1) % n);
    }

    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static boolean inRange(int value, int min, int max) {
        return (value >= min && value <= max);
    }

    public static boolean inRange(float value, float min, float max) {
        return (value >= min && value <= max);
    }
}
