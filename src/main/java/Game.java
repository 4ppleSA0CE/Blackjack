import java.util.*;

/**
 * Represents the main game logic and state.
 * Handles game flow, rules, and player turns.
 */
public class Game {
    private final Deck deck;
    private final List<Player> players;
    private Player dealer;
    private Player currentPlayer;
    private GameState gameState;

    /**
     * Represents the possible states of the game.
     */
    public enum GameState {
        BETTING,    // Players are placing bets
        PLAYING,    // Players are taking their turns
        DEALER_TURN, // Dealer is playing
        ROUND_OVER  // Round is complete
    }

    /**
     * Creates a new game with a deck and dealer.
     */
    public Game() {
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.dealer = new Player("Dealer", true);
        this.gameState = GameState.BETTING;
    }

    /**
     * Adds a new player to the game.
     * @param name The name of the player to add
     */
    public void addPlayer(String name) {
        players.add(new Player(name, false));
    }

    /**
     * Starts a new round of the game.
     * Resets the deck, clears hands, and deals initial cards.
     */
    public void startNewRound() {
        deck.reset();
        clearAllHands();
        dealInitialCards();
        gameState = GameState.PLAYING;
        currentPlayer = players.get(0);
    }

    /**
     * Clears all players' hands for a new round.
     */
    private void clearAllHands() {
        dealer.clearHand();
        for (Player player : players) {
            player.clearHand();
        }
    }

    /**
     * Deals the initial two cards to each player and the dealer.
     * The dealer's second card is dealt face down.
     */
    private void dealInitialCards() {
        // Deal two cards to each player
        for (Player player : players) {
            player.getHand().addCard(deck.drawCard());
            player.getHand().addCard(deck.drawCard());
        }

        // Deal two cards to dealer (second card face down)
        dealer.getHand().addCard(deck.drawCard());
        Card dealerSecondCard = deck.drawCard();
        dealerSecondCard.setFaceUp(false);
        dealer.getHand().addCard(dealerSecondCard);
    }

    /**
     * Player hits (takes another card).
     * If the player busts, the round ends.
     */
    public void hit() {
        if (gameState != GameState.PLAYING) return;

        currentPlayer.getHand().addCard(deck.drawCard());
        
        if (currentPlayer.getHand().isBust()) {
            // If player busts, end the round immediately
            gameState = GameState.ROUND_OVER;
            return;
        }
    }

    /**
     * Player stays (ends their turn).
     * Moves to the next player or dealer's turn.
     */
    public void stay() {
        if (gameState != GameState.PLAYING) return;
        nextPlayer();
    }

    /**
     * Moves to the next player or dealer's turn.
     */
    private void nextPlayer() {
        int currentIndex = players.indexOf(currentPlayer);
        if (currentIndex < players.size() - 1) {
            currentPlayer = players.get(currentIndex + 1);
        } else {
            dealerTurn();
        }
    }

    /**
     * Handles the dealer's turn.
     * Dealer hits until their total is 17 or higher.
     */
    private void dealerTurn() {
        gameState = GameState.DEALER_TURN;
        dealer.getHand().revealAllCards();

        while (dealer.getHand().calculateValue() < 17) {
            dealer.getHand().addCard(deck.drawCard());
        }

        evaluateRound();
    }

    /**
     * Evaluates the round and determines winners.
     * Compares each player's hand to the dealer's hand.
     */
    private void evaluateRound() {
        int dealerValue = dealer.getHand().calculateValue();
        boolean dealerBust = dealer.getHand().isBust();

        for (Player player : players) {
            int playerValue = player.getHand().calculateValue();
            boolean playerBust = player.getHand().isBust();
            
            if (playerBust) {
                // Player busts, they lose their bet
                player.loseBet();
            } else if (dealerBust) {
                // Dealer busts, player wins
                player.winBet();
            } else if (player.getHand().isBlackjack() && !dealer.getHand().isBlackjack()) {
                // Player has blackjack and dealer doesn't
                player.blackjack();
            } else if (playerValue > dealerValue) {
                // Player's hand is higher than dealer's
                player.winBet();
            } else if (playerValue == dealerValue) {
                // Push - return the bet
                player.push();
            } else {
                // Player's hand is lower than dealer's
                player.loseBet();
            }
        }

        gameState = GameState.ROUND_OVER;
    }

    /**
     * Gets the current game state.
     * @return The current game state
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Gets the current player.
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the dealer.
     * @return The dealer
     */
    public Player getDealer() {
        return dealer;
    }

    /**
     * Gets all players in the game.
     * @return A list of all players
     */
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
} 