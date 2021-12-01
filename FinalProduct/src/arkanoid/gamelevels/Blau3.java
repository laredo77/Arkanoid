/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.gamelevels;

import arkanoid.gamepieces.Block;
import arkanoid.gameutilities.Velocity;
import arkanoid.interfaces.LevelInformation;
import arkanoid.interfaces.Sprite;
import arkanoid.levelsbackground.Blau3BackGround;
import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The Blau3 class.
 * Level 3.
 */
public class Blau3 implements LevelInformation {
    private List<Velocity> velocities;

    /**
     * Blau3 Constructor.
     * Initialize new list for the ball velocities.
     */
    public Blau3() {
        this.velocities = new LinkedList<>();
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // Spotting the balls
        for (int i = 0; i < numberOfBalls(); i++) {
            double speed = 6;
            double angle1 = -30 + i * 60;
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
        return "Blau 3";
    }

    @Override
    public Sprite getBackground() {
        // Calling the Blau3 background class and returning it.
        return new Blau3BackGround();
    }

    @Override
    public List<Block> blocks() {
        // Initialize new Linked list to store the blocks.
        List<Block> blocks = new LinkedList<>();

        // Color array that will used for the blocks.
        java.awt.Color[] color = {Color.LIGHT_GRAY,
                Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE };

        int x = 720;  // Initialize x coordinate for the placement blocks loop
        int y = 150;  // Initialize y coordinate for the placement blocks loop
        int maxCols = 10;     // Variable that store the max blocks in column.
        int maxRows = 5;      // Variable that store the max rows of blocks.
        int blockWidth = 50;  // Initialize block width
        int blockHeight = 25; // Initialize block height

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
                x = x - blockWidth; // Moving the x block width left.
            }
            y = y + blockHeight; // Moving the y block height down.
            x = 720;    // Variable x is back to the star of line point.
            maxCols--;  // Subtracting 1 when starting new line.
        }
        // Returning the list of blocks.
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

}
