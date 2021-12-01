/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.interfaces;

import arkanoid.gamepieces.Block;

/**
 * Block creator interface.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos the x coordinate of the block location
     * @param ypos the y coordinate of the block location
     * @return creating new Block.
     */
    Block create(int xpos, int ypos);
}
