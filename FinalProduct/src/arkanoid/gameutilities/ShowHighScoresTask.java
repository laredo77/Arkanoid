/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.gameutilities;

import arkanoid.animations.AnimationRunner;
import arkanoid.interfaces.Animation;
import arkanoid.interfaces.Task;
/**
 * High Score Task class.
 */
public class ShowHighScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * Constructor.
     * @param runner animation runner
     * @param highScoresAnimation highscore screen animation
     */
    public ShowHighScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }
    /**
     * This method calls the animation runner to run the highscore animation.
     * @return null.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }

}
