/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.animations;

import arkanoid.gamemanagement.ScoreIndicator;
import arkanoid.interfaces.Animation;
import biuoop.DrawSurface;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The Win screen class.
 */
public class WinScreen implements Animation {
    private ScoreIndicator scoreIndicator;

    /**
     * Constructor to initialize new Winning screen.
     * In the screen it show the score that the player
     * succeed to gain in the game after cleaning all the
     * blocks in the whole levels.
     *
     * @param scoreIndicator the score indicator.
     */
    public WinScreen(ScoreIndicator scoreIndicator) {
        this.scoreIndicator = scoreIndicator;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Draw image background.
        try {
            String path = "FinalProduct\\resources\\background_images\\Screens\\YouWinScreen.jpg";
            Image img = ImageIO.read(new File(path));
            d.drawImage(0, 0, img);
        } catch (IOException e) {
            System.out.println("Error: IOException catched in WinScreen class");
        }

        // Drawing "You win!" message and performing the score.
        d.setColor(Color.white);
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is "
                + scoreIndicator.getScore().getValue(), 50);
    }

    @Override
    public boolean shouldStop() {
        // This animation shouldn't stop from this class.
        return false;
    }
}
