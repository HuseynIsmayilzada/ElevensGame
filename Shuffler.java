import java.util.Random;

public class Shuffler {

    /**
     * The number of consecutive shuffle steps to be performed in each call
     * to each sorting procedure.
     */
    private static final int SHUFFLE_COUNT = 1;

    /**
     * Tests shuffling methods.
     * @param args is not used.
     */
    public static void main(String[] args) {
        System.out.println("Results of " + SHUFFLE_COUNT +
                " consecutive perfect shuffles:");
        int[] values1 = {0, 1, 2, 3};
        for (int j = 1; j <= SHUFFLE_COUNT; j++) {
            perfectShuffle(values1);
            System.out.print("  " + j + ":");
            for (int k = 0; k < values1.length; k++) {
                System.out.print(" " + values1[k]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Results of " + SHUFFLE_COUNT +
                " consecutive efficient selection shuffles:");
        int[] values2 = {0, 1, 2, 3};
        for (int j = 1; j <= SHUFFLE_COUNT; j++) {
            selectionShuffle(values2);
            System.out.print("  " + j + ":");
            for (int k = 0; k < values2.length; k++) {
                System.out.print(" " + values2[k]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Apply a "perfect shuffle" to the argument.
     * The perfect shuffle algorithm splits the deck in half, then interleaves
     * the CardGame.cards in one half with the CardGame.cards in the other.
     * @param values is an array of integers simulating CardGame.cards to be shuffled.
     */
    public static void perfectShuffle(int[] values) {
        int[] shuffled = new int[values.length];
        int mid = (values.length + 1) / 2; // Split deck in half (rounding up if odd)

        int k = 0;
        for (int j = 0; j < mid; j++) { // Place first half in even positions
            shuffled[k] = values[j];
            k += 2;
        }

        k = 1;
        for (int j = mid; j < values.length; j++) { // Place second half in odd positions
            shuffled[k] = values[j];
            k += 2;
        }

        // Copy shuffled array back into original array
        System.arraycopy(shuffled, 0, values, 0, values.length);
    }

    /**
     * Apply an "efficient selection shuffle" to the argument.
     * The selection shuffle algorithm randomly swaps elements in the array.
     * @param values is an array of integers simulating CardGame.cards to be shuffled.
     */
    public static void selectionShuffle(int[] values) {
        Random rand = new Random();
        for (int k = values.length - 1; k > 0; k--) {
            int r = rand.nextInt(k + 1); // Random index between 0 and k
            // Swap values[k] and values[r]
            int temp = values[k];
            values[k] = values[r];
            values[r] = temp;
        }
    }
}
