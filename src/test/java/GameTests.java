import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTests {

    // Test for evaluateWinnings - Player Wins with Natural Blackjack
    @Test
    void testEvaluateWinningsPlayerNaturalBlackjack() {
        BlackJackGame game = new BlackJackGame();
        game.currentBet = 100;
        game.playerHand.add(new Card(1, "Hearts")); // Ace
        game.playerHand.add(new Card(10, "Diamonds")); // Face card
        game.bankerHand.add(new Card(10, "Clubs"));
        game.bankerHand.add(new Card(9, "Spades"));
        double winnings = game.evaluateWinnings();
        assertEquals(150, winnings, "Winnings calculation failed for natural blackjack");
    }

    // Test for evaluateWinnings - Player Wins (Not Blackjack)
    @Test
    void testEvaluateWinningsPlayerWins() {
        BlackJackGame game = new BlackJackGame();
        game.currentBet = 100;
        game.playerHand.add(new Card(10, "Hearts")); // Face card
        game.playerHand.add(new Card(9, "Diamonds"));
        game.bankerHand.add(new Card(10, "Clubs"));
        game.bankerHand.add(new Card(8, "Spades"));
        double winnings = game.evaluateWinnings();
        assertEquals(100, winnings, "Winnings calculation failed for player win");
    }

    // Test for evaluateWinnings - Player Loses
    @Test
    void testEvaluateWinningsPlayerLoses() {
        BlackJackGame game = new BlackJackGame();
        game.currentBet = 100;
        game.playerHand.add(new Card(10, "Hearts"));
        game.playerHand.add(new Card(6, "Diamonds"));
        game.bankerHand.add(new Card(10, "Clubs"));
        game.bankerHand.add(new Card(7, "Spades"));
        double winnings = game.evaluateWinnings();
        assertEquals(-100, winnings, "Winnings calculation failed for player loss");
    }

    // Test for evaluateWinnings - Push or Tie
    @Test
    void testEvaluateWinningsPush() {
        BlackJackGame game = new BlackJackGame();
        game.currentBet = 100;
        game.playerHand.add(new Card(10, "Hearts"));
        game.playerHand.add(new Card(7, "Diamonds"));
        game.bankerHand.add(new Card(9, "Clubs"));
        game.bankerHand.add(new Card(8, "Spades"));
        double winnings = game.evaluateWinnings();
        assertEquals(0.0, winnings, "Winnings calculation failed for push or tie");
    }

    // Test for handTotal - Various Hands
    @Test
    void testHandTotal() {
        BlackJackGame game = new BlackJackGame();
        game.playerHand.add(new Card(10, "Hearts"));
        game.playerHand.add(new Card(2, "Diamonds"));
        int total = game.handTotal(game.playerHand);
        assertEquals(12, total, "Hand total calculation failed");
    }

    // Further Tests for evaluateWinnings - Player Busts
    @Test
    void testEvaluateWinningsPlayerBusts() {
        BlackJackGame game = new BlackJackGame();
        game.currentBet = 100;
        game.playerHand.add(new Card(10, "Hearts")); // Face card
        game.playerHand.add(new Card(10, "Diamonds")); // Face card
        game.playerHand.add(new Card(5, "Clubs")); // Bust
        game.bankerHand.add(new Card(10, "Spades")); // Face card
        game.bankerHand.add(new Card(6, "Hearts"));
        double winnings = game.evaluateWinnings();
        assertEquals(-100, winnings, "Winnings calculation failed for player bust");
    }

    // Player Wins with 5-Card Trick
    @Test
    void testEvaluateWinningsFiveCardTrick() {
        BlackJackGame game = new BlackJackGame();
        game.currentBet = 100;
        game.playerHand.add(new Card(2, "Hearts"));
        game.playerHand.add(new Card(3, "Diamonds"));
        game.playerHand.add(new Card(4, "Clubs"));
        game.playerHand.add(new Card(5, "Spades"));
        game.playerHand.add(new Card(6, "Hearts")); // 5-card trick
        game.bankerHand.add(new Card(10, "Spades"));
        game.bankerHand.add(new Card(6, "Clubs"));
        double winnings = game.evaluateWinnings();
        assertEquals(100, winnings, "Winnings calculation failed for 5-card trick");
    }

    // Dealer Wins with Blackjack
    @Test
    void testEvaluateWinningsDealerBlackjack() {
        BlackJackGame game = new BlackJackGame();
        game.currentBet = 100;
        game.playerHand.add(new Card(9, "Hearts"));
        game.playerHand.add(new Card(8, "Diamonds"));
        game.bankerHand.add(new Card(1, "Clubs")); // Ace
        game.bankerHand.add(new Card(10, "Spades")); // Face card for Blackjack
        double winnings = game.evaluateWinnings();
        assertEquals(-100, winnings, "Winnings calculation failed for dealer blackjack");
    }

    // Additional Tests for handTotal - Hand with Multiple Aces
    @Test
    void testHandTotalMultipleAces() {
        BlackJackGame game = new BlackJackGame();
        game.playerHand.add(new Card(1, "Hearts")); // Ace
        game.playerHand.add(new Card(1, "Diamonds")); // Ace
        int total = game.handTotal(game.playerHand);
        assertEquals(12, total, "Hand total calculation failed with multiple aces");
    }

    // Hand Total with Face Cards
    @Test
    void testHandTotalWithFaceCards() {
        BlackJackGame game = new BlackJackGame();
        game.playerHand.add(new Card(11, "Hearts")); // Jack
        game.playerHand.add(new Card(12, "Diamonds")); // Queen
        int total = game.handTotal(game.playerHand);
        assertEquals(20, total, "Hand total calculation failed with face cards");
    }


}
