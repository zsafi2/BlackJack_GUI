import java.util.ArrayList;


public class BlackJackGame {
    ArrayList<Card> playerHand = new ArrayList<>();
    ArrayList<Card> bankerHand = new ArrayList<>();
    BlackJackDealer theDealer = new BlackJackDealer();
    BlackJackGameLogic gameLogic = new BlackJackGameLogic();

    // the amount currently bet from the user
    double currentBet = 0;

    //the total amount of value that the user has.
    double totalWinnings = 0;


    // This method will determine if the user won or lost their bet
    // and return the amount won or lost based on the value in currentBet.
    public double evaluateWinnings() {

        String result = gameLogic.whoWon(playerHand, bankerHand);

        if ("player".equals(result) && playerHand.size() == 2 && handTotal(playerHand) == 21) {
            totalWinnings += currentBet;
            return totalWinnings * 1.5; // Natural blackjack
        } else if ("player".equals(result)) {
            return currentBet;
        } else if ("dealer".equals(result)) {
            return -currentBet;
        } else { // Push or tie
            return 0.0;
        }
    }
    public int handTotal(ArrayList<Card> hand) {
        return gameLogic.handTotal(hand); // Delegate to game logic
    }
}
