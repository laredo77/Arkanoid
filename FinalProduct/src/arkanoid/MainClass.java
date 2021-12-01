/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid;

import arkanoid.animations.AnimationRunner;
import arkanoid.gamelevels.*;
import arkanoid.gamemanagement.GameFlow;
import arkanoid.gamemanagement.LevelSpecificationReader;
import arkanoid.interfaces.LevelInformation;
import biuoop.KeyboardSensor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * The main Ass 6 game class.
 */
public class MainClass {
    /**
     * Main for application Ass6Game.
     *
     * @param args no input args
     */
    public static void main(String[] args) {

        DirectHit dh = new DirectHit(); // Initialize level 1.
        WideEasy we = new WideEasy();   // Initialize level 2.
        Blau3 g3 = new Blau3();       // Initialize level 3.
        FinalFour f4 = new FinalFour(); // Initialize level 4.

        // Initialize linked list to store the levels of the alpha version game.
        List<LevelInformation> levels = new LinkedList<>();

        // Initialize linked list to store the levels of the beta version game.
        List<LevelInformation> betaLevels = new LinkedList<>();

        // Initialize new animation runner
        AnimationRunner ar = new AnimationRunner(60);
        // Initialize new keyboard for the game.
        KeyboardSensor keyboard = ar.getGUI().getKeyboardSensor();

        // Adding the DirectHit level to the list of the levels.
        levels.add(dh);
        // Adding the WideEasy level to the list of the levels.
        levels.add(we);
        // Adding the Green3 level to the list of the levels.
        levels.add(g3);
        // Adding the FinalFour level to the list of the levels.
        levels.add(f4);

        // Beta version game
        try {
            LevelSpecificationReader lsr = new LevelSpecificationReader();
            String path = "FinalProduct/resources/definitions/level_definitions/levels.txt";
            Reader reader = new BufferedReader(new FileReader(path));
            List<LevelInformation> levelsFromTextFile = lsr.fromReader(reader);
            betaLevels.addAll(levelsFromTextFile);
        } catch (IOException ignored) {
            ;
        }

        // Initialize new gameFlow object
        GameFlow gameFlow = new GameFlow(ar, keyboard);
        // Run the menu
        gameFlow.runMenu(levels, betaLevels);
    }
}
