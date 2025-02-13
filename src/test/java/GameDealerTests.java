import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameDealerTests {

    // Extended Tests for generateDeck
    @Test
    void testGenerateDeckContents() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        HashMap<String, Integer> suitCounts = new HashMap<>();
        HashMap<Integer, Integer> valueCounts = new HashMap<>();

        for (Card card : dealer.deck) {
            suitCounts.put(card.suit, suitCounts.getOrDefault(card.suit, 0) + 1);
            valueCounts.put(card.value, valueCounts.getOrDefault(card.value, 0) + 1);
        }

        for (String suit : new String[]{"Hearts", "Diamonds", "Clubs", "Spades"}) {
            assertEquals(13, suitCounts.getOrDefault(suit, 0), "Incorrect number of " + suit);
        }

        for (int i = 1; i <= 13; i++) {
            assertEquals(4, valueCounts.getOrDefault(i, 0), "Incorrect number of value " + i);
        }
    }


    // More Detailed Tests for dealHand
    @Test
    void testDealHandEmptyDeck() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.deck.clear();
        ArrayList<Card> hand = dealer.dealHand();
        assertTrue(hand.isEmpty(), "Hand should be empty if deck is empty");
    }

    @Test
    void testDealMultipleHands() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        dealer.dealHand(); // First hand
        dealer.dealHand(); // Second hand
        assertEquals(48, dealer.deck.size(), "Deck should have 48 cards after dealing two hands");
    }

    // Additional Tests for drawOne
    @Test
    void testDrawOneEmptyDeck() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.deck.clear();
        assertNull(dealer.drawOne(), "Should return null if deck is empty");
    }

    @Test
    void testSequentialDraws() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        Card firstDraw = dealer.drawOne();
        Card secondDraw = dealer.drawOne();
        assertNotEquals(firstDraw, secondDraw, "Sequential draws should yield different cards");
        assertEquals(50, dealer.deck.size(), "Deck should have 50 cards after two draws");
    }

    // Further Tests for shuffleDeck
    @Test
    void testShuffleDeckOrderChange() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        ArrayList<Card> originalDeck = new ArrayList<>(dealer.deck);
        dealer.shuffleDeck();
        assertFalse(originalDeck.equals(dealer.deck), "Deck order should change after shuffling");
    }

    // More Tests for deckSize
    @Test
    void testDeckSizeVariousScenarios() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        dealer.shuffleDeck();
        assertEquals(52, dealer.deckSize(), "Deck size should be 52 after shuffle");
        dealer.dealHand();
        dealer.drawOne();
        assertEquals(49, dealer.deckSize(), "Deck size should be 49 after dealing a hand and drawing a card");
    }

    @Test
    void testGenerateDeckConsistency() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        ArrayList<Card> firstGeneration = new ArrayList<>(dealer.deck);
        dealer.generateDeck();
        ArrayList<Card> secondGeneration = new ArrayList<>(dealer.deck);
        int n = 0;
        for (Card card : firstGeneration){
            assertEquals(card.value, secondGeneration.get(n).value, "Deck generation should be consistent");
            assertEquals(card.suit, secondGeneration.get(n).suit, "Deck generation should be consistent");
            n++;
        }

    }


    @Test
    void testDealHandAfterShuffle() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        dealer.shuffleDeck();
        ArrayList<Card> hand = dealer.dealHand();
        assertEquals(2, hand.size(), "Hand should contain 2 cards after shuffle");
        assertEquals(50, dealer.deck.size(), "Deck should have 50 cards left after deal");
    }

    // Extended Testing for drawOne
    @Test
    void testDrawCardsUntilEmpty() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        while (!dealer.deck.isEmpty()) {
            dealer.drawOne();
        }
        assertTrue(dealer.deck.isEmpty(), "Deck should be empty after drawing all cards");
    }

    // Robustness Tests for shuffleDeck
    @Test
    void testShuffleDeckMultipleTimes() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        ArrayList<Card> preShuffle = new ArrayList<>(dealer.deck);
        dealer.shuffleDeck();
        ArrayList<Card> postShuffle = new ArrayList<>(dealer.deck);
        dealer.shuffleDeck();
        ArrayList<Card> secondShuffle = new ArrayList<>(dealer.deck);
        assertFalse(preShuffle.equals(postShuffle), "First shuffle should change the order");
        assertFalse(postShuffle.equals(secondShuffle), "Second shuffle should change the order");
    }

    // Comprehensive Tests for deckSize
    @Test
    void testDeckSizeAfterAllActions() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize(), "Deck size should be 52 after generation");
        dealer.dealHand();
        assertEquals(50, dealer.deckSize(), "Deck size should be 50 after dealing a hand");
        dealer.drawOne();
        assertEquals(49, dealer.deckSize(), "Deck size should be 49 after drawing a card");
        dealer.shuffleDeck();
        assertEquals(49, dealer.deckSize(), "Deck size should remain 49 after shuffling");
    }



    @Test
    void testShuffleEmptyDeck() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.deck.clear();
        dealer.shuffleDeck();
        assertTrue(dealer.deck.isEmpty(), "Shuffling an empty deck should leave it empty");
    }

    // In-depth Testing for drawOne
    @Test
    void testDrawOneSpecificCard() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        Card expectedCard = dealer.deck.get(0);
        Card drawnCard = dealer.drawOne();
        assertEquals(expectedCard, drawnCard, "The drawn card should match the expected card");
    }

    @Test
    void testDrawAllCards() {
        BlackJackDealer dealer = new BlackJackDealer();
        dealer.generateDeck();
        ArrayList<Card> drawnCards = new ArrayList<>();
        while (dealer.deckSize() > 0) {
            drawnCards.add(dealer.drawOne());
        }
        assertEquals(52, drawnCards.size(), "Should draw 52 cards in total");
    }





}

