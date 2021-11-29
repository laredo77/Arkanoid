/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.animations;
import arkanoid.interfaces.Animation;
import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * HighScore class keeping the highest scores for all
 * games that played.
 */
public class HighScores implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {

        // Draw image background.
        try {
            String path = "FinalProduct\\resources\\background_images\\Screens\\ScoreScreenBackground.jpg";
            Image img = ImageIO.read(new File(path));
            d.drawImage(0, 0, img);
        } catch (IOException e) {
            System.out.println("Error: IOException catched in HighScore class");
        }

        String hsPath = "highscores.txt";

        // Write a message.
        String content;
        try {
            byte[] allBytes = Files.readAllBytes(Paths.get(hsPath));
            content = new String(allBytes);
        } catch (IOException ignored) {
            content = "No game have been played yet!";
        }
        d.setColor(Color.WHITE);
        d.drawText(50, 260, content, 50);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
