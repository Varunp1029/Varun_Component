/**
 * Enhanced interface for a PlaylistQueue component.
 *
 * @author Varun Pillai
 *
 */
public interface PlaylistQueue extends PlaylistQueueKernel {

    /**
     * Reports whether {@code this} contains no tracks.
     *
     * @return true iff {@code this} is empty
     * @ensures <pre>
     * isEmpty = (this = <>) and
     * this = #this
     * </pre>
     */
    boolean isEmpty();

    /**
     * Moves the next track in {@code this} to the end.
     *
     * @updates this
     * @requires this /= <>
     * @ensures <pre>
     * if |#this| = 1 then
     *     this = #this
     * else
     *     there exists x, y such that
     *         #this = <x> * y and
     *         this = y * <x>
     * end
     * </pre>
     */
    void moveNextToEnd();

    /**
     * Removes the next {@code k} tracks from {@code this}.
     *
     * @param k
     *            number of tracks to skip
     * @updates this
     * @requires 0 <= k and k <= |this|
     * @ensures there exists s such that #this = s * this and |s| = k
     */
    void skip(int k);

    /**
     * Reports whether {@code trackId} appears in {@code this}.
     *
     * @param trackId
     *            the track to search for
     * @return true iff {@code trackId} is in {@code this}
     * @requires trackId is not null
     * @ensures this = #this
     */
    boolean contains(String trackId);

    /**
     * Removes all tracks from {@code this}.
     *
     * @clears this
     * @ensures this = <>
     */
    void clearAll();
}
