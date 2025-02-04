package CardGame;

import java.util.List;
import java.util.ArrayList;

/**
 * The CardGame.ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board{

    private static final int BOARD_SIZE = 9;

    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};

    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

    private Card[] cards;
    private Deck deck;
    private static final boolean I_AM_DEBUGGING = false;

    public ElevensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
        cards = new Card[BOARD_SIZE];
        deck = new Deck(RANKS, SUITS, POINT_VALUES);
        if (I_AM_DEBUGGING) {
            System.out.println(deck);
            System.out.println("----------");
        }
        dealMyCards();
    }

    public void newGame() {
        deck.shuffle();
        dealMyCards();
    }

    public int size() {
        return cards.length;
    }

    public boolean isEmpty() {
        for (Card c : cards) {
            if (c != null) {
                return false;
            }
        }
        return true;
    }

    public void deal(int k) {
        cards[k] = deck.deal();
    }

    public int deckSize() {
        return deck.size();
    }

    public Card cardAt(int k) {
        return cards[k];
    }

    public void replaceSelectedCards(List<Integer> selectedCards) {
        for (Integer k : selectedCards) {
            deal(k);
        }
    }

    public List<Integer> cardIndexes() {
        List<Integer> selected = new ArrayList<>();
        for (int k = 0; k < cards.length; k++) {
            if (cards[k] != null) {
                selected.add(k);
            }
        }
        return selected;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int k = 0; k < cards.length; k++) {
            s.append(k).append(": ").append(cards[k]).append("\n");
        }
        return s.toString();
    }

    public boolean gameIsWon() {
        if (deck.isEmpty()) {
            for (Card c : cards) {
                if (c != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Determines if the selected CardGame.cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face CardGame.cards whose values add to 11,
     * and (2) a group of three CardGame.cards consisting of a jack, a queen, and a king.
     */
    public boolean isLegal(List<Integer> selectedCards) {
        if (selectedCards.size() == 2) {
            return containsPairSum11(selectedCards);
        } else if (selectedCards.size() == 3) {
            return containsJQK(selectedCards);
        }
        return false;
    }

    /**
     * Determines if there are any legal plays left on the board.
     */
    public boolean anotherPlayIsPossible() {
        List<Integer> indexes = cardIndexes();
        return containsPairSum11(indexes) || containsJQK(indexes);
    }

    /**
     * Check for an 11-pair in the selected CardGame.cards.
     */
    private boolean containsPairSum11(List<Integer> selectedCards) {
        for (int i = 0; i < selectedCards.size(); i++) {
            for (int j = i + 1; j < selectedCards.size(); j++) {
                Card card1 = cards[selectedCards.get(i)];
                Card card2 = cards[selectedCards.get(j)];
                if (card1.pointValue() + card2.pointValue() == 11) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check for a JQK in the selected CardGame.cards.
     */
    private boolean containsJQK(List<Integer> selectedCards) {
        boolean hasJack = false, hasQueen = false, hasKing = false;
        for (int idx : selectedCards) {
            String rank = cards[idx].rank();
            if (rank.equals("jack")) {
                hasJack = true;
            } else if (rank.equals("queen")) {
                hasQueen = true;
            } else if (rank.equals("king")) {
                hasKing = true;
            }
        }
        return hasJack && hasQueen && hasKing;
    }

    private void dealMyCards() {
        for (int k = 0; k < cards.length; k++) {
            cards[k] = deck.deal();
        }
    }
}
