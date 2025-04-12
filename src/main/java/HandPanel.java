import javax.swing.*;
import java.awt.*;

public class HandPanel extends JPanel {
    private final Player player;
    private final JLabel nameLabel;
    private final JLabel valueLabel;
    private final JPanel cardsPanel;

    public HandPanel(Player player) {
        this.player = player;
        setLayout(new BorderLayout());
        setBackground(new Color(0, 100, 0));

        // Create top panel for player info
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setBackground(new Color(0, 100, 0));
        infoPanel.setForeground(Color.WHITE);

        nameLabel = new JLabel(player.getName());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        valueLabel = new JLabel();
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 16));

        infoPanel.add(nameLabel);
        infoPanel.add(valueLabel);

        // Create panel for cards
        cardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        cardsPanel.setBackground(new Color(0, 100, 0));

        add(infoPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);

        updateHand();
    }

    public void updateHand() {
        cardsPanel.removeAll();
        Hand hand = player.getHand();

        for (int i = 0; i < hand.getCards().size(); i++) {
            cardsPanel.add(new CardPanel(hand.getCards().get(i)));
        }

        if (hand.getCards().size() > 0) {
            valueLabel.setText("Value: " + hand.calculateValue());
            if (hand.isBust()) {
                valueLabel.setText(valueLabel.getText() + " (BUST)");
            } else if (hand.isBlackjack()) {
                valueLabel.setText(valueLabel.getText() + " (BLACKJACK)");
            }
        } else {
            valueLabel.setText("");
        }

        revalidate();
        repaint();
    }
} 