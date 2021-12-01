/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.levelsbackground;

import arkanoid.interfaces.Sprite;
import arkanoid.shapes.Line;
import arkanoid.shapes.Point;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * FinalFour background class.
 */
public class FinalFourBackGround implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {

        // Setting new background color.
        Color background = new Color(102, 178, 255);
        d.setColor(background);
        d.drawRectangle(0, 0, 800, 600);
        // Adding color for the background of the game.
        d.fillRectangle(0, 0, 800, 600);

        Color cloudColor1 = new Color(224, 224, 224);

        // Creating the LEFT clouds rain.
        Point startRainLine = new Point(100, 400);
        Point endRainLine = new Point(80, 600);
        d.setColor(cloudColor1);

        for (int i = 0; i < 10; i++) {
            Line rain = new Line(startRainLine, endRainLine);
            d.drawLine(startRainLine.getIntX(), startRainLine.getIntY(),
                    endRainLine.getIntX(), endRainLine.getIntY());
            startRainLine.setX(startRainLine.getIntX() + 10);
            endRainLine.setX(endRainLine.getIntX() + 10);
        }

        // Creating the RIGHT clouds rain.
        startRainLine.setX(600);
        startRainLine.setY(490);
        endRainLine.setX(580);
        endRainLine.setY(600);

        for (int i = 0; i < 10; i++) {
            Line rain = new Line(startRainLine, endRainLine);
            d.drawLine(startRainLine.getIntX(), startRainLine.getIntY(),
                    endRainLine.getIntX(), endRainLine.getIntY());
            startRainLine.setX(startRainLine.getIntX() + 10);
            endRainLine.setX(endRainLine.getIntX() + 10);
        }

        // Creating the LIGHT GRAY clouds.
        d.setColor(cloudColor1);
        d.fillCircle(100, 400, 20);
        d.fillCircle(120, 420, 25);
        d.fillCircle(600, 490, 20);
        d.fillCircle(620, 520, 25);

        // Creating the MID GRAY clouds.
        Color cloudColor2 = new Color(192, 192, 192);
        d.setColor(cloudColor2);
        d.fillCircle(140, 395, 28);
        d.fillCircle(642, 500, 28);

        // Creating the DARK GRAY clouds.
        Color cloudColor3 = new Color(160, 160, 160);
        d.setColor(cloudColor3);
        d.fillCircle(180, 405, 30);
        d.fillCircle(160, 430, 20);
        d.fillCircle(680, 510, 28);
        d.fillCircle(648, 532, 20);
    }

    @Override
    public void timePassed() {
    }
}
