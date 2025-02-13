import java.util.ArrayList;
import java.util.Collections;

public class BlackJackDealer {

    ArrayList<Card> deck = new ArrayList<>();

    // this should generate 52 cards, one for each of 13 faces and 4 suits.
    public void generateDeck(){

        deck.clear(); // Assuming 'deck' is a List<Card>

        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String suit : suits) {
            // Add numeric cards 2-10
            for (int i = 2; i <= 10; i++) {
                deck.add(new Card(i, suit));
            }
            // Add face cards
            deck.add(new Card(11, suit)); // Jack
            deck.add(new Card(12, suit)); // Queen
            deck.add(new Card(13, suit)); // King
            deck.add(new Card(1, suit));  // Ace
        }
    }

    // this will return an Arraylist of two cards and leave the remainder of the deck able to
    // be drawn later.
    public ArrayList<Card> dealHand(){
        ArrayList<Card> twoCards = new ArrayList<Card>();
        if (deck.size() >= 2) {
            twoCards.add(deck.remove(0));
            twoCards.add(deck.remove(1));
        }
        return twoCards;
    }

    // this will return the next card on top of the deck
    public Card drawOne(){
        if (!deck.isEmpty()){
            return deck.remove(0);
        }
        return null;

    }

    //this will return all 52 cards to the deck and shuffle their order
    public void shuffleDeck(){
        Collections.shuffle(deck);

    }

    // this will return the number of cards left in the deck. After a call to shuffleDeck()
    // this should be 52.
    public int deckSize(){
        return deck.size();
    }

}
