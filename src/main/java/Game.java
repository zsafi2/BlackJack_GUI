import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.util.*;


import static java.lang.Math.abs;

public class Game extends Application {

    private Button start, restart, Deal, Hit, Stand, Replay, Bet;

    private Label gameTitle, Enter_Name, Enter_Total, Dealer, User, UserTotal, message;

    private TextField name, totalAmount, BetAmount;

    private ImageView Dealer_Card1, Dealer_Card2, User_Card1, User_Card2;

    private Pane GameMenu, StartMenu;

    private ArrayList<ImageView> playerCardViews = new ArrayList<>();

    private String UserName;
    private double PlayingAmount;
    private int new_card_position = 300;

    private BlackJackGame Game = new BlackJackGame();

    private Button Create_Button(String name, int Width, int Height, int x, int y, Boolean Disalbe){

        Button newButton = new Button(name);
        newButton.setPrefWidth(Width);
        newButton.setPrefHeight(Height);
        newButton.setLayoutX(x);
        newButton.setLayoutY(y);
        newButton.setDisable(Disalbe);
        return newButton;
    }

    private Label Create_Label(String prompt, int font, int x, int y){

        Label newLabel = new Label(prompt);
        newLabel.setFont(new Font(font));
        newLabel.setLayoutX(x);
        newLabel.setLayoutY(y);
        return newLabel;
    }

    private TextField Create_TextField(String prompt, int Width, int x, int y){

        TextField newTextField = new TextField();
        newTextField.setPromptText(prompt);
        newTextField.setPrefWidth(Width);
        newTextField.setLayoutX(x);
        newTextField.setLayoutY(y);
        return newTextField;
    }

    private ImageView Create_Image(String name, int x, int y){

        Image card = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/PNG-cards/" + name)));
        ImageView newView = new ImageView(card);
        newView.setFitHeight(180);
        newView.setFitWidth(90);
        newView.setPreserveRatio(true);
        newView.setLayoutX(x);
        newView.setLayoutY(y);
        return newView;
    }

    private Scene CreateMainScene(){

        gameTitle = Create_Label("Black Jack Game!", 30, 80, 30);
        Enter_Name = Create_Label("Name", 10, 70, 120);
        Enter_Total = Create_Label("Playing Amount", 10, 70, 180);

        name = Create_TextField("Enter your Name", 200, 70, 140);
        totalAmount= Create_TextField("Enter the Playing Amount", 200, 70, 200);

        start = Create_Button("Start", 200, 40, 70, 250, false);

        StartMenu = new Pane(gameTitle, Enter_Name, name, Enter_Total, totalAmount, start);
        StartMenu.setStyle("-fx-background-color: #ADD8E6;"); // Light blue background

        return new Scene(StartMenu, 400, 400);
    }

    private Scene CreateGameScene(){

        Dealer = Create_Label("Dealer", 15, 290, 70);
        Dealer.setStyle("-fx-text-fill: blue;");

        User = Create_Label(UserName, 15, 290, 280);
        User.setStyle("-fx-text-fill: blue;");

        UserTotal = Create_Label("Total : " + totalAmount, 20, 20, 550);
        UserTotal.setStyle("-fx-text-fill: red;");

        restart = Create_Button("Restart", 100, 30, 20, 20, false);
        Deal = Create_Button("Deal", 150, 60, 530, 520, true);
        Hit = Create_Button("Hit", 100, 30, 250, 490, true);
        Stand = Create_Button("Stand", 100, 30, 360, 490, true);
        Replay = Create_Button("Replay", 210, 50, 250, 530, true);
        Bet = Create_Button("Bet", 150, 40, 20, 490, false);

        BetAmount = Create_TextField("Enter your Bet Amount", 150, 20, 450);

        GameMenu = new Pane(Dealer, User, UserTotal, restart, Deal, Hit, Stand, Bet, BetAmount, Replay);
        GameMenu.setStyle("-fx-background-color: lightgreen;");

        return new Scene(GameMenu, 700, 600);
    }

    private String GenerateName(int value, String Suit){
        return value + "_of_" + Suit + ".png";
    }

