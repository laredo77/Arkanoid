/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gameutilities;

import arkanoid.gamemanagement.GameLevel;
import arkanoid.gamepieces.Ball;
import arkanoid.gamepieces.Block;
import arkanoid.interfaces.HitListener;

/**
 * Ball Remover class.
 * This class implements HitListener in purpose to know
 * when to remove ball from the game.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;    // The game
    private Counter remainingBalls; // Counter for balls

    /**
     * Constructor for BallRemover class.
     *
     * @param gameLevel           the game
     * @param remainingBalls the remaining balls in the game
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // There was a hit to the Death-region, first reduce the number
        // of the balls, then remove it from the game.
        hitter.removeFromGame(gameLevel);
        this.remainingBalls.decrease(1);
    }
}
