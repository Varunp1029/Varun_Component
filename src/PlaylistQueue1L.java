import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code PlaylistQueue} represented as a {@code Sequence<String>}.
 *
 * @convention <pre>
 * rep /= null
 * </pre>
 *
 * @correspondence <pre>
 * this = rep
 * </pre>
 *
 * @author Varun Pillai
 *
 */
public final class PlaylistQueue1L extends PlaylistQueueSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of this {@code PlaylistQueue}.
     */
    private Sequence<String> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Sequence1L<String>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public PlaylistQueue1L() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final PlaylistQueue newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(PlaylistQueue source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof PlaylistQueue1L : ""
                + "Violation of: source is of dynamic type PlaylistQueue1L";

        PlaylistQueue1L localSource = (PlaylistQueue1L) source;
        this.rep.transferFrom(localSource.rep);
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public void addTrack(String trackId) {
        assert trackId != null : "Violation of: trackId is not null";

        this.rep.add(this.rep.length(), trackId);
    }

    @Override
    public String playNext() {
        assert this.rep.length() > 0 : "Violation of: this /= <>";

        return this.rep.remove(0);
    }

    @Override
    public String peekNext() {
        assert this.rep.length() > 0 : "Violation of: this /= <>";

        return this.rep.entry(0);
    }

    @Override
    public int length() {
        return this.rep.length();
    }
}
