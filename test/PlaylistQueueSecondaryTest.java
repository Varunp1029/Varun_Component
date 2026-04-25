import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@code PlaylistQueue} secondary methods.
 *
 * @author Varun Pillai
 *
 */
public class PlaylistQueueSecondaryTest {

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
     * Tests isEmpty on empty queue.
     */
    @Test
    public void testIsEmptyTrue() {
        PlaylistQueue q = new PlaylistQueue1L();

        assertTrue(q.isEmpty());
        assertEquals(0, q.length());
    }

    /**
     * Tests isEmpty on non-empty queue.
     */
    @Test
    public void testIsEmptyFalse() {
        PlaylistQueue q = createFromArgs("track:a");

        assertFalse(q.isEmpty());
        assertEquals("track:a", q.peekNext());
    }

    /**
     * Tests moveNextToEnd with one track.
     */
    @Test
    public void testMoveNextToEndOne() {
        PlaylistQueue q = createFromArgs("track:a");

        q.moveNextToEnd();

        assertEquals(1, q.length());
        assertEquals("track:a", q.peekNext());
    }

    /**
     * Tests moveNextToEnd with multiple tracks.
     */
    @Test
    public void testMoveNextToEndMany() {
        PlaylistQueue q = createFromArgs("track:a", "track:b", "track:c");

        q.moveNextToEnd();

        assertEquals("track:b", q.playNext());
        assertEquals("track:c", q.playNext());
        assertEquals("track:a", q.playNext());
    }

    /**
     * Tests skip with zero tracks.
     */
    @Test
    public void testSkipZero() {
        PlaylistQueue q = createFromArgs("track:a", "track:b");

        q.skip(0);

        assertEquals(2, q.length());
        assertEquals("track:a", q.peekNext());
    }

    /**
     * Tests skip with some tracks.
     */
    @Test
    public void testSkipSome() {
        PlaylistQueue q = createFromArgs("track:a", "track:b", "track:c");

        q.skip(2);

        assertEquals(1, q.length());
        assertEquals("track:c", q.peekNext());
    }

    /**
     * Tests skip with all tracks.
     */
    @Test
    public void testSkipAll() {
        PlaylistQueue q = createFromArgs("track:a", "track:b");

        q.skip(2);

        assertEquals(0, q.length());
        assertTrue(q.isEmpty());
    }

    /**
     * Tests contains when the track is present.
     */
    @Test
    public void testContainsPresent() {
        PlaylistQueue q = createFromArgs("track:a", "track:b", "track:c");

        assertTrue(q.contains("track:b"));
        assertEquals(3, q.length());
        assertEquals("track:a", q.peekNext());
    }

    /**
     * Tests contains when the track is absent.
     */
    @Test
    public void testContainsAbsent() {
        PlaylistQueue q = createFromArgs("track:a", "track:b");

        assertFalse(q.contains("track:x"));
        assertEquals(2, q.length());
        assertEquals("track:a", q.peekNext());
    }

    /**
     * Tests clearAll.
     */
    @Test
    public void testClearAll() {
        PlaylistQueue q = createFromArgs("track:a", "track:b");

        q.clearAll();

        assertEquals(0, q.length());
        assertTrue(q.isEmpty());
    }

    /**
     * Tests toString.
     */
    @Test
    public void testToStringPreservesQueue() {
        PlaylistQueue q = createFromArgs("track:a", "track:b");

        assertEquals("<track:a, track:b>", q.toString());
        assertEquals(2, q.length());
        assertEquals("track:a", q.peekNext());
    }

    /**
     * Tests equals for equal queues.
     */
    @Test
    public void testEqualsTrue() {
        PlaylistQueue q1 = createFromArgs("track:a", "track:b");
        PlaylistQueue q2 = createFromArgs("track:a", "track:b");

        assertTrue(q1.equals(q2));
        assertEquals("track:a", q1.peekNext());
        assertEquals("track:a", q2.peekNext());
    }

    /**
     * Tests equals for different queues.
     */
    @Test
    public void testEqualsFalse() {
        PlaylistQueue q1 = createFromArgs("track:a", "track:b");
        PlaylistQueue q2 = createFromArgs("track:a", "track:c");

        assertFalse(q1.equals(q2));
        assertEquals("track:a", q1.peekNext());
        assertEquals("track:a", q2.peekNext());
    }

    /**
     * Tests hashCode preserves the queue.
     */
    @Test
    public void testHashCodePreservesQueue() {
        PlaylistQueue q = createFromArgs("track:a", "track:b");

        q.hashCode();

        assertEquals(2, q.length());
        assertEquals("track:a", q.peekNext());
    }
}
