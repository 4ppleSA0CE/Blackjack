import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 140;
    private final Card card;

    public CardPanel(Card card) {
        this.card = card;
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw card background
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0, 0, CARD_WIDTH, CARD_HEIGHT, 10, 10);
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(0, 0, CARD_WIDTH, CARD_HEIGHT, 10, 10);

        if (!card.isFaceUp()) {
            // Draw card back
            g2d.setColor(new Color(0, 100, 0));
            g2d.fillRoundRect(5, 5, CARD_WIDTH - 10, CARD_HEIGHT - 10, 5, 5);
            return;
        }

        // Set color based on suit
        if (card.getSuit() == Card.Suit.HEARTS || card.getSuit() == Card.Suit.DIAMONDS) {
            g2d.setColor(Color.RED);
        } else {
            g2d.setColor(Color.BLACK);
        }

        // Draw rank and suit
        String rank = getRankSymbol(card.getRank());
        String suit = getSuitSymbol(card.getSuit());

        Font font = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(font);

        // Draw top-left rank and suit
        g2d.drawString(rank, 10, 25);
        g2d.drawString(suit, 10, 45);

        // Draw bottom-right rank and suit (rotated)
        g2d.rotate(Math.PI, CARD_WIDTH / 2, CARD_HEIGHT / 2);
        g2d.drawString(rank, -CARD_WIDTH + 10, -CARD_HEIGHT + 25);
        g2d.drawString(suit, -CARD_WIDTH + 10, -CARD_HEIGHT + 45);
    }

    private String getRankSymbol(Card.Rank rank) {
        switch (rank) {
            case ACE: return "A";
            case JACK: return "J";
            case QUEEN: return "Q";
            case KING: return "K";
            default: return String.valueOf(rank.getValue());
        }
    }

    private String getSuitSymbol(Card.Suit suit) {
        switch (suit) {
            case HEARTS: return "♥";
            case DIAMONDS: return "♦";
            case CLUBS: return "♣";
            case SPADES: return "♠";
            default: return "";
        }
    }
} 