    private void handleStart(Stage stage, Scene GameScene){

        UserName = name.getText();  // Update UserName

        try {
            PlayingAmount = Double.parseDouble(totalAmount.getText());
        } catch (NumberFormatException ex) {
            totalAmount.clear();
            totalAmount.setPromptText("Invalid input. Enter again");
            return;  // Return early to prevent scene switch on invalid input
        }

        // Update labels with the new values
        User.setText(UserName);
        UserTotal.setText("Total: " + PlayingAmount);
        message = Create_Label("", 30, 440, 240);
        message.setStyle("-fx-text-fill: red;");

        stage.setScene(GameScene);  // Switch to the Game scene
    }

    private void handleBet(){

        try {
            int bet = Integer.parseInt(BetAmount.getText());

            // Check if bet amount is greater than playing amount
            if (bet > PlayingAmount) {
                BetAmount.clear();
                BetAmount.setPromptText("Amount higher than total");
            } else {
                Game.currentBet = bet;
                BetAmount.clear();
                BetAmount.setPromptText("Bet Amount: " + Game.currentBet);
                BetAmount.setDisable(true);
                Bet.setDisable(true);
                Deal.setDisable(false);
            }
        } catch (NumberFormatException ex) {
            BetAmount.clear();
            BetAmount.setPromptText("Invalid Input");
        }
    }

    private void handleDeal(){

        Game.theDealer.generateDeck();
        Game.theDealer.shuffleDeck();
        Game.playerHand = Game.theDealer.dealHand();
        Game.bankerHand = Game.theDealer.dealHand();

        String Dealer_Card1_name = GenerateName(Game.bankerHand.get(0).value, Game.bankerHand.get(0).suit);
        String Dealer_Card2_name = GenerateName(Game.bankerHand.get(1).value, Game.bankerHand.get(1).suit);
        String User_Card1_name = GenerateName(Game.playerHand.get(0).value, Game.playerHand.get(0).suit);
        String User_Card2_name = GenerateName(Game.playerHand.get(1).value, Game.playerHand.get(1).suit);

        Dealer_Card1 = Create_Image("Card_back.png", 260, 110);
        Dealer_Card2 = Create_Image(Dealer_Card2_name, 280, 110);
        User_Card1 = Create_Image(User_Card1_name, 260, 320);
        User_Card2 = Create_Image(User_Card2_name, 280, 320);

        int UserHandTotal = Game.gameLogic.handTotal(Game.playerHand);
        int DealerHandTotal = Game.gameLogic.handTotal(Game.bankerHand);

        User.setText(UserName + ": " + UserHandTotal);

        GameMenu.getChildren().addAll(Dealer_Card1, Dealer_Card2, User_Card1, User_Card2);
        Image show_dealer_card = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/PNG-cards/" + Dealer_Card1_name)));

        if (UserHandTotal == 21 && DealerHandTotal != 21){

            Dealer_Card1.setImage(show_dealer_card);

            double amountWon = Game.evaluateWinnings();

            Game.totalWinnings += amountWon;
            PlayingAmount += amountWon;
            UserTotal.setText("Total: " + PlayingAmount);

            message.setText(UserName + " Won!" + "\nYou Won: " + amountWon);
            GameMenu.getChildren().addAll(message);
            Replay.setDisable(false);
            Deal.setDisable(true);

        }

        else if(UserHandTotal == 21){

            Dealer_Card1.setImage(show_dealer_card);

            message.setText("Game Draw");
            GameMenu.getChildren().addAll(message);
            Replay.setDisable(false);
            Deal.setDisable(true);
        }

        else {
            Deal.setDisable(true);
            Hit.setDisable(false);
            Stand.setDisable(false);

        }
        playerCardViews.add(Dealer_Card1);
        playerCardViews.add(Dealer_Card2);
        playerCardViews.add(User_Card1);
        playerCardViews.add(User_Card2);
    }

