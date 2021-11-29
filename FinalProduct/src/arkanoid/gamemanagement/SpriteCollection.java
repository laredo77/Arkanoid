/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gamemanagement;

import arkanoid.interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The Sprite collection class.
 */
public class SpriteCollection {
    /**
     * Initialize ArrayList to store the sprites.
     */
    private List<Sprite> sprite = new ArrayList<Sprite>();

    /**
     * Add sprite to the list method.
     *
     * @param s is the new sprite to add.
     */
    public void addSprite(Sprite s) {
        sprite.add(s);
    }

    /**
     * Removing sprite to the list method.
     *
     * @param s is the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprite.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Running in for-each loop on the list of the sprites
        for (int i = 0; i < sprite.size(); i++) {
            sprite.get(i).timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     *
     * @param d is the draw surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        // Running in for-each loop on the list of the sprites.
        for (Sprite s : sprite) {
            s.drawOn(d);
        }
    }
}
