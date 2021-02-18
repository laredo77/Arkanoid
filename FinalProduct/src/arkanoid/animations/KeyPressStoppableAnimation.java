/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.animations;

import arkanoid.interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The Key press stoppable animation class.
 * This class manage the stopping animation by pressing special key.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;


    /**
     * Constructor for a new Key press stoppable animation.
     *
     * @param sensor    the keyboard
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, Animation animation) {
        this.keyboard = sensor;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        // If the player press the key to stop the screen the animation
        // should stop showing.
        if (this.keyboard.isPressed("space")) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            } else {
                isAlreadyPressed = false;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}