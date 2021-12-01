/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.levelsbackground;

import arkanoid.interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Direct Hit background class.
 */
public class DirectHitBackGround implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        // Setting the background color.
        d.setColor(Color.black);
        // Draw the background
        d.drawRectangle(0, 0, 800, 600);
        // Adding color for the background of the game.
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.blue);
        // Draw in blue the circles and lines as needed.
        d.drawCircle(400, 150, 100);
        d.drawCircle(400, 150, 75);
        d.drawCircle(400, 150, 50);
        d.drawLine(400, 130, 400, 30);
        d.drawLine(400, 170, 400, 270);
        d.drawLine(380, 150, 280, 150);
        d.drawLine(420, 150, 520, 150);
    }

    @Override
    public void timePassed() {
    }
}

