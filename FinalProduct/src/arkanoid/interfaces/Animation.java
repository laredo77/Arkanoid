/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.interfaces;

import biuoop.DrawSurface;
/**
 * The interface Animation.
 */
public interface Animation {

    /**
     * Do one frame.
     * this method will use to implement one frame of animation
     * in the game.
     *
     * @param d is the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop method will manage if stop the frame or keep
     * it running.
     *
     * @return true if stop or false to keep the animation run.
     */
    boolean shouldStop();
}