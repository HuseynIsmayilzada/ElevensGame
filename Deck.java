package CardGame;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * The CardGame.Deck class represents a shuffled deck of CardGame.cards.
 * It provides several operations including
 * initialize, shuffle, deal, and check if empty.
 */
public class Deck {

    /**
     * CardGame.cards contains all the CardGame.cards in the deck.
     */
    private List<Card> cards;

    /**
     * size is the number of not-yet-dealt CardGame.cards.
     * Cards are dealt from the top (highest index) down.
     * The next card to be dealt is at size - 1.
     */
    private int size;

    /**
     * Creates a new CardGame.Deck instance.
     * It pairs each element of ranks with each element of suits,
     * and produces one of the corresponding card.
     * @param ranks is an array containing all of the card ranks.
     * @param suits is an array containing all of the card suits.
     * @param values is an array containing all of the card point values.
     */
    public Deck(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<>();

        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                cards.add(new Card(ranks[i], suit, values[i]));
            }
        }

        size = cards.size();
        shuffle(); // Shuffle the deck after initialization
    }

    /**
     * Shuffles the deck using the efficient selection shuffle algorithm.
     * Ensures all permutations have an equal probability of occurring.
     */
    public void shuffle() {
        Random rand = new Random();
        for (int k = size - 1; k > 0; k--) {
            int r = rand.nextInt(k + 1); // Random index between 0 and k
            // Swap CardGame.cards[k] and CardGame.cards[r]
            Card temp = cards.get(k);
            cards.set(k, cards.get(r));
            cards.set(r, temp);
        }
        size = cards.size(); // Reset size after shuffle to indicate all CardGame.cards are available
    }

    /**
     * Determines if this deck is empty (no undealt CardGame.cards).
     * @return true if this deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Accesses the number of undealt CardGame.cards in this deck.
     * @return the number of undealt CardGame.cards in this deck.
     */
    public int size() {
        return size;
    }

    /**
     * Deals a card from this deck.
     * @return the card just dealt, or null if all the CardGame.cards have been dealt.
     */
    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        size--; // Reduce the number of undealt CardGame.cards
        return cards.get(size);
    }

    /**
     * Generates and returns a string representation of this deck.
     * @return a string representation of this deck.
     */
    @Override
    public String toString() {
        String rtn = "size = " + size + "\nUndealt CardGame.cards: \n";

        for (int k = size - 1; k >= 0; k--) {
            rtn += cards.get(k);
            if (k != 0) {
                rtn += ", ";
            }
            if ((size - k) % 2 == 0) {
                rtn += "\n";
            }
        }

        rtn += "\nDealt CardGame.cards: \n";
        for (int k = cards.size() - 1; k >= size; k--) {
            rtn += cards.get(k);
            if (k != size) {
                rtn += ", ";
            }
            if ((k - cards.size()) % 2 == 0) {
                rtn += "\n";
            }
        }

        rtn += "\n";
        return rtn;
    }
}
