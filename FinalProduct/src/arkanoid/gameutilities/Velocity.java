/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gameutilities;

import arkanoid.shapes.Point;
/**
 * A velocity class.
 *
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx; // The change in position on the x axe
    private double dy; // The change in position on the y axe

    /**
     * Constructor to define a velocity.
     *
     * @param dx The change in position on the x axe
     * @param dy The change in position on the y axe
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Using getDx in order to know the dx value of the velocity.
     *
     * @return dx value
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Using getDy in order to know the dy value of the velocity.
     *
     * @return dy value
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Setting new dx value.
     *
     * @param newDx the new dx value to change
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * Setting new dy value.
     *
     * @param newDy the new dy value to change
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * Other way to perform velocity is by using angle and speed, vector.
     * This method calculate the change in the x and y axe's by using
     * the speed value. It using the radians for represent angle and
     * acouridng to this game the coordinate is behave like compass
     * coordinate and the y axe is inverted.
     *
     * @param angle is the degree the velocity point to.
     * @param speed the value of the speed.
     * @return new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Initialize dx, dy and converting angles to radians.
        double dx = -speed * Math.cos(Math.toRadians(angle + 90));
        double dy = -speed * Math.sin(Math.toRadians(angle + 90));
        return new Velocity(dx, dy);
    }

    /**
     * This method is get the actual coordinate of point and adding dx,dy values
     * in purpose to perform move position.
     *
     * @param p point with position (x,y)
     * @return new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * This method gets a velocity and calculate the speed.
     * @param v is the given velocity
     * @return speed
     */
    public static double getSpeed(Velocity v) {
        return Math.sqrt(v.getDx() * v.getDx() + v.getDy() * v.getDy());
    }
}