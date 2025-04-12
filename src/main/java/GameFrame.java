import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final Game game;
    private final JPanel mainPanel;
    private final HandPanel dealerPanel;
    private final JPanel playersPanel;
    private final JPanel controlPanel;
    private final JLabel statusLabel;
    private final JLabel winLossLabel;

    public GameFrame(String playerName) {
        game = new Game();
        game.addPlayer(playerName);
        game.startNewRound();

        setTitle("Blackjack - " + playerName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0, 100, 0));

        // Dealer panel
        dealerPanel = new HandPanel(game.getDealer());
        mainPanel.add(dealerPanel);

        // Players panel
        playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
        playersPanel.setBackground(new Color(0, 100, 0));
        updatePlayersPanel();
        mainPanel.add(playersPanel);

        // Control panel
        controlPanel = new JPanel();
        controlPanel.setBackground(new Color(0, 100, 0));
        
        JButton hitButton = new JButton("Hit");
        hitButton.addActionListener(e -> {
            game.hit();
            updateGame();
        });

        JButton stayButton = new JButton("Stay");
        stayButton.addActionListener(e -> {
            game.stay();
            updateGame();
        });

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            game.startNewRound();
            updateGame();
        });

        controlPanel.add(hitButton);
        controlPanel.add(stayButton);
        controlPanel.add(newGameButton);

        // Status label
        statusLabel = new JLabel("Welcome to Blackjack!");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        controlPanel.add(statusLabel);

        // Win/Loss label
        winLossLabel = new JLabel("Wins: 0 | Losses: 0");
        controlPanel.add(winLossLabel);

        add(mainPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updatePlayersPanel() {
        playersPanel.removeAll();
        for (Player player : game.getPlayers()) {
            playersPanel.add(new HandPanel(player));
        }
        playersPanel.revalidate();
        playersPanel.repaint();
    }

    private void updateGame() {
        dealerPanel.updateHand();
        updatePlayersPanel();
        
        if (game.getCurrentPlayer() != null) {
            switch (game.getGameState()) {
                case BETTING:
                    statusLabel.setText("Place your bet!");
                    break;
                case PLAYING:
                    statusLabel.setText(game.getCurrentPlayer().getName() + "'s turn");
                    break;
                case DEALER_TURN:
                    statusLabel.setText("Dealer's turn");
                    break;
                case ROUND_OVER:
                    statusLabel.setText("Round over! Click 'New Game' to play again");
                    winLossLabel.setText("Wins: " + game.getWins() + " | Losses: " + game.getLosses());
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartScreen());
    }
} 