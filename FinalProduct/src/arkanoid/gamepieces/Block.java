/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gamepieces;

import arkanoid.gamemanagement.GameLevel;
import arkanoid.gameutilities.Velocity;
import arkanoid.interfaces.Collidable;
import arkanoid.interfaces.HitListener;
import arkanoid.interfaces.HitNotifier;
import arkanoid.interfaces.Sprite;
import arkanoid.shapes.Line;
import arkanoid.shapes.Rectangle;
import arkanoid.shapes.Point;
import biuoop.DrawSurface;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Block class.
 * This class implements Collidable and Sprite
 * interface's for the block object.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private java.awt.Color color; // The block color
    private Rectangle block;      // The block rectangle
    private List<HitListener> hitListeners;
    private Image img = null;

    /**
     * Constructor.
     * Instantiates a new Block.
     *
     * @param rect  the shape of a block is rectangle.
     * @param color the color of the block.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.block = rect;
        this.color = color;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * Second constructor in purpose to get an image.
     * @param rect block rectangle
     * @param img the block image
     */
    public Block(Rectangle rect, Image img) {
        this.block = rect;
        this.img = img;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This method is getting the color of the block as it specified.
     *
     * @return java.awt.Color variable of the ball color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    public void drawOn(DrawSurface surface) {

        if (this.color != null) {
            // Draw the block on the surface.
            surface.setColor(getColor());
            surface.fillRectangle(this.block.getUpperLeft().getIntX(),
                    this.block.getUpperLeft().getIntY(),
                    this.block.getIntWidth(), this.block.getIntHeight());
        } else {
            // Draw image
            surface.drawImage(this.block.getUpperLeft().getIntX(),
                    this.block.getUpperLeft().getIntY(), img);

        }
        // Drawing the block frame with black
        surface.setColor(Color.black);
        surface.drawRectangle(this.block.getUpperLeft().getIntX(),
                this.block.getUpperLeft().getIntY(),
                this.block.getIntWidth(), this.block.getIntHeight());

    }

    @Override
    public void timePassed() {
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        // Initialize new Line to represent the collision line on the block.
        Line collisionLine = this.block.getMatchLine(collisionPoint);
        // newDx and newDy is the new velocity that could change.
        double newDx = -currentVelocity.getDx();
        double newDy = -currentVelocity.getDy();

        this.notifyHit(hitter);

        if (collisionLine.isVertical()) {
            // case the ball hit the left or right border of a block
            currentVelocity.setDx(newDx);
        }
        if (collisionLine.isHorizontal()) {
            // case the ball hit the up or down border of a block
            currentVelocity.setDy(newDy);
        }
        return currentVelocity;
    }

    /**
     * Adding to the game method.
     * Block is kind of object that collidable and as well sprite.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove the block from the game method.
     * Block implement Sprite and Collidable so this method
     * call those to remove the block out of the game.
     *
     * @param gameLevel is the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * This private method notify the listeners of block that
     * hit occur.
     *
     * @param hitter the ball that hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    @Override
    public void addHitListener(HitListener hl) {
        // Adding HitListener to the list.
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        // Removing HitListener from the list.
        hitListeners.remove(hl);
    }

    /**
     * A getter for block's width.
     *
     * @return block's width.
     */
    public int getBlockWidth() {
        return (int) this.block.getWidth();
    }

    /**
     * A getter for block's height.
     *
     * @return block's height.
     */
    public int getBlockHeight() {
        return (int) this.block.getHeight();
    }
}
