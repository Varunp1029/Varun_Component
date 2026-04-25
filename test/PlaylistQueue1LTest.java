import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Tests for {@code PlaylistQueue1L} kernel and Standard methods.
 *
 * @author Varun Pillai
 *
 */
public class PlaylistQueue1LTest {

    /**
     * Creates a playlist from the given tracks.
     *
     * @param tracks
     *            tracks to add
     * @return playlist containing the tracks
     */
    private static PlaylistQueue createFromArgs(String... tracks) {
        PlaylistQueue result = new PlaylistQueue1L();
        for (String track : tracks) {
            result.addTrack(track);
        }
        return result;
    }

    /**
     * Tests constructor.
     */
    @Test
    public void testConstructorEmpty() {
        PlaylistQueue q = new PlaylistQueue1L();

        assertEquals(0, q.length());
        assertEquals("<>", q.toString());
    }

    /**
     * Tests addTrack on an empty queue.
     */
    @Test
    public void testAddTrackEmpty() {
        PlaylistQueue q = new PlaylistQueue1L();

        q.addTrack("track:a");

        assertEquals(1, q.length());
        assertEquals("track:a", q.peekNext());
    }

    /**
     * Tests addTrack on a non-empty queue.
     */
    @Test
    public void testAddTrackNonEmpty() {
        PlaylistQueue q = createFromArgs("track:a", "track:b");

        q.addTrack("track:c");

        assertEquals(3, q.length());
        assertEquals("track:a", q.playNext());
        assertEquals("track:b", q.playNext());
        assertEquals("track:c", q.playNext());
    }

    /**
     * Tests playNext with one track.
     */
    @Test
    public void testPlayNextOne() {
        PlaylistQueue q = createFromArgs("track:a");

        String result = q.playNext();

        assertEquals("track:a", result);
        assertEquals(0, q.length());
    }

    /**
     * Tests playNext with multiple tracks.
     */
    @Test
    public void testPlayNextMany() {
        PlaylistQueue q = createFromArgs("track:a", "track:b", "track:c");

        String result = q.playNext();

        assertEquals("track:a", result);
        assertEquals(2, q.length());
        assertEquals("track:b", q.peekNext());
    }

    /**
     * Tests peekNext does not remove the track.
     */
    @Test
    public void testPeekNextPreservesQueue() {
        PlaylistQueue q = createFromArgs("track:a", "track:b");

        String result = q.peekNext();

        assertEquals("track:a", result);
        assertEquals(2, q.length());
        assertEquals("track:a", q.playNext());
        assertEquals("track:b", q.playNext());
    }

    /**
     * Tests length on empty queue.
     */
    @Test
    public void testLengthEmpty() {
        PlaylistQueue q = new PlaylistQueue1L();

        assertEquals(0, q.length());
    }

    /**
     * Tests length on non-empty queue.
     */
    @Test
    public void testLengthNonEmpty() {
        PlaylistQueue q = createFromArgs("track:a", "track:b", "track:c");

        assertEquals(3, q.length());
        assertEquals("track:a", q.peekNext());
    }

    /**
     * Tests newInstance.
     */
    @Test
    public void testNewInstance() {
        PlaylistQueue q = createFromArgs("track:a", "track:b");

        PlaylistQueue newQ = q.newInstance();

        assertEquals(0, newQ.length());
        assertEquals(2, q.length());
        assertFalse(q == newQ);
    }

    /**
     * Tests clear.
     */
    @Test
    public void testClear() {
        PlaylistQueue q = createFromArgs("track:a", "track:b");

        q.clear();

        assertEquals(0, q.length());
    }

    /**
     * Tests transferFrom.
     */
    @Test
    public void testTransferFrom() {
        PlaylistQueue q1 = createFromArgs("track:a", "track:b");
        PlaylistQueue q2 = createFromArgs("track:x");

        q2.transferFrom(q1);

        assertEquals(0, q1.length());
        assertEquals(2, q2.length());
        assertEquals("track:a", q2.playNext());
        assertEquals("track:b", q2.playNext());
    }
}
