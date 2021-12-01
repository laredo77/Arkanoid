/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.interfaces;

import arkanoid.gamepieces.Block;
import arkanoid.gameutilities.Velocity;
import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls in the level of the game.
     *
     * @return the amount of balls.
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return the list of the balls velocity.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddle speed.
     * The speed of the paddle is the amount of pixels it
     * adding or subtract from the x axle.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * Paddle width.
     * It dependent to the level.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * Level name string.
     * The level name will be displayed at the top of the screen.
     *
     * @return string of the level name.
     */
    String levelName();

    /**
     * Gets background method.
     * Each level got it own special background.
     *
     * @return Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the blocks list.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of blocks.
     */
    int numberOfBlocksToRemove();
}
