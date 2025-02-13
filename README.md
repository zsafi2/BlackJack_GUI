BlackJack GUI
A simple Blackjack (also known as 21) game implemented in Java with a JavaFX graphical user interface. This application demonstrates the essentials of GUI programming, JavaFX layouts, and event handling in a fun card game context.

Table of Contents
Features
Requirements
Installation
How to Run
Gameplay
Project Structure
Contributing
License
Features
Interactive GUI using JavaFX
Dealer AI simulates realistic blackjack dealing
Player choices include “Hit,” “Stand,” (and possibly “Double Down,” “Split,” etc. if implemented)
Card graphics (if included) to visually represent the deck, suits, and face values
Scoring and outcome display with immediate feedback
Requirements
Java 11 or higher
Maven 3.6+ (to build the project)
JavaFX dependencies (handled automatically via Maven)
Your pom.xml includes:

xml
Copy
Edit
<dependencies>
    <!-- JUnit 5 for tests -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.1</version>
        <scope>test</scope>
    </dependency>
    
    <!-- JavaFX Controls & FXML -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>19.0.2.1</version>
        <scope>compile</scope>
    </dependency>

    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>19.0.2.1</version>
    </dependency>
</dependencies>
Installation
Clone the Repository:
bash
Copy
Edit
git clone https://github.com/YourUsername/BlackJackGUI.git
Navigate to the Project Folder:
bash
Copy
Edit
cd BlackJackGUI
Build with Maven:
bash
Copy
Edit
mvn clean install
This command will download all dependencies and compile the source code.
How to Run
By default, the Maven project is configured with:

xml
Copy
Edit
<exec.mainClass>Game</exec.mainClass>
to indicate the main class.

There are a few ways to run the JavaFX application:

1. Using Maven Exec Plugin (If Configured)
If you have (or choose to add) the Maven Exec Plugin configured in your pom.xml, you can run:

bash
Copy
Edit
mvn clean compile exec:java
This will launch the JavaFX application directly from Maven.

2. Using the JavaFX Maven Plugin (Optional)
Alternatively, if you add the JavaFX Maven Plugin to your pom.xml, you could run:

bash
Copy
Edit
mvn javafx:run
(This plugin is not currently shown in your pom.xml, but it’s a common way to run JavaFX apps.)

3. Using the Packaged JAR
If your build is successful, Maven will create a JAR in the target folder:

pgsql
Copy
Edit
target/BlackJackGUI-0.0.1-SNAPSHOT.jar
You can run it with:

bash
Copy
Edit
java -jar target/BlackJackGUI-0.0.1-SNAPSHOT.jar
Make sure the required JavaFX modules are on the module path if needed, depending on how you’ve structured your application.

Gameplay
Objective: Get as close to 21 without going over.
Initial Deal: Both the player and dealer receive two cards.
Player Turn:
Hit: Draw an additional card.
Stand: End your turn.
(Other rules like Double Down or Split can be included if implemented.)
Dealer Turn: Dealer draws until reaching at least 17.
Win Condition:
If you exceed 21, you bust.
If the dealer exceeds 21, the dealer busts.
Otherwise, whoever is closest to 21 wins.
Project Structure
Typical structure (may vary):

bash
Copy
Edit
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── Game.java           # Main class (launches the JavaFX app)
│   │   │   ├── controllers         # (Optional) JavaFX controllers
│   │   │   ├── model               # Classes for cards, deck, player, dealer logic
│   │   │   └── ...                
│   │   └── resources
│   │       ├── fxml               # (Optional) FXML layouts
│   │       └── images             # (Optional) card images or UI assets
│   └── test
│       └── java
│           └── ...                # JUnit tests
├── pom.xml
└── README.md
Contributing
Fork the repo
Create a feature branch: git checkout -b feature/amazing-feature
Commit changes: git commit -m 'Add amazing feature'
Push to your branch: git push origin feature/amazing-feature
Open a Pull Request on GitHub
License
This project is licensed under the MIT License (or whichever you prefer). Include a LICENSE file in the repository to clarify usage terms.

