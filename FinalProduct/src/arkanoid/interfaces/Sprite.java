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
 * The interface Sprite.
 * This interface hold the drawOn and timepassed methods.
 */
public interface Sprite {
    /**
     * Draw on.
     * This method get called from classes to implement
     * draw the sprite to the screen
     *
     * @param d is the surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}