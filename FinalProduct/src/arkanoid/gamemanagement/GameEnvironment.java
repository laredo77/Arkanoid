/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.gamemanagement;

import arkanoid.interfaces.Collidable;
import arkanoid.shapes.Line;
import arkanoid.shapes.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game environment class.
 * This class holding the game collisions and using it
 * info to keep performing the game.
 */
public class GameEnvironment {
    /**
     * List of Collidables.
     * In order to keep the date about collidables,
     * the list storing all in it.
     */
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * Add the given collidable to the environment.
     *
     * @param c is the given collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removing the given collidable from the environment.
     *
     * @param c is the collidable
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, the method return null.
     * Else, it return the information about the closest collision
     * that is going to occur.
     *
     * @param trajectory is the trajectory of the ball while moving.
     * @return the closest collision if had, null otherwise.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Initialize point as infinity to check if exist other closer one.
        Point closest = new Point(Double.POSITIVE_INFINITY,
                Double.POSITIVE_INFINITY);
        Collidable temp = null;

        /*
         * This for-each loop will run on the collidables
         * in the list and will check with others classes and methods if
         * collision occur. If is, it update the closest point, and the
         * temp object to be the collision object.
         */
        for (Collidable c : collidables) {
            if (trajectory.closestIntersectionToStartOfLine(
                    c.getCollisionRectangle()) != null) {
                // There is collision.
                if (trajectory.start().distance(trajectory.
                        closestIntersectionToStartOfLine(
                                c.getCollisionRectangle()))
                        < trajectory.start().distance(closest)) {
                    // If the collision point is the closest, updating
                    // the closest point and the collision object.
                    closest = trajectory.closestIntersectionToStartOfLine(
                            c.getCollisionRectangle());
                    temp = c;
                }
            }
        }
        if (closest.getX() < Double.POSITIVE_INFINITY) {
            // Case there is collision point, returning
            // the closest point and the collision object.
            return new CollisionInfo(closest, temp);
        } else {
            // Case there is not collision point, returning null.
            return null;
        }
    }
}
