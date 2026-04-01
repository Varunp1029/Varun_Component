/**
 * Secondary methods for {@code PlaylistQueue}.
 *
 * @author Varun Pillai
 *
 */
public abstract class PlaylistQueueSecondary implements PlaylistQueue {

    /**
     * Constructs a string representation of {@code this}.
     *
     * @return string representation of {@code this}
     * @ensures <pre>
     * toString = [string representation of this] and
     * this = #this
     * </pre>
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int n = this.length();

        result.append("<");
        for (int i = 0; i < n; i++) {
            String next = this.playNext();
            result.append(next);
            if (i < n - 1) {
                result.append(", ");
            }
            this.addTrack(next);
        }
        result.append(">");

        return result.toString();
    }

    /**
     * Reports whether {@code this} is equal to {@code obj}.
     *
     * @param obj
     *            the object to compare with {@code this}
     * @return true iff {@code this} equals {@code obj}
     * @ensures this = #this
     */
    @Override
    public boolean equals(Object obj) {
        boolean isEqual = true;

        if (!(obj instanceof PlaylistQueue)) {
            isEqual = false;
        } else {
            PlaylistQueue other = (PlaylistQueue) obj;

            if (this == other) {
                isEqual = true;
            } else if (this.length() != other.length()) {
                isEqual = false;
            } else {
                int n = this.length();

                for (int i = 0; i < n; i++) {
                    String thisNext = this.playNext();
                    String otherNext = other.playNext();

                    if (!thisNext.equals(otherNext)) {
                        isEqual = false;
                    }

                    this.addTrack(thisNext);
                    other.addTrack(otherNext);
                }
            }
        }

        return isEqual;
    }

    /**
     * Reports whether {@code this} contains no tracks.
     *
     * @return true iff {@code this} is empty
     * @ensures <pre>
     * isEmpty = (this = <>) and
     * this = #this
     * </pre>
     */
    @Override
    public boolean isEmpty() {
        return this.length() == 0;
    }

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
    @Override
    public void moveNextToEnd() {
        assert this.length() > 0 : "Violation of: this /= <>";

        String next = this.playNext();
        this.addTrack(next);
    }

    /**
     * Removes the next {@code k} tracks from {@code this}.
     *
     * @param k
     *            number of tracks to skip
     * @updates this
     * @requires 0 <= k and k <= |this|
     * @ensures there exists s such that #this = s * this and |s| = k
     */
    @Override
    public void skip(int k) {
        assert k >= 0 : "Violation of: 0 <= k";
        assert k <= this.length() : "Violation of: k <= |this|";

        for (int i = 0; i < k; i++) {
            this.playNext();
        }
    }

    /**
     * Reports whether {@code trackId} appears in {@code this}.
     *
     * @param trackId
     *            the track to search for
     * @return true iff {@code trackId} is in {@code this}
     * @requires trackId is not null
     * @ensures this = #this
     */
    @Override
    public boolean contains(String trackId) {
        assert trackId != null : "Violation of: trackId is not null";

        boolean found = false;
        int n = this.length();

        for (int i = 0; i < n; i++) {
            String next = this.playNext();

            if (next.equals(trackId)) {
                found = true;
            }

            this.addTrack(next);
        }

        return found;
    }

    /**
     * Removes all tracks from {@code this}.
     *
     * @clears this
     * @ensures this = <>
     */
    @Override
    public void clearAll() {
        while (this.length() > 0) {
            this.playNext();
        }
    }

    /**
     * Returns a hash code for {@code this}.
     *
     * @return hash code for {@code this}
     * @ensures this = #this
     */
    @Override
    public int hashCode() {
        int result = 1;
        int n = this.length();

        for (int i = 0; i < n; i++) {
            String next = this.playNext();
            result = 31 * result + next.hashCode();
            this.addTrack(next);
        }

        return result;
    }
}
