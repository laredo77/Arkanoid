/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.gamelevels;

import arkanoid.gamepieces.Block;
import arkanoid.gameutilities.Velocity;
import arkanoid.interfaces.LevelInformation;
import arkanoid.interfaces.Sprite;
import java.util.List;

/**
 * Generic level class is generate new levels by demand
 * by user from the file txt.
 * This class is generic, and making new game levels.
 */
public class GenericLevel implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private int numberOfBlocksToRemove;
    private List<Block> blocks;

    /**
     * Instantiates a new GenericLevel -- a level that gets all it's properties from the constructor.
     *
     * @param levelName the name of the level.
     * @param background the background.
     * @param blocks a list of blocks and their properties.
     * @param numBlocks number of blocks to clear in the level.
     * @param pSpeed paddle speed.
     * @param pWidth paddle width.
     * @param velocities list of balls velocities (list size == number of balls).
     */
    public GenericLevel(List<Velocity> velocities, int pSpeed, int pWidth, String levelName,
                        Sprite background, int numBlocks, List<Block> blocks) {
        this.numberOfBalls = velocities.size();
        this.initialBallVelocities = velocities;
        this.paddleSpeed = pSpeed;
        this.paddleWidth = pWidth;
        this.levelName = levelName;
        this.background = background;
        this.numberOfBlocksToRemove = numBlocks;
        this.blocks = blocks;
    }

    /**
     * Return the number of balls in the game.
     *
     * @return the number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Return the initial velocity of each ball.
     *
     * @return a list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * Return paddle speed.
     *
     * @return speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Return paddle width.
     *
     * @return width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Return the level name that displayed at the top of the screen.
     *
     * @return level name.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background as a sprite.
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Return blocks layout.
     *
     * @return list of blocks.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Return the number of blocks to remove before the level is "cleared".
     *
     * @return number of blocks in the level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

}
