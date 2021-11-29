/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gamepieces;

import arkanoid.gamemanagement.GameLevel;
import arkanoid.gameutilities.Velocity;
import arkanoid.interfaces.Collidable;
import arkanoid.interfaces.Sprite;
import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The Paddle class.
 * This class implements Sprite and Colidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int paddleSpeed;

    /**
     * Constructor.
     * Instantiates a new Paddle.
     *
     * @param rect     the rectangle of the paddle.
     * @param keyboard the keyboard that the paddle obey to.
     */
    public Paddle(Rectangle rect, biuoop.KeyboardSensor keyboard) {
        this.paddle = rect;
        this.keyboard = keyboard;
    }

    /**
     * Applying Move left.
     * If the keyboard is pressed to left the paddle sets
     * new upperleft x coordinate.
     */
    public void moveLeft() {
        if (paddle.getUpperLeft().getX() < 40) {
            // In case the paddle is crossing its limit and to
            // make sure the paddle dosent cross the left border.
            paddle.getUpperLeft().setX(30);
        } else {
            // Moving the paddle one step left (-paddleSpeed).
            paddle.getUpperLeft().setX(paddle.getUpperLeft().getX()
                    - this.paddleSpeed);
        }
    }

    /**
     * Applying Move right.
     * If the keyboard is pressed to right the paddle sets
     * new upperleft x coordinate
     */
    public void moveRight() {
        if (paddle.getUpperLeft().getX() + paddle.getWidth() > 760) {
            // In case the paddle is crossing its limit and to
            // make sure the paddle dosent cross the right border.
            paddle.getUpperLeft().setX(770 - paddle.getWidth());
        } else {
            // Moving the paddle on step right (+paddleSpeed).
            paddle.getUpperLeft().setX(paddle.getUpperLeft().getX()
                    + this.paddleSpeed);
        }
    }

    // Sprite
    @Override
    public void timePassed() {
        // Moving the paddle Left/Right.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Drawing the paddle on the surface
        Color paddleColor = new Color(255, 180, 0);
        d.setColor(paddleColor);
        d.fillRectangle(paddle.getUpperLeft().getIntX(),
                paddle.getUpperLeft().getIntY(),
                paddle.getIntWidth(), paddle.getIntHeight());
        // Draw the frame
        d.setColor(Color.black);
        d.drawRectangle(paddle.getUpperLeft().getIntX(),
                paddle.getUpperLeft().getIntY(),
                paddle.getIntWidth(), paddle.getIntHeight());
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        // Initialize var for the collision point that occurred on the paddle.
        Point cpOnPaddle = new Point(collisionPoint.getX()
                - paddle.getUpperLeft().getX(), paddle.getUpperLeft().getY());

        double speed = Velocity.getSpeed(currentVelocity); // Ball speed
        // Dividing the paddle into five equal parts.

        double firstFifth = paddle.getWidth() / 5;
        double secondFifth = 2 * firstFifth;
        double thirdFifth = 3 * firstFifth;
        double forthFifth = 4 * firstFifth;

        if (cpOnPaddle.getX() <= firstFifth) {
            // Case the ball hit first fifth - retuning angle is 300
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (cpOnPaddle.getX() > firstFifth && cpOnPaddle.getX()
                <= secondFifth) {
            // Case the ball hit second fifth - returning angle is 330
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if (cpOnPaddle.getX() > secondFifth && cpOnPaddle.getX()
                <= thirdFifth) {
            // Case the ball hit third fifth - retuning angle is 0
            return Velocity.fromAngleAndSpeed(0, speed);
        } else if (cpOnPaddle.getX() > thirdFifth && cpOnPaddle.getX()
                <= forthFifth) {
            // Case the ball hit forth fifth - retuning angle is 30
            return Velocity.fromAngleAndSpeed(30, speed);
        } else {
            // Case the ball hit fifth fifth - retuning angle is 60
            return Velocity.fromAngleAndSpeed(60, speed);
        }
    }

    /**
     * Add this paddle to the game.
     * Paddle is Colliadble and Sprite.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Setter for the paddle speed.
     *
     * @param pSpeed is the pixels the paddle move in one step
     */
    public void setPaddleSpeed(int pSpeed) {
        this.paddleSpeed = pSpeed;
    }
}
