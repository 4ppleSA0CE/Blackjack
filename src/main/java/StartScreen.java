import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {
    private final JTextField playerNameField;
    private final JButton startButton;

    public StartScreen() {
        setTitle("Blackjack - Start Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0, 100, 0));

        // Title
        JLabel titleLabel = new JLabel("Welcome to Blackjack!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Player name input
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(0, 100, 0));
        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setForeground(Color.WHITE);
        playerNameField = new JTextField(15);
        namePanel.add(nameLabel);
        namePanel.add(playerNameField);

        // Start button
        startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText().trim();
                if (!playerName.isEmpty()) {
                    dispose(); // Close start screen
                    new GameFrame(playerName); // Start game with player name
                } else {
                    JOptionPane.showMessageDialog(StartScreen.this,
                            "Please enter your name",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to main panel
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(namePanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(startButton);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartScreen());
    }
} 