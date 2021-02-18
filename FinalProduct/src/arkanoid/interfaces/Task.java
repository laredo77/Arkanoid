/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
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