import java.util.ArrayList;
import java.util.List;

/**
 * Proof-of-possibility for a PlaylistQueue component.
 *
 * This component models a real-world playlist music queue. It is practical,
 * small in scope, and feasible to implement using only Strings and a simple
 * list representation. More advanced features are currently excluded to keep
 * the design manageable.
 */
public final class PlaylistQueuePOC {

    /**
     * Representation field: the front of the queue is at index 0.
     */
    private final List<String> tracks = new ArrayList<>();

    /**
     * Adds a track to the end of the playlist queue.
     *
     * @param trackId
     *            the identifier of the track to add
     * @throws IllegalArgumentException
     *             if {@code trackId} is {@code null}
     */
    public void addTrack(String trackId) {
        if (trackId == null) {
            throw new IllegalArgumentException("trackId must not be null");
        }
        this.tracks.add(trackId);
    }

    /**
     * Removes and returns the next track in the queue.
     *
     * @return the next track, or {@code null} if the queue is empty
     */
    public String playNext() {
        if (this.tracks.isEmpty()) {
            return null;
        }
        return this.tracks.remove(0);
    }

    /**
     * Reports the next track in the queue without removing it.
     *
     * @return the next track, or {@code null} if the queue is empty
     */
    public String peekNext() {
        if (this.tracks.isEmpty()) {
            return null;
        }
        return this.tracks.get(0);
    }

    /**
     * Reports the number of tracks currently in the queue.
     *
     * @return the number of tracks in the queue
     */
    public int length() {
        return this.tracks.size();
    }

    /**
     * Reports whether the playlist queue is empty.
     *
     * @return {@code true} if the queue contains no tracks; {@code false}
     *         otherwise
     */
    public boolean isEmpty() {
        return this.length() == 0;
    }

    /**
     * Moves the next track to the end of the queue.
     * <p>
     * If the queue is empty, this method does nothing.
     * </p>
     */
    public void moveNextToEnd() {
        if (!this.isEmpty()) {
            String next = this.playNext();
            this.addTrack(next);
        }
    }

    /**
     * Returns a string representation of this playlist queue.
     *
     * @return a string representation of the queue contents
     */
    @Override
    public String toString() {
        return this.tracks.toString();
    }

    /**
     * Demonstrates usage of the PlaylistQueuePOC component.
     *
     * @param args
     *            command-line arguments (unused)
     */
    public static void main(String[] args) {
        PlaylistQueuePOC pq = new PlaylistQueuePOC();

        pq.addTrack("track:90210");
        pq.addTrack("track:Goosebumps");
        pq.addTrack("track:FE!N");

        System.out.println("Up next: " + pq);
        System.out.println("Peek: " + pq.peekNext());
        System.out.println("Length: " + pq.length());

        pq.moveNextToEnd();
        System.out.println("After moveNextToEnd: " + pq);

        System.out.println("Playing: " + pq.playNext());
        System.out.println("Now up next: " + pq.peekNext());
        System.out.println("Empty? " + pq.isEmpty());
    }
}
