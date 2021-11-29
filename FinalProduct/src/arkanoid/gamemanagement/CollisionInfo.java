/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gamemanagement;

import arkanoid.interfaces.Collidable;
import arkanoid.shapes.Line;
import arkanoid.shapes.Point;

/**
 * The type Collision info.
 * This call gathering all the information needed of collision.
 */
public class CollisionInfo {
    // Initialize variable for the collision point
    // and variable for the collision object.
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor.
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * This method return the point at which the collision occurs
     * between two objects.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object.
     *
     * @return the object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }

    /**
     * This method using the collisionObject and analyze the segment
     * on the object that the collision occur.
     *
     * @return the line on the collision object that the collision occur.
     */
    public Line collisionSegment() {
        return collisionObject().getCollisionRectangle().getMatchLine(
                collisionPoint());
    }
}