    private void handleHit(Stage stage, Scene mainScene){

        Card new_card = Game.theDealer.drawOne();
        Game.playerHand.add(new_card);

        String new_Card_name = GenerateName(new_card.value, new_card.suit);

        ImageView newView = Create_Image(new_Card_name, new_card_position, 320);

        new_card_position += 20;
        playerCardViews.add(newView);

        GameMenu.getChildren().addAll(newView);

        User.setText(UserName + ": " + Game.gameLogic.handTotal(Game.playerHand));

        if (Game.gameLogic.handTotal(Game.playerHand) > 21){

            double amount_lost = Game.evaluateWinnings();
            Game.totalWinnings += amount_lost;
            PlayingAmount += amount_lost;
            UserTotal.setText("Total: " + PlayingAmount);
            Stand.setDisable(true);
            Replay.setDisable(false);
            Hit.setDisable(true);

            message.setText("Dealer Won!" + "\nYou lost: " + abs(amount_lost));
            GameMenu.getChildren().addAll(message);

            if (PlayingAmount <= 0){
                Alert losing_alert = new Alert(Alert.AlertType.INFORMATION);
                losing_alert.setContentText("You Lost the Total Amount");
                losing_alert.showAndWait();
                handleRestart(stage, mainScene);

            }
        }
    }

    private void handleStand(Stage stage, Scene mainScene){

        String Dealer_Card1_name = GenerateName(Game.bankerHand.get(0).value, Game.bankerHand.get(0).suit);
        Hit.setDisable(true);

        Image show_dealer_card = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/PNG-cards/" + Dealer_Card1_name)));
        Dealer_Card1.setImage(show_dealer_card);

        new_card_position = 300;
        Dealer.setText("Dealer: " + Game.gameLogic.handTotal(Game.bankerHand));

        while(Game.gameLogic.evaluateBankerDraw(Game.bankerHand)) {
            Card new_card = Game.theDealer.drawOne();
            String new_Card_name = GenerateName(new_card.value, new_card.suit);
            ImageView newView = Create_Image(new_Card_name, new_card_position, 110);
            new_card_position += 20;
            Game.bankerHand.add(new_card);
            playerCardViews.add(newView);
            GameMenu.getChildren().addAll(newView);
        }

        Dealer.setText("Dealer: " + Game.gameLogic.handTotal(Game.bankerHand));

        String result = Game.gameLogic.whoWon(Game.playerHand, Game.bankerHand);

        double amountWon = Game.evaluateWinnings();
        Game.totalWinnings += amountWon;
        PlayingAmount += amountWon;
        Replay.setDisable(false);
        Stand.setDisable(true);
        UserTotal.setText("Total: " + PlayingAmount);


        if (result.equals("player")){
            message.setText(UserName + " Won" + "\nAmount Won: " + amountWon);
            GameMenu.getChildren().addAll(message);

        } else if (result.equals("dealer")){
            message.setText("Dealer Won!" + "\nYou lost: " + abs(amountWon));
            GameMenu.getChildren().addAll(message);

            if (PlayingAmount <= 0){
                Alert losing_alert = new Alert(Alert.AlertType.INFORMATION);
                losing_alert.setContentText("You Lost the Total Amount");
                losing_alert.showAndWait();
                handleRestart(stage, mainScene);
            }
        }
        else{
            message.setText("Game Draw!");
            GameMenu.getChildren().addAll(message);
        }
    }

    private void handleReplay(){

        Bet.setDisable(false);
        BetAmount.setPromptText("Enter your BetAmount");
        BetAmount.setDisable(false);
        Hit.setDisable(true);
        Stand.setDisable(true);
        Replay.setDisable(true);
        Game.playerHand.clear();
        Game.bankerHand.clear();
        Game.theDealer.deck.clear();
        Dealer.setText("Dealer");
        GameMenu.getChildren().removeAll(message);

        for (ImageView i : playerCardViews) {
            GameMenu.getChildren().removeAll(i);
        }
        User.setText(UserName);
        new_card_position = 300;

    }

    private void handleRestart(Stage stage, Scene mainScene) {
        handleReplay();
        name.clear();
        totalAmount.clear();
        Game.totalWinnings = 0;
        Game.currentBet = 0;
        stage.setScene(mainScene);
    }

    @Override
    public void start(Stage stage) {

        Scene MainScene = CreateMainScene();
        Scene GameScene = CreateGameScene();

        start.setOnAction(e-> handleStart(stage, GameScene));

        Bet.setOnAction(e -> handleBet());

        Deal.setOnAction(e -> handleDeal());

        Hit.setOnAction(e -> handleHit(stage, MainScene));

        Stand.setOnAction(e -> handleStand(stage, MainScene));

        Replay.setOnAction(e -> handleReplay());

        restart.setOnAction(e -> handleRestart(stage, MainScene));

        stage.setScene(MainScene);
        stage.setTitle("Black Jack Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


