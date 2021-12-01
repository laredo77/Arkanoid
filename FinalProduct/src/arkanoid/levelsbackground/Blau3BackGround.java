/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.levelsbackground;

import arkanoid.interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Green3 background class.
 */
public class Blau3BackGround implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        // Initialize the background color.
        Color bgColor = new Color(0, 0, 130);
        d.setColor(bgColor);
        d.drawRectangle(0, 0, 800, 600);
        // Adding color for the background of the game.
        d.fillRectangle(0, 0, 800, 600);

        // Drawing the building on the left down corner of the screen.
        d.setColor(Color.white);
        d.fillRectangle(80, 420, 100, 180);
        Color wallsColor = new Color(30, 40, 40);
        d.setColor(wallsColor);
        int upX = 80, upY = 420, blackWidth = 10, blackHeight = 180;

        d.fillRectangle(upX, upY, 100, 10); // Drawing the roof.

        for (int i = 0; i < 6; i++) {
            d.fillRectangle(upX, upY, blackWidth, blackHeight);
            upX = upX + 20;
        }

        // Mid building horizontal walls.
        upX = 80;
        upY = 460;
        blackWidth = 100;
        blackHeight = 5;

        for (int i = 0; i < 4; i++) {
            d.fillRectangle(upX, upY, blackWidth, blackHeight);
            upY = upY + 40;
        }

        Color roofRect = new Color(50, 50, 50);
        d.setColor(roofRect);
        d.fillRectangle(115, 360, 40, 60);

        Color antennaColor = new Color(80, 80, 80);
        d.setColor(antennaColor);
        d.fillRectangle(132, 200, 8, 160);

        // Light on top of the antenna
        Color ligthColor = new Color(255, 180, 0);
        d.setColor(ligthColor);
        d.fillCircle(136, 190, 10);
        Color ligthColor2 = new Color(230, 90, 90);
        d.setColor(ligthColor2);
        d.fillCircle(136, 190, 7);
        d.setColor(Color.white);
        d.fillCircle(136, 190, 3);
    }

    @Override
    public void timePassed() {
    }
}
