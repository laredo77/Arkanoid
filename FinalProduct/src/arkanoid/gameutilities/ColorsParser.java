/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gameutilities;

import java.awt.*;
import java.lang.reflect.Field;

/**
 * This class in parser the image colors value from the text to the program.
 */
public class ColorsParser {
    /**
     * Parsering...
     * @param s the level text
     * @return Colors.
     */
    public java.awt.Color colorFromString(String s) {

        if (s.startsWith("RGB")) {
            // Case 1: color representation is in R.G.B numbers.
            String[] rgb = s.substring((s.indexOf("(")) + 1).split(",");
            int r = Integer.parseInt(rgb[0]);
            int g = Integer.parseInt(rgb[1]);
            int b = Integer.parseInt(rgb[2]);
            return new Color(r, g, b);
        } else {
            // Case 2: color representation is a name that might be in Java's library.
            try {
                Field field = Class.forName("java.awt.Color").getField(s);
                return (Color) field.get(null);
            } catch (Exception e) {
                System.out.println("Error in ColorParser class: couldn't parse the given color.");
                return null;
            }
        }
    }
}