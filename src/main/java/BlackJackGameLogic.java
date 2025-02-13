import java.util.ArrayList;

public class BlackJackGameLogic {

    public String whoWon(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {

        int sumPlayer = handTotal(playerHand);
        int sumDealer = handTotal(dealerHand);

        if (sumPlayer > 21 && sumDealer > 21) {
            return "push";
        } else if (sumPlayer > 21){
            return "dealer";
        } else if (sumDealer > 21) {
            return "player";
        } else if (playerHand.size() == 2 && sumPlayer == 21 && sumDealer != 21) {
            return "player";
        } else if (dealerHand.size() == 2 && playerHand.size() > 2 && sumPlayer == 21 && sumDealer == 21){
            return "dealer";
        } else if (sumPlayer > sumDealer) {
            return "player";
        } else if (sumDealer > sumPlayer) {
            return "dealer";
        } else {
            return "push";
        }

    }
    public int handTotal(ArrayList<Card> hand) {

        int total = 0;
        int aceCount = 0;

        for (Card card : hand) {
            if (card.value > 10) {
                total += 10; // Face cards
            } else if (card.value == 1) {
                aceCount = aceCount + 1;
                total += 1;
            } else {
                total += card.value;
            }
        }

        while (aceCount > 0) {
            if ((10 + total) < 22) {
                total += 10;
            }
            aceCount = aceCount - 1;
        }

        return total;
    }

    public boolean evaluateBankerDraw(ArrayList<Card> hand) {
        return handTotal(hand) <= 16;
    }
}

