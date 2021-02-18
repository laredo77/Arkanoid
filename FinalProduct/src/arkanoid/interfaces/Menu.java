/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.interfaces;

/**
 * Menu interface.
 * @param <T> Generic Task.
 */
public interface Menu<T> extends Animation {
    /**
     * Adding new menu selection as option to the user.
     * @param key is the key that pressed to run the task
     * @param message the description of the task
     * @param returnVal the task.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Returning the current task that run.
     * @return task.
     */
    T getStatus();
}
