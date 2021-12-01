/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.interfaces;

import arkanoid.gamepieces.Ball;
import arkanoid.gameutilities.Velocity;
import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;

/**
 * The interface Collidable.
 * Block and Ball and some more implements it.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     * The object in the classes who implements this interface
     * use this method to know the rectangle it collided with.
     *
     * @return "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     *  Notify the object that it collided at collisionPoint with
     *  a given velocity.
     *  The return is the new velocity expected after the hit (based on
     *  the force the object inflicted on it).
     * @param collisionPoint  the collision point.
     * @param hitter          is the ball that hit the collidable.
     * @param currentVelocity the current velocity.
     * @return the velocity should change after hit happened.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
