/**
 * Represents a player in the game.
 * Handles player's hand, chips, and betting.
 */
public class Player {
    private final String name;
    private final Hand hand;
    private int chips;
    private int currentBet;
    private final boolean isDealer;

    /**
     * Creates a new player with the specified name and dealer status.
     * @param name The player's name
     * @param isDealer true if this player is the dealer, false otherwise
     */
    public Player(String name, boolean isDealer) {
        this.name = name;
        this.hand = new Hand(isDealer);
        this.chips = 1000; // Starting chips
        this.currentBet = 0;
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
     * Gets the player's current chip count.
     * @return The number of chips the player has
     */
    public int getChips() {
        return chips;
    }

    /**
     * Gets the player's current bet.
     * @return The current bet amount
     */
    public int getCurrentBet() {
        return currentBet;
    }

    /**
     * Places a bet from the player's chips.
     * @param amount The amount to bet
     * @return true if the bet was placed successfully, false otherwise
     */
    public boolean placeBet(int amount) {
        if (amount <= chips) {
            currentBet = amount;
            chips -= amount;
            return true;
        }
        return false;
    }

    /**
     * Handles a win (1:1 payout).
     * Adds the bet amount back to the player's chips.
     */
    public void winBet() {
        chips += currentBet * 2;
        currentBet = 0;
    }

    /**
     * Handles a blackjack win (3:2 payout).
     * Adds 1.5 times the bet amount to the player's chips.
     */
    public void blackjack() {
        chips += (int)(currentBet * 2.5);
        currentBet = 0;
    }

    /**
     * Handles a push (tie).
     * Returns the bet to the player's chips.
     */
    public void push() {
        chips += currentBet;
        currentBet = 0;
    }

    /**
     * Handles a loss.
     * The bet is already deducted from chips when placed.
     */
    public void loseBet() {
        currentBet = 0;
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