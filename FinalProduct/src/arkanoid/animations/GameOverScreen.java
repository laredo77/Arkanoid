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
 * The Game over screen class.
 */
public class GameOverScreen implements Animation {
    private ScoreIndicator scoreIndicator;

    /**
     * Constructor to initialize new Game over screen.
     * In the screen it show the score that the player
     * succeed to gain in the game.
     *
     * @param scoreIndicator is the score indicator
     */
    public GameOverScreen(ScoreIndicator scoreIndicator) {
        this.scoreIndicator = scoreIndicator;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        // Draw image background.
        try {
            String path = "FinalProduct\\resources\\background_images\\Screens\\GameOverScreen.jpg";
            Image img = ImageIO.read(new File(path));
            d.drawImage(0, 0, img);
        } catch (IOException e) {
            System.out.println("Error: IOException catched in MenuAnimation class");
        }

        // Drawing "Game-over" message and performing the score.
        d.setColor(Color.white);
        d.drawText(30, 180, "Game Over. Your score is "
                + scoreIndicator.getScore().getValue(), 50);
    }

    @Override
    public boolean shouldStop() {
        // This animation shouldn't stop from this class.
        return false;
    }
}
