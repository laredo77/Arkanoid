/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.gamemanagement;

import arkanoid.animations.*;
import arkanoid.gameutilities.Counter;
import arkanoid.gameutilities.ShowHighScoresTask;
import arkanoid.interfaces.Animation;
import arkanoid.interfaces.LevelInformation;
import arkanoid.interfaces.Menu;
import arkanoid.interfaces.Task;
import biuoop.KeyboardSensor;
import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * Game Flow class. This class purpose is to make the game
 * flow between the levels and other screens like game over or
 * win screen.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private GameLevel level;
    private ScoreIndicator scoreIndicator;

    /**
     * Constructor for a new Game flow.
     *
     * @param ar is the Animation runner
     * @param ks the keyboard.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
    }

    /**
     * Run the levels method.
     * This method adding to list all the levels and sent it to
     * the gameLevel to initialize the levels.
     *
     * @param levels the levels of the whole game.
     */
    public void runLevels(List<LevelInformation> levels)  {

        // Initialize new counter for the score.
        this.score = new Counter(0);
        // Initialize scoreIndicator object
        this.scoreIndicator = new ScoreIndicator(this.score);

        /*
         * In this loop, for each level info the method add it to the list
         * and calling the gameLevel to initialize it.
         */
        for (LevelInformation levelInfo : levels) {

            this.level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, scoreIndicator);

            level.initialize();
            /*
             * While the animation dose not need to stop its calling the
             * gameLevel to runs it.
             */
            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getAvailableBalls() == 0) {
                // Case no more balls.
                break;
            }
        }

        // The loop breaks: Should followed with Game Over screen or win screen.
        if (level.getAvailableBalls() == 0) {
            // Case no more balls - the player lose.
            // Initialize new keyPress and let it run the game over screen.
            KeyPressStoppableAnimation keyPress = new KeyPressStoppableAnimation(
                    keyboardSensor, new GameOverScreen(scoreIndicator));
            this.animationRunner.run(keyPress);
        } else {
            // Otherwise, the player won.
            // Initialize new keyPressStoppable and let it run the win screen.
            KeyPressStoppableAnimation keyPress = new KeyPressStoppableAnimation(
                    keyboardSensor, new WinScreen(scoreIndicator));
            this.animationRunner.run(keyPress);
        }
    }

    /**
     * Running the menu method.
     * @param levels alpha version game levels
     * @param betaLevels beta version game levels.
     */
    public void runMenu(List<LevelInformation> levels, List<LevelInformation> betaLevels) {

        // Initialize new menu Task
        Menu<Task<Void>> menu = new MenuAnimation<>(keyboardSensor);
        // Initialize new highScore Animation
        Animation highScore = new KeyPressStoppableAnimation(keyboardSensor, new HighScores());
        // Initialize new highScore Task
        Task<Void> highScoreTask = new ShowHighScoresTask(animationRunner, highScore);
        // Adding the 'h' key press to the menu option for
        // showing the highscore screen
        menu.addSelection("h", "Show High Score", highScoreTask);
        // Adding the 'q' key press to the menu option in
        // purpose to quit the program
        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                // Quitting the program
                animationRunner.getGUI().close();
                System.exit(0);
                return null;
            }
        });

        // Adding the 'a' key press to the menu options in
        // purpose to play new alpha version game.
        menu.addSelection("a", "Play alpha version game", new Task<Void>() {
            @Override
            public Void run() {
                // Running the alpha version levels
                runLevels(levels);
                // updateing the highest score when game is over
                updateHighScore();
                return null;
            }
        });

        // Adding the 'b' key press to the menu options in
        // purpose to play new beta version game.
        menu.addSelection("b", "Play beta version game", new Task<Void>() {
            @Override
            public Void run() {
                // Running the beta version levels
                runLevels(betaLevels);
                // updateing the highest score when game is over
                updateHighScore();
                return null;
            }
        });
        /*
         * Runs the user-selected animation.
         * Because the software does not run until 'q' is pressed,
         * the program recursively calls the method.
         */
        animationRunner.run(menu);
        Task<Void> task = menu.getStatus();
        task.run();
        runMenu(levels, betaLevels);
    }

    /**
     * Updateing the highscore method.
     * The method checks whether the high score maintained is higher
     * than the player's current score in the last game.
     * If not, the high score is updated accordingly.
     */
    public void updateHighScore() {
        String hsPath = "highscores.txt";

        try {
            File file = new File(hsPath);
            if (file.createNewFile()) {
                // File dosent exist
                FileWriter writer = new FileWriter(hsPath);
                writer.write("The highest score so far is: " + this.score.getValue());
                writer.close();
            } else {
                // File already exists
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    //remove non-digits characters
                    String clean = data.replaceAll("\\D+", "");
                    int highscore = Integer.parseInt(clean);
                    if (highscore < this.score.getValue()) {
                        FileWriter writer = new FileWriter(hsPath);
                        writer.write("The highest score so far is: " + this.score.getValue());
                        writer.close();
                    }
                }
                reader.close();
            }
        } catch (IOException ioException) {
            System.out.println("An error occurred.");
        }
    }
}
