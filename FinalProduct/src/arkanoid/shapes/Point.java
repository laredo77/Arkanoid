/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.shapes;

/**
 * A Point class.
 *
 */
public class Point {
    private double x;  // The x coordinate of a point
    private double y;  // The y coordinate of a point

    /**
     * Construct a point given x and y coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance return the distance of this point to the other point.
     *
     * Using the equation distance between to points:
     * d = sqrt((dx * dx) + (dy * dy))
     * @param other a point to measure the distance to
     * @return the distance to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Check if two given points have the same coordinates.
     * @param other a point to compare coordinate with
     * @return return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            // No point to compare
            return false;
        } else {
            return (this.x == other.getX() && this.y == other.getY());
        }
    }

    /**
     * @return the x coordinate of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * In some cases, it necessary to get the x coordinate
     * as int value.
     * @return the x coordinate of a point
     */
    public int getIntX() {
        return (int) this.x;
    }

    /**
     * In some cases, it necessary to get the y coordinate
     * as int value.
     * @return the y coordinate of a point
     */
    public int getIntY() {
        return (int) this.y;
    }

    /**
     * Setting x coordinate for a point.
     * @param newX is the new x coordinate to change for.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * Setting y coordinate for a point.
     * @param newY is the new y coordinate to change for.
     */
    public void setY(double newY) {
        this.y = newY;
    }

}