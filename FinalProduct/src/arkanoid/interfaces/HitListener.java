/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.interfaces;

import arkanoid.gamepieces.Ball;
import arkanoid.gamepieces.Block;

/**
 * The interface Hit listener.
 * This interface realized by objects that will be the listeners.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit is the block that being hit
     * @param hitter   is the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}