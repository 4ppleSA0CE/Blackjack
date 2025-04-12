import java.awt.Color;
/**
 * Represents a playing card with a suit and rank.
 * Handles the visual representation of the card.
 */
public class Card {
    // Card suits
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    // Card ranks with their values
    public enum Rank {
        ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;

    /**
     * Creates a new card with the specified suit and rank.
     * @param suit The suit of the card
     * @param rank The rank of the card
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = true;
    }

    /**
     * Gets the value of the card.
     * @return The numeric value of the card
     */
    public int getValue() {
        return rank.getValue();
    }

    /**
     * Gets the suit of the card.
     * @return The suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the rank of the card.
     * @return The rank of the card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Checks if the card is face up.
     * @return true if the card is face up, false otherwise
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * Sets whether the card is face up or down.
     * @param faceUp true to show the card face up, false to show it face down
     */
    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    /**
     * Gets the color of the card based on its suit.
     * @return Color.RED for hearts and diamonds, Color.BLACK for clubs and spades
     */
    public Color getColor() {
        return (suit == Suit.HEARTS || suit == Suit.DIAMONDS) ? Color.RED : Color.BLACK;
    }

    /**
     * Gets the symbol representing the card's suit.
     * @return The Unicode symbol for the suit
     */
    public String getSuitSymbol() {
        switch (suit) {
            case HEARTS:
                return "♥";
            case DIAMONDS:
                return "♦";
            case CLUBS:
                return "♣";
            case SPADES:
                return "♠";
            default:
                return "";
        }
    }

    /**
     * Gets the string representation of the card's rank.
     * @return The string representation of the rank
     */
    public String getRankString() {
        switch (rank) {
            case ACE:
                return "A";
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
            default:
                return String.valueOf(rank.getValue());
        }
    }

    /**
     * Returns a string representation of the card.
     * @return A string in the format "Rank of Suit" (e.g., "Ace of Spades")
     */
    @Override
    public String toString() {
        if (!faceUp) {
            return "XX";
        }
        return rank + " of " + suit;
    }
} 