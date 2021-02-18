/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.levelsbackground;

import arkanoid.interfaces.Sprite;
import arkanoid.shapes.Line;
import arkanoid.shapes.Point;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Wide Easy background class.
 */
public class WideEasyBackGround implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        // Initialize Point for the middle of the sun,
        // and new point for the end of the ray.
        Point middleOfSun = new Point(130, 130);
        Point endOfRay = new Point(30, 250);

        // Adding color for the background of the game.
        d.setColor(Color.white);
        d.drawRectangle(0, 0, 800, 600);
        d.fillRectangle(0, 0, 800, 600);

        // Setting the sun rays on the background.
        for (int i = 0; i < 100; i++) {
            Line ray = new Line(middleOfSun, endOfRay);
            Color rayColor = new Color(255, 255, 100);
            d.setColor(rayColor);
            d.drawLine(middleOfSun.getIntX(), middleOfSun.getIntY(),
                    endOfRay.getIntX(), endOfRay.getIntY());
            endOfRay.setX(endOfRay.getIntX() + 7);
        }

        // Initialize the sun and fill it with 3 layers of  different yellow.
        Color sun = new Color(255, 255, 100);
        d.setColor(sun);
        d.fillCircle(middleOfSun.getIntX(), middleOfSun.getIntY(), 60);
        sun = new Color(255, 235, 0);
        d.setColor(sun);
        d.fillCircle(middleOfSun.getIntX(), middleOfSun.getIntY(), 50);
        sun = new Color(255, 215, 0);
        d.setColor(sun);
        d.fillCircle(middleOfSun.getIntX(), middleOfSun.getIntY(), 40);
    }

    @Override
    public void timePassed() {
    }
}
