/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gamepieces;

import arkanoid.gamemanagement.CollisionInfo;
import arkanoid.gamemanagement.GameEnvironment;
import arkanoid.gamemanagement.GameLevel;
import arkanoid.gameutilities.Velocity;
import arkanoid.interfaces.Sprite;
import arkanoid.shapes.Line;
import arkanoid.shapes.Point;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A Ball class.
 * <p>
 * this class is defining ball, which have center point radius and color.
 * the ball can draw itself on surface given, perform moves by using
 * Velocity class.
 */
public class Ball implements Sprite {
    private int size;             // The radius of the ball
    private java.awt.Color color; // The color of the ball
    private Point center;         // Center point of the ball using (x, y)
    private Velocity velocity;    // The velocity of the ball while moving
    private GameEnvironment gE;

    /**
     * The first constructor define a ball.
     *
     * @param center Point that representing the center by using (x,y)
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.center = center;
        this.size = radius;
        this.color = color;
    }

    /**
     * Second constructor to define a ball.
     *
     * @param x      the x coordinate of the center of the ball.
     * @param y      the y coordinate of the center of the ball.
     * @param radius the size of the ball radius
     * @param color  the color of the ball
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this.center = new Point(x, y);
        this.size = radius;
        this.color = color;
    }

    /**
     * third constructor to define a ball.
     * Using it for the Game.
     *
     * @param center the center
     * @param radius the radius
     * @param color  the color
     * @param gE     the Game environment that the ball play in.
     */
    public Ball(Point center, int radius, java.awt.Color color,
                GameEnvironment gE) {
        this.center = center;
        this.size = radius;
        this.color = color;
        this.gE = gE;
    }

    /**
     * This method purpose is to identify the center x coordinate.
     *
     * @return center (int) x coordinate
     */
    public int getX() {
        return  center.getIntX();
    }

    /**
     * This method purpose is to identify the center y coordinate.
     *
     * @return center (int) y coordinate
     */
    public int getY() {
        return center.getIntY();
    }

    /**
     * This method identify the radius of the ball.
     *
     * @return the size (radius) of the ball.
     */
    public int getSize() {
        return size;
    }


    /**
     * This method is getting the color of the ball as it specified.
     *
     * @return java.awt.Color variable of the ball color
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * This method making able to set new color to ball.
     *
     * @param newColor the new color to set for the ball.
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }


    /**
     * This method identify the center point of the ball.
     *
     * @return the center point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * This method making able to set new center point to ball.
     *
     * @param newCenter is the point to set for the new ball center
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }


    @Override
    public void drawOn(DrawSurface surface) {
        // Draw the ball on the given DrawSurface.
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());

        // Draw the frame
        surface.setColor(Color.black);
        surface.drawCircle(getX(), getY(), getSize());

        // Draw the center point of the ball in red.
        surface.setColor(Color.red);
        surface.drawCircle(getX(), getY(), 1);
    }

    /**
     * The method setVelocity is making able the user to set new
     * velocity to the ball.
     * the purpose is to using the velocity to perform the ball moving.
     *
     * @param v is the given velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method sharing the same purpose as setVelocity above.
     * It able to set velocity by changing in the x and y point.
     *
     * @param dx is the change in the x. (i.e: end.x-start.x)
     * @param dy is the change in the y. (i.e: end.y-start.y)
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Able to know the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * This method Placement the ball center to new point to perform step.
     *
     * The method make sure the balls keep the game rules such as not
     * penetrate to blocks. In case there is a hit, it adding
     * or subtracting ball radius to the center point.
     */
    public void moveOneStep() {
        // initialize Line for the ball trajectory
        Line trajectory = ballTrajectory();
        // Initialize cInfo to represent the collision.
        CollisionInfo cInfo = getGameEnvironment().getClosestCollision(
                trajectory);

        if (cInfo == null) {
            // No hit in the range of the trajectory, moving the ball center
            // to the next step.
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            // Hit situation.
            if (cInfo.collisionSegment().isHorizontal()) {
                //Case the ball collision with horizontal line
                if (this.getVelocity().getDy() < 0) {
                    // The ball is moving from down to up.
                    this.center.setY(cInfo.collisionPoint().getY() + getSize());
                } else if (this.getVelocity().getDy() > 0) {
                    // The ball is moving from up to down.
                    this.center.setY(cInfo.collisionPoint().getY() - getSize());
                }
            }

            if (cInfo.collisionSegment().isVertical()) {
                //Case the ball collision with vertical line
                if (this.getVelocity().getDx() < 0) {
                    // The ball is moving from right to left.
                    this.center.setX(cInfo.collisionPoint().getX() + getSize());
                } else if (this.getVelocity().getDx() > 0) {
                    // The ball is moving from left to right.
                    this.center.setX(cInfo.collisionPoint().getX() - getSize());
                }
            }
            // Notify the object on hit, and setting new velocity.
            this.setVelocity(cInfo.collisionObject().hit(this,
                    cInfo.collisionPoint(), this.getVelocity()));
        }
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }


    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gE = gameEnvironment;
    }

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gE;
    }

    /**
     * Ball trajectory line.
     * This method uses the ball's start and end points to
     * generate for the ball trajectory line.
     *
     * @return the trajectory line
     */
    public Line ballTrajectory() {
        // Initialize end point for the trajectory
        Point endPoint = new Point(getCenter().getX() + getVelocity().getDx(),
                getCenter().getY() + getVelocity().getDy());

        return new Line(getCenter(), endPoint);
    }

    /**
     * Add to game method.
     * Adding the ball as sprite to the game.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Removing the ball from the game mathod.
     * Removing the ball as sprite from the game.
     *
     * @param g is the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
