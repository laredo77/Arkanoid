/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gamelevels;

import arkanoid.gamepieces.Block;
import arkanoid.gameutilities.Velocity;
import arkanoid.interfaces.LevelInformation;
import arkanoid.interfaces.Sprite;
import arkanoid.levelsbackground.DirectHitBackGround;
import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The Direct Hit class.
 * Level 1.
 */
public class DirectHit implements LevelInformation {
    private List<Velocity> velocities;

    /**
     * DirectHit Constructor.
     * Initialize new list for the ball velocities.
     */
    public DirectHit() {
        this.velocities = new LinkedList<>();
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // Spotting the ball
        this.velocities.add(new Velocity(0, -7));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        // The speed of the paddle is the amount of pixel it goes
        // each movement.
        return 10;
    }

    @Override
    public int paddleWidth() {
        // The paddle Width.
        return 70;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        // Calling the Direct Hit background class and returning it.
        return new DirectHitBackGround();
    }

    @Override
    public List<Block> blocks() {
        // Initialize new Linked list to store the blocks.
        List<Block> blocks = new LinkedList<>();

        // Initialize new block, and add it to the list.
        Point p = new Point(385, 135);
        Rectangle rct = new Rectangle(p, 30, 30);
        Block b = new Block(rct, Color.red);
        blocks.add(b);
        // Return the list.
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
