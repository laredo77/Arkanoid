/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.gamemanagement;

import arkanoid.animations.AnimationRunner;
import arkanoid.animations.CountdownAnimation;
import arkanoid.animations.KeyPressStoppableAnimation;
import arkanoid.animations.PauseScreen;
import arkanoid.gamepieces.Ball;
import arkanoid.gamepieces.Block;
import arkanoid.gamepieces.Paddle;
import arkanoid.gameutilities.*;
import arkanoid.gameutilities.Counter;
import arkanoid.interfaces.Animation;
import arkanoid.interfaces.Collidable;
import arkanoid.interfaces.LevelInformation;
import arkanoid.interfaces.Sprite;
import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * Game class.
 * This class manage a game. It initialize and run game by
 * using ball, blocks and paddle.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInfo;
    private KeyboardSensor keyboardSensor;
    private ScoreIndicator scoreIndicator;


    /**
     * Constructor.
     * Instantiates a new Game.
     *
     * @param levelInfo is the information about level
     * @param keyboardSensor is the keyboard using in the game.
     * @param runner is the animation runner.
     * @param scoreIndicator is the score indicator.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner runner, ScoreIndicator scoreIndicator) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInfo = levelInfo;
        this.keyboardSensor = keyboardSensor;
        this.runner = runner;
        this.running = true;
        this.scoreIndicator = scoreIndicator;
    }

    /**
     * Add collidable objects.
     *
     * @param c is the Collidable type to add to the collidable list.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite objects.
     *
     * @param s is the Sprite type to add to the Sprite list.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * Initialize a new game: create the Blocks, Ball and Paddle
     * and add them to the game.
     * Some sprite will implement as listeners to make the game going on.
     */

    public void initialize() {

        // Initialize game background and add it to the SpriteCollection.
        Sprite levelBackground = this.levelInfo.getBackground();
        this.sprites.addSprite(levelBackground);

        // Initialize new counters for blocks, balls, and total score.
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        this.score = this.scoreIndicator.getScore();

        // Initialize BlockRemover object
        BlockRemover blockR = new BlockRemover(this, this.blockCounter);

        // Initialize BallRemover object
        BallRemover ballR = new BallRemover(this, this.ballCounter);

        // Initialize ScoreTrackingListener object as a listener
        ScoreTrackingListener scoreTL = new ScoreTrackingListener(this.score);

        /*
         * Setting game boarders.
         */
        // Initialize rectangle for the up border
        Rectangle rectangle1 = new Rectangle(new Point(0, 0), 800, 30);
        Block upBorder = new Block(rectangle1, Color.DARK_GRAY);
        upBorder.addToGame(this); // Add the up border to the game

        // Initialize rectangle for the left border
        Rectangle rectangle2 = new Rectangle(new Point(0, 30), 30, 570);
        Block leftBorder = new Block(rectangle2, Color.DARK_GRAY);
        leftBorder.addToGame(this); // Add the left border to the game

        // Initialize rectangle for the right border
        Rectangle rectangle3 = new Rectangle(new Point(770, 30), 30, 570);
        Block rightBorder = new Block(rectangle3, Color.DARK_GRAY);
        rightBorder.addToGame(this); // Add the right border to the game

        // Adding scoreIndicator as Sprite to the game.
        scoreIndicator.addToGame(this);

        // Initialize rectangle for the Death-Region
        Rectangle rectangle4 = new Rectangle(new Point(0, 600), 800, 0);
        Block deathRegion = new Block(rectangle4, Color.RED);
        // Add the Death region to the game
        deathRegion.addToGame(this);
        // Add the Death-Region to be BallRemover listener
        deathRegion.addHitListener(ballR);

        // Initialize var in purpose to set the paddle
        // in the middle of the screen
        int fixedPaddleX = (int) (400 - this.levelInfo.paddleWidth() / 2);
        // Initialize Paddle as a rectangle with keyboard.
        Rectangle rectPaddle = new Rectangle(new Point(fixedPaddleX, 555),
                this.levelInfo.paddleWidth(), 15);
        Paddle paddle = new Paddle(rectPaddle, this.keyboardSensor);
        paddle.setPaddleSpeed(this.levelInfo.paddleSpeed());
        paddle.addToGame(this); // Adding the paddle to the game

        /*
         * For-loop to initialize the amount of blocks into the game.
         * Each level has it own amount of blocks, so the loop run until
         * the numberOfBlocks.
         */
        for (int i = 0; i < this.levelInfo.numberOfBlocksToRemove(); i++) {
            Block block = this.levelInfo.blocks().get(i);
            block.addToGame(this);
            // Increasing the amount of the blocks
            this.blockCounter.increase(1);
            // Add the block to be BlockRemover listener
            block.addHitListener(blockR);
            // Add the block to be ScoreTrackingListener listener
            block.addHitListener(scoreTL);
        }

        // Spotting the Balls into the game
        int n = this.levelInfo.numberOfBalls();
        int d = 0;

        for (int i = 0; i < n; i++) {
            if (n % 2 == 0) {
                d = 5;
            }
            Ball ball = new Ball(400 + d - 10 * (n / 2) + i * 10, 550, 5, Color.WHITE);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        // countdown before turn starts.
       this.runner.run(new CountdownAnimation(2, 3,
               this.sprites, this.runner.getGUI()));
       // Running the level and make the running true.
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Remove collidable from the game.
     *
     * @param c is the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite from the game.
     *
     * @param s is the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        // Drawing all the Sprits into the game.
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // Drawing the Level Name on the surface.
        d.setColor(Color.BLACK);
        d.drawText(530, 22, "Level Name: ", 15);
        // Adding the update score.
        d.drawText(620, 22, this.levelInfo.levelName(), 15);

        // If the player pressing 'p' to pause the screen - it running the
        // PauseScreen until keyPress make the game run again.
        if (this.runner.getGUI().getKeyboardSensor().isPressed("p")) {
            KeyPressStoppableAnimation keyPress = new KeyPressStoppableAnimation(
                    this.runner.getGUI().getKeyboardSensor(), new PauseScreen());
            this.runner.run(keyPress);
        }

        /*
         * Check if no more blocks or balls.
         * In case there is at least one ball, and no more blocks,
         * the player won the game and the score increased with 100.
         * If so, closing the gui and return from the run method.
         */
        if (this.blockCounter.getValue() == 0
                || this.ballCounter.getValue() == 0) {
            if (this.blockCounter.getValue() == 0) {
                // Case no more blocks available.
                this.score.increase(100);
            }
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Getter method.
     * Getting the available balls currently in the game.
     *
     * @return the amount of balls
     */
    public int getAvailableBalls() {
        // Getter for available balls in the game.
        return this.ballCounter.getValue();
    }
}
