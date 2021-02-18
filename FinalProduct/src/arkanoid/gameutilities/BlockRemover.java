/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gameutilities;

import arkanoid.gamemanagement.GameLevel;
import arkanoid.gamepieces.Ball;
import arkanoid.gamepieces.Block;
import arkanoid.interfaces.HitListener;

/**
 * Block Remover class.
 * This class implements HitListener in purpose to know
 * when to remove block out of the game.
 * As well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;     // The game
    private Counter remainingBlocks; // Counter for the blocks remained.

    /**
     * Constructor for the BlockRemover class.
     *
     * @param gameLevel          is the game
     * @param removedBlocks the remain blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // At first it decrease the amount of the blocks in the game by one,
        // second it remove the block and it listener.
        this.remainingBlocks.decrease(1);
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
    }
}