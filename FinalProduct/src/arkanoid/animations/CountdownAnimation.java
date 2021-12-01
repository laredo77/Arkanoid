/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.animations;

import arkanoid.gamemanagement.SpriteCollection;
import arkanoid.interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for 2 seconds, and on top of them it will show
 * a countdown from 3 back to 1, where each number will
 * appear on the screen for one seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean stop;
    private GUI gui;

    /**
     * Constructor to create a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     * @param gui          the gui
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen, GUI gui) {
        this.numOfSeconds = numOfSeconds; // How long the animation will run.
        this.countFrom = countFrom;       // Start the counting from
        this.gameScreen = gameScreen;     // The screen of the game it show on.
        this.sleeper = new Sleeper();     // Initialize new Sleeper.
        this.stop = false;                // Determine when stop the animation.
        this.gui = gui;                   // Getting the gui.
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int countDownSeconds = 3; // Initialize the count down var

        /*
         * This while-loop will run until the countdown is running.
         * It will show on the screen with the game on the background
         * the countdown, each frame for one second.
         */
        while (countFrom >= 0) {
            d = gui.getDrawSurface();
            // Drawing all the sprites on the game screen.
            this.gameScreen.drawAllOn(d);
            // Setting color for the count down text
            d.setColor(Color.RED);
            d.drawText(380, d.getHeight() / 2, "" + countDownSeconds, 60);

            if (numOfSeconds >= countFrom) {
                sleeper.sleepFor(1000);
            }
            // Performing the frame to the screen.
            this.gui.show(d);
            countDownSeconds--; // Count goes down.
            countFrom--;
        }
        // Out for the loop, calling to stop the animation.
        this.stop = true;
    }

    /**
     * Should stop method.
     *
     * @return true if yes, false if no.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
