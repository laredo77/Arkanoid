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
import arkanoid.levelsbackground.FinalFourBackGround;
import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Final four class.
 * Level 4.
 */
public class FinalFour implements LevelInformation {
    private List<Velocity> velocities;

    /**
     * FinalFour Constructor.
     * Initialize new list for the ball velocities.
     */
    public FinalFour() {
        this.velocities = new LinkedList<>();
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // Spotting the Balls
        for (int i = 0; i < numberOfBalls(); i++) {
            double speed = 6;
            double angle1 = -35 + i * 35;
            Velocity v = Velocity.fromAngleAndSpeed(angle1, speed);
            this.velocities.add(v);
        }
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        // Calling the Final Four background class and returning it.
        return new FinalFourBackGround();
    }

    @Override
    public List<Block> blocks() {
        // Initialize new Linked list to store the blocks.
        List<Block> blocks = new LinkedList<>();
        // Color array that will used for the blocks.
        java.awt.Color[] color = {Color.LIGHT_GRAY, Color.RED, Color.YELLOW,
                Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN };

        int x = 30;  // Initialize x coordinate for the placement blocks loop
        int y = 100;  // Initialize y coordinate for the placement blocks loop
        int maxCols = 15;     // Variable that store the max blocks in column.
        int maxRows = 7;      // Variable that store the max rows of blocks.
        int blockWidth = 50;  // Initialize block width
        int blockHeight = 22; // Initialize block height

        /*
         * This for double for loop will place the blocks into the game
         * environment. Each block have own place, and for each line of
         * blocks have the same color, but different from others lines.
         */
        for (int i = 0; i < maxRows; i++) {
            // The upper loop is to place the lines in rows
            for (int j = 0; j < maxCols; j++) {
                // The inner loop is to place the blocks in lines.
                Rectangle rectangle = new Rectangle(new Point(x, y),
                        blockWidth, blockHeight);
                // Initialize new block and add it to the list.
                Block block = new Block(rectangle, color[i]);
                blocks.add(block);
                x = x + blockWidth; // Moving the x block width right.

                if (j == maxCols - 2) {
                    // The last block had -10 width to make sure all the blocks
                    // between the boarders.
                    blockWidth = blockWidth - 10;
                }
            }
            blockWidth = 50;
            y = y + blockHeight; // Moving the y block height down.
            x = 30;              // Variable x is back to the star of line point
        }
        // Returning the list of blocks.
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
