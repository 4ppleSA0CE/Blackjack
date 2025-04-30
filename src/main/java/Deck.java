import java.util.*;

/**
 * Represents a deck of playing cards.
 * Handles card dealing and deck management.
 */
public class Deck {
    private final List<Card> cards;
    private static final int NUMBER_OF_DECKS = 1;

    /**
     * Creates a new deck and initializes it with cards.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
    }

    /**
     * Initializes the deck with cards from all suits and ranks.
     * Creates NUMBER_OF_DECKS copies of each card.
     */
    private void initializeDeck() {
        cards.clear();
        for (int i = 0; i < NUMBER_OF_DECKS; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
        shuffle();
    }

    /**
     * Shuffles the deck using Java's built-in shuffle algorithm.
     */
    public void shuffle() {
        for (int i = 0; i<10; i++){
            Collections.shuffle(cards);
        }
    }

    /**
     * Draws a card from the top of the deck.
     * If the deck is empty, it reinitializes the deck.
     * @return The drawn card
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            initializeDeck();
        }
        return cards.remove(0);
    }

    /**
     * Resets the deck by reinitializing it.
     */
    public void reset() {
        initializeDeck();
    }
} 