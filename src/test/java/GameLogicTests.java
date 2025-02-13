import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLogicTests{

    @Test
    void testHandTotal(){

        BlackJackGameLogic game = new BlackJackGameLogic();
        Card card1 = new Card(10, "diamonds");
        Card card2 = new Card(3, "spades");
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        assertEquals(game.handTotal(cardList), 13, "Hand total failed adding value cards");
    }

    @Test
    void testHandTotalFaceCards() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card(11, "hearts")); // Jack
        cardList.add(new Card(12, "clubs")); // Queen
        cardList.add(new Card(13, "spades")); // King
        assertEquals(game.handTotal(cardList), 30, "Hand total failed with face cards");
    }

    @Test
    void testHandTotalAces() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, "hearts")); // Ace
        cardList.add(new Card(9, "clubs"));
        assertEquals(game.handTotal(cardList), 20, "Hand total failed with ace counting as 11");
    }

    @Test
    void testWhoWonPlayerBlackjack() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(new Card(1, "hearts")); // Ace
        playerHand.add(new Card(10, "diamonds"));
        dealerHand.add(new Card(10, "clubs"));
        dealerHand.add(new Card(9, "spades"));
        assertEquals(game.whoWon(playerHand, dealerHand), "player", "Blackjack scenario failed for player win");
    }

    @Test
    void testHandTotalMultipleAces() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, "diamonds")); // Ace
        cardList.add(new Card(1, "spades")); // Ace
        assertEquals(game.handTotal(cardList), 12, "Hand total failed with multiple aces");
    }

    // Tests for whoWon
    @Test
    void testWhoWonDealerWins() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(new Card(10, "hearts"));
        playerHand.add(new Card(6, "diamonds"));
        dealerHand.add(new Card(10, "clubs"));
        dealerHand.add(new Card(8, "spades"));
        assertEquals(game.whoWon(playerHand, dealerHand), "dealer", "Dealer should win this scenario");
    }

    @Test
    void testWhoWonPushScenario() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(new Card(10, "hearts"));
        playerHand.add(new Card(7, "diamonds"));
        dealerHand.add(new Card(10, "clubs"));
        dealerHand.add(new Card(7, "spades"));
        assertEquals(game.whoWon(playerHand, dealerHand), "push", "The game should be a push in this scenario");
    }

    @Test
    void testWhoWonBothBlackjack() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(new Card(1, "hearts")); // Ace
        playerHand.add(new Card(10, "diamonds"));
        dealerHand.add(new Card(1, "clubs")); // Ace
        dealerHand.add(new Card(10, "spades"));
        assertEquals(game.whoWon(playerHand, dealerHand), "push", "Both have blackjack, should be a push");
    }

    // Test for evaluateBankerDraw
    @Test
    void testEvaluateBankerDraw() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(9, "hearts"));
        hand.add(new Card(8, "clubs"));
        assertFalse(game.evaluateBankerDraw(hand), "Banker should not draw with a total of 17 or more");
    }

    @Test
    void testHandTotalMixedHand() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, "hearts")); // Ace
        cardList.add(new Card(10, "clubs")); // Face card
        cardList.add(new Card(5, "spades")); // Number card
        assertEquals(game.handTotal(cardList), 16, "Hand total calculation failed for mixed hand");
    }

    @Test
    void testHandTotalExceedsTwentyOne() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card(10, "diamonds")); // Face card
        cardList.add(new Card(10, "spades")); // Face card
        cardList.add(new Card(5, "clubs")); // Number card
        assertEquals(game.handTotal(cardList), 25, "Hand total should exceed 21");
    }

    // Tests for whoWon
    @Test
    void testWhoWonPlayerWithLessThan21() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(new Card(9, "hearts"));
        playerHand.add(new Card(7, "diamonds"));
        dealerHand.add(new Card(10, "clubs"));
        dealerHand.add(new Card(5, "spades"));
        assertEquals(game.whoWon(playerHand, dealerHand), "player", "Player should win with a total less than 21 but higher than the dealer");
    }

    @Test
    void testWhoWonDealerWithLessThan21() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(new Card(7, "hearts"));
        playerHand.add(new Card(8, "diamonds"));
        dealerHand.add(new Card(9, "clubs"));
        dealerHand.add(new Card(7, "spades"));
        assertEquals(game.whoWon(playerHand, dealerHand), "dealer", "Dealer should win with a total less than 21 but higher than the player");
    }

    @Test
    void testWhoWonPushUnder21() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(new Card(10, "hearts"));
        playerHand.add(new Card(6, "diamonds"));
        dealerHand.add(new Card(8, "clubs"));
        dealerHand.add(new Card(8, "spades"));
        assertEquals(game.whoWon(playerHand, dealerHand), "push", "Game should be a push with both having the same total under 21");
    }

    // Test for evaluateBankerDraw
    @Test
    void testEvaluateBankerDrawOn16() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(10, "hearts"));
        hand.add(new Card(6, "clubs"));
        assertTrue(game.evaluateBankerDraw(hand), "Banker should draw with a total of 16");
    }

    @Test
    void testEvaluateBankerDrawOn17() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(10, "hearts"));
        hand.add(new Card(7, "clubs"));
        assertFalse(game.evaluateBankerDraw(hand), "Banker should not draw with a total of 17 or more");
    }

    @Test
    void testHandTotalWithMultipleAces() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, "hearts")); // Ace
        cardList.add(new Card(1, "spades")); // Ace
        cardList.add(new Card(9, "clubs"));
        assertEquals(game.handTotal(cardList), 21, "Hand total failed with multiple aces");
    }

    @Test
    void testHandTotalAllFaceCards() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card(11, "hearts")); // Jack
        cardList.add(new Card(12, "spades")); // Queen
        cardList.add(new Card(13, "clubs")); // King
        assertEquals(game.handTotal(cardList), 30, "Hand total failed with all face cards");
    }

    // Additional Tests for whoWon
    @Test
    void testWhoWonDealerBlackjack() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(new Card(10, "diamonds"));
        playerHand.add(new Card(7, "hearts"));
        playerHand.add(new Card(4, "clubs"));
        dealerHand.add(new Card(1, "spades")); // Ace
        dealerHand.add(new Card(10, "hearts"));
        assertEquals(game.whoWon(playerHand, dealerHand), "dealer", "Dealer should win with a blackjack");
    }

    @Test
    void testWhoWonPlayerWinsDealerBusts() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(new Card(9, "diamonds"));
        playerHand.add(new Card(7, "hearts"));
        dealerHand.add(new Card(10, "clubs"));
        dealerHand.add(new Card(10, "spades"));
        dealerHand.add(new Card(5, "hearts"));
        assertEquals(game.whoWon(playerHand, dealerHand), "player", "Player should win as dealer busts");
    }

    @Test
    void testWhoWonBothBustPlayerHigherTotal() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(new Card(10, "diamonds"));
        playerHand.add(new Card(10, "hearts"));
        playerHand.add(new Card(5, "clubs"));
        dealerHand.add(new Card(9, "spades"));
        dealerHand.add(new Card(9, "hearts"));
        dealerHand.add(new Card(5, "diamonds"));
        assertEquals(game.whoWon(playerHand, dealerHand), "push", "Dealer should win as both bust but player has a higher total");
    }

    // Test for evaluateBankerDraw with Soft 17
    @Test
    void testEvaluateBankerDrawSoft17() {
        BlackJackGameLogic game = new BlackJackGameLogic();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(1, "hearts")); // Ace
        hand.add(new Card(6, "spades"));
        assertFalse(game.evaluateBankerDraw(hand), "Banker should not draw with a soft 17");
    }

}
