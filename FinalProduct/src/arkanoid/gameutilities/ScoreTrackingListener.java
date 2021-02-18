/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gameutilities;

import arkanoid.gamepieces.Ball;
import arkanoid.gamepieces.Block;
import arkanoid.interfaces.HitListener;

/**
 * The Score tracking listener class.
 * This class updating the player score during the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * holding reference for the score Counter.
     *
     * @param scoreCounter is the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Hit occur. adding 5 point to the score.
       this.currentScore.increase(5);
    }
}