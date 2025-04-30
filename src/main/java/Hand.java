import java.util.*;

/**
 * Represents a hand of cards in the game.
 * Handles card management and value calculation.
 */
public class Hand {
    private final List<Card> cards;

    /**
     * Creates an empty hand.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Adds a card to the hand.
     * @param card The card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Removes all cards from the hand.
     */
    public void clear() {
        cards.clear();
    }

    /**
     * Gets all cards in the hand.
     * @return A list of cards in the hand
     */
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    /**
     * Calculates the value of the hand.
     * Aces are counted as 11 if it doesn't cause a bust, otherwise as 1.
     * @return The total value of the hand
     */
    public int calculateValue() {
        int value = 0;
        int aces = 0;

        // First pass: count all non-ace cards
        for (Card card : cards) {
            if (card.isFaceUp()) {
                if (card.getRank() == Card.Rank.ACE) {
                    aces++;
                } else {
                    value += card.getValue();
                }
            }
        }

        // Second pass: handle aces
        for (int i = 0; i < aces; i++) {
            // If adding 11 would keep us under 21, use 11
            if (value + 11 <= 21) {
                value += 11;
            } else {
                // Otherwise use 1
                value += 1;
            }
        }

        return value;
    }

    /**
     * Checks if the hand is a bust (value over 21).
     * @return true if the hand value is over 21, false otherwise
     */
    public boolean isBust() {
        return calculateValue() > 21;
    }

    /**
     * Checks if the hand is a blackjack (Ace + 10-value card).
     * @return true if the hand is a blackjack, false otherwise
     */
    public boolean isBlackjack() {
        return cards.size() == 2 && calculateValue() == 21;
    }

    /**
     * Reveals all cards in the hand by setting them face up.
     */
    public void revealAllCards() {
        for (Card card : cards) {
            card.setFaceUp(true);
        }
    }
} 