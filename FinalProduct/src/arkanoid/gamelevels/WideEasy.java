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
import arkanoid.levelsbackground.WideEasyBackGround;
import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The WideEasy class.
 * Level 2.
 */
public class WideEasy implements LevelInformation {
    private List<Velocity> velocities;

    /**
     * WideEasy Constructor.
     * Initialize new list for the ball velocities.
     */
    public WideEasy() {
        this.velocities = new LinkedList<>();
    }
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // Spotting the Balls
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            double speed = 6;
            double angle = -60 + i * 10;
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            this.velocities.add(v);
        }
        for (int i = numberOfBalls() / 2; i < numberOfBalls(); i++) {
            double speed = 6;
            double angle = -30 + i * 10;
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            this.velocities.add(v);
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        // The speed of the paddle is the amount of pixel it goes
        // each movement.
        return 5;
    }

    @Override
    public int paddleWidth() {
        // The paddle Width.
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        // Calling the WideEasy background class and returning it.
        return new WideEasyBackGround();
    }

    @Override
    public List<Block> blocks() {
        // Initialize new Linked list to store the blocks.
        List<Block> blocks = new LinkedList<>();

        // Color array that will used for the blocks.
        java.awt.Color[] color = {Color.RED, Color.RED, Color.ORANGE,
                Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN,
                Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK,
                Color.PINK, Color.CYAN, Color.CYAN };

        int upperX = 30; // Initialize x coordinate for the placement blocks
        int upperY = 250; // Initialize y coordinate for the placement blocks
        int blockWidth = 50;  // Initialize block width
        int blockHeight = 23; // Initialize block height

        /*
         * This for double for loop will place the blocks into the game
         * environment. Each block have own place, and for each line of
         * blocks have the same color, but different from others lines.
         */
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Rectangle rect = new Rectangle(new Point(upperX, upperY),
                    blockWidth, blockHeight);
            // Initialize new block and add it to the list.
            Block b = new Block(rect, color[i]);
            blocks.add(b);
            upperX = upperX + blockWidth;
            if (i == 13) {
                blockWidth = 40;
            }
        }
        // Returning the list of blocks.
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
