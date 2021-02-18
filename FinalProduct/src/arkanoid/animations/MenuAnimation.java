/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.animations;

import arkanoid.interfaces.Menu;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Menu Animation class.
 * This class manage the menu, with the several options
 * for the user. it an generic class.
 * @param <T> Generic task.
 */
public class MenuAnimation<T> implements Menu {
    private String key;
    private String message;
    private Object status;
    private KeyboardSensor keyboardSensor;
    private List<String> keys;
    private Map<String, Object> tasks;
    private boolean stop;

    /**
     * Constructor for the menu animation class.
     * @param keyboardSensor is the keyboard the user use.
     */
    public MenuAnimation(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.keys = new LinkedList<>();
        this.tasks = new Hashtable();
        this.stop = false;
    }

    @Override
    public void addSelection(String theKey, String theMessage, Object returnVal) {

        this.message = theKey;
        this.key = theKey;
        this.keys.add(key);
        this.tasks.put(key, returnVal);
    }

    @Override
    public Object getStatus() {
        return this.status;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        // Draw image background.
        try {
            String path = "FinalProduct\\resources\\background_images\\Screens\\MainMenuBackground.jpg";
            Image img = ImageIO.read(new File(path));
            d.drawImage(0, 0, img);
        } catch (IOException e) {
            System.out.println("Error: IOException catched in MenuAnimation class");
        }

        // Drawing the menu
        d.setColor(Color.white);
        d.drawText(120, 200, "-Press 'a' to start new alpha version game", 35);
        d.drawText(120, 240, "-Press 'b' to start new beta version game", 35);
        d.drawText(120, 280, "-Press 'h' to see the highest score", 35);
        d.drawText(120, 320, "-Press 'q' to quit", 35);

        // Waiting for key pressing by the user
        for (String k : keys) {
            if (keyboardSensor.isPressed(k)) {
                this.status = tasks.get(k);
                this.stop = true;
                break;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
