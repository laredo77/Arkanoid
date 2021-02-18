/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
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

/**
 * The Pause screen class.
 * This class managing the screen that shows when the
 * player decide to pause the game.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {

        // Draw image background.
        try {
            String path = "FinalProduct\\resources\\background_images\\Screens\\PauseScreen.jpg";
            Image img = ImageIO.read(new File(path));
            d.drawImage(0, 0, img);
        } catch (IOException e) {
            System.out.println("Error: IOException catched in PauseScreen class");
        }

        // Drawing pause massage, and how to continue the game again.
        d.setColor(Color.white);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 40);
    }

    @Override
    public boolean shouldStop() {
        // This animation shouldn't stop from this class.
        return false;
    }
}