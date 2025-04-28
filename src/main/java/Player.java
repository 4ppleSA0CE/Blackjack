/**
 * Represents a player in the game.
 * Handles player's hand and game state.
 */
public class Player {
    private final String name;
    private final Hand hand;
    private final boolean isDealer;

    /**
     * Creates a new player with the specified name and dealer status.
     * @param name The player's name
     * @param isDealer true if this player is the dealer, false otherwise
     */
    public Player(String name, boolean isDealer) {
        this.name = name;
        this.hand = new Hand(isDealer);
        this.isDealer = isDealer;
    }

    /**
     * Gets the player's name.
     * @return The player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand.
     * @return The player's hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Clears the player's hand for a new round.
     */
    public void clearHand() {
        hand.clear();
    }

    /**
     * Checks if this player is the dealer.
     * @return true if this player is the dealer, false otherwise
     */
    public boolean isDealer() {
        return isDealer;
    }
} 