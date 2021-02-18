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
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Animation Runner class.
 * This class manage the screen of the game and running
 * the animation to it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor for initialize animation running.
     *
     * @param framesPerSecond the frames per second.
     */
    public AnimationRunner(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        // Initialize GUI with "Arkanoid" title and width, height for the game.
        this.gui = new GUI("Arkanoid", 800, 600);
        this.sleeper = new Sleeper(); // Initialize Sleeper.
    }

    /**
     * Getter for GUI.
     * Other classes would call it to get the gui.
     *
     * @return the gui
     */
    public GUI getGUI() {
        return this.gui;
    }

    /**
     * Run method.
     * This method gets animation and runs it until it get
     * order to stop the run.
     * The running means make the gui show the given animation.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        // Initialize time for frame.
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        /*
         * While-Loop that run until shouldStop is true.
         * the loop calling the doOneFrame method of the specific animation
         * and show it on the gui.
         */
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);  // Calling to perform the animation.
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}