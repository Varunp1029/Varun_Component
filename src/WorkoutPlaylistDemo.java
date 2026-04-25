/**
 * Example use case showing {@code PlaylistQueue} for a workout playlist.
 *
 * @author Varun Pillai
 *
 */
public final class WorkoutPlaylistDemo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WorkoutPlaylistDemo() {
    }

    /**
     * Demonstrates a workout playlist.
     *
     * @param args
     *            command-line arguments, unused
     */
    public static void main(String[] args) {
        PlaylistQueue workoutQueue = new PlaylistQueue1L();

        workoutQueue.addTrack("track:Warmup");
        workoutQueue.addTrack("track:Intervals");
        workoutQueue.addTrack("track:Cooldown");

        System.out.println("Workout queue: " + workoutQueue);

        System.out.println("Skipping warmup...");
        workoutQueue.skip(1);

        System.out.println("Next workout track: " + workoutQueue.peekNext());
        System.out.println("Queue now: " + workoutQueue);

        workoutQueue.clearAll();
        System.out
                .println("Workout finished. Empty? " + workoutQueue.isEmpty());
    }
}
