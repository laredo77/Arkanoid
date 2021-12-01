/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 */
package arkanoid.interfaces;

/**
 * Task interface.
 * @param <T>
 */
public interface Task<T> {
    /**
     * Runs the task.
     * @return null.
     */
    T run();
}
