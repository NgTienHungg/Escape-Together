/* Author: NgTienHungg */
package game.manager;

import game.resource.AudioManager;
import game.state.GSPlay;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
Current Level
Allow Sound
 */
public class Saver {
    
    private static String fileName = "data/save.txt";
    
    public static void load() {
        try {
            Scanner sc = new Scanner(new File(fileName));

            // read
            GSPlay.CurrentLevel = sc.nextInt();
            AudioManager.AllowSound = sc.nextInt() == 1;
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void save() {
        System.out.println("save");
        try {
            try (FileWriter writer = new FileWriter(fileName)) {
                // write
                writer.write(GSPlay.CurrentLevel + "\n");
                writer.write(String.valueOf((AudioManager.AllowSound ? 1 : 0)));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
