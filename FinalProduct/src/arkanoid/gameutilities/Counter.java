/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.gameutilities;

/**
 * Counter class.
 */
public class Counter {
    private int counter; // Initialize counter.

    /**
     * Constructor for counter.
     *
     * @param number the number to start the count with.
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * Increase method.
     * This method add number to the current count.
     * @param number the number
     */
    public void increase(int number) {
        this.counter = getValue() + number;

    }

    /**
     * Decrease method.
     * This method subtract number from the current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.counter = getValue() - number;
    }

    /**
     * Gets value method.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}
