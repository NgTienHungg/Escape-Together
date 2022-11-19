/* Author: NgTienHungg */
package game.resource;

import game.manager.Saver;

/* 
    Background
    MoveA
    MoveB
    MoveDownStairs
    Grab
    StandOnSwitch
    OpenDoor
 */
public class AudioManager {

    private static AudioManager instance;
    public static boolean AllowSound;
    public Audio themeMusic;

    private AudioManager() {
        themeMusic = getAudio("Background");
        themeMusic.loop();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public Audio getAudio(String name) {
        return new Audio(name);
    }

    public void enable() {
        AudioManager.AllowSound = true;
        Saver.save();
        themeMusic.playMusic();
    }

    public void disable() {
        AudioManager.AllowSound = false;
        Saver.save();
        themeMusic.stop();
    }
}

// làm theo cách load sẵn tất cả các Audio
// -> khi di chuyển nhanh, sfx của lần di chuyển 1 chưa play xong thì đã bị reset để play cho lần 2
