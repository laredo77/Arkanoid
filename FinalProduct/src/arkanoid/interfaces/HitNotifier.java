/**
 * This code was written by Itamar Laredo.
 * ID: 311547087
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.interfaces;

/**
 * The interface Hit Notifier.
 * This interface realized by objects that will be the notifier.
 */
public interface HitNotifier {

    /**
     * Add hit listener method.
     * Add hl as a listener to hit events.
     *
     * @param hl is the listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl is the listener
     */
    void removeHitListener(HitListener hl);
}