import components.standard.Standard;

/**
 * Kernel interface for a PlaylistQueue component.
 *
 * @mathsubtypes <pre>
 * PLAYLIST_QUEUE_MODEL is finite string of String
 * </pre>
 *
 * @initially <pre>
 * this = <>
 * </pre>
 *
 * @author Varun Pillai
 *
 */
public interface PlaylistQueueKernel extends Standard<PlaylistQueue> {

    /**
     * Adds {@code trackId} to the end of {@code this}.
     *
     * @param trackId
     *            the track to add
     * @updates this
     * @requires trackId is not null
     * @ensures this = #this * <trackId>
     */
    void addTrack(String trackId);

    /**
     * Removes and returns the next track in {@code this}.
     *
     * @return the next track in {@code this}
     * @updates this
     * @requires this /= <>
     * @ensures <pre>
     * #this = <playNext> * this
     * </pre>
     */
    String playNext();

    /**
     * Returns the next track in {@code this} without removing it.
     *
     * @return the next track in {@code this}
     * @requires this /= <>
     * @ensures this = #this
     */
    String peekNext();

    /**
     * Reports the number of tracks in {@code this}.
     *
     * @return the length of {@code this}
     * @ensures this = #this
     */
    int length();
}
