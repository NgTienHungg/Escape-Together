/* Author: NgTienHungg */
package game.resource;

import java.net.URL;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

    private Clip clip;
    private String pathSound = "/sounds/";

    public Audio(String name) {
        try {
            String path = pathSound + name + ".wav";
            URL url = getClass().getResource(path);
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
    }

    public void play() {
        if (AudioManager.AllowSound) {
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }

    public void playMusic() {
        if (AudioManager.AllowSound) {
            clip.start();
        }
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
