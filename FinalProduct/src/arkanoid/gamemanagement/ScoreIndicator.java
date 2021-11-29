/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gamemanagement;

import arkanoid.gameutilities.Counter;
import arkanoid.interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Score indicator Class.
 * This class in purpose to displaying a score on the surface.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * Constructor for the score indicator.
     * The score calculate due to the Counter so it
     * hold a reference to the scores counter.
     *
     * @param scoreCounter is the score counter
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Drawing the score on the surface.
        d.setColor(Color.BLACK);
        d.drawText(350, 22, "Score: ", 15);
        // Adding the update score.
        d.drawText(400, 22, String.valueOf(this.scoreCounter.getValue()), 15);
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add the score as Sprite to the game.
     *
     * @param g is the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Getter to the score.
     *
     * @return the currently score Counter.
     */
    public Counter getScore() {
        return this.scoreCounter;
    }
}
