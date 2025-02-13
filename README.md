# BlackJack GUI

A simple **Blackjack** (also known as 21) game implemented in **Java** with a **JavaFX** graphical user interface. This application demonstrates the essentials of GUI programming, JavaFX layouts, and event handling in a fun card game context.

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [How to Run](#how-to-run)
- [Gameplay](#gameplay)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Interactive GUI** using JavaFX  
- **Dealer AI** simulates realistic blackjack dealing  
- **Player choices** like “Hit,” “Stand,” (and possibly other actions if implemented)  
- **Card graphics** (if included) to visually represent the deck, suits, and face values  
- **Scoring and outcome** display with immediate feedback  

## Requirements
- **Java 11** or higher  
- **Maven 3.6+** (to build the project)  
- JavaFX dependencies (handled automatically via Maven)

Your `pom.xml` includes:
```xml
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
```

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/YourUsername/BlackJackGUI.git
   ```
2. **Navigate to the Project Folder**:
   ```bash
   cd BlackJackGUI
   ```
3. **Build with Maven**:
   ```bash
   mvn clean install
   ```
   This command will download all dependencies and compile the source code.

## How to Run
By default, the Maven project is configured with:
```xml
<exec.mainClass>Game</exec.mainClass>
```
to indicate the main class.

There are a few ways to run the JavaFX application:

### 1. Using Maven Exec Plugin
If you have (or choose to add) the [Maven Exec Plugin](https://www.mojohaus.org/exec-maven-plugin/) configured in your `pom.xml`, you can run:
```bash
mvn clean compile exec:java
```
This will launch the JavaFX application directly from Maven.

### 2. Using the JavaFX Maven Plugin (Optional)
If you add the **JavaFX Maven Plugin** to your `pom.xml`, you could run:
```bash
mvn javafx:run
```
*(Not currently shown in your `pom.xml`, but commonly used.)*

### 3. Using the Packaged JAR
If your build is successful, Maven will create a JAR in the `target` folder:
```
target/BlackJackGUI-0.0.1-SNAPSHOT.jar
```
You can run it with:
```bash
java -jar target/BlackJackGUI-0.0.1-SNAPSHOT.jar
```
*(Ensure JavaFX modules are on the module path if needed.)*

## Gameplay
1. **Objective**: Get as close to 21 without going over.  
2. **Initial Deal**: Player and dealer both get two cards.  
3. **Player Turn**:  
   - **Hit**: Draw an additional card.  
   - **Stand**: End your turn.  
   - (Other actions like Double Down or Split if implemented.)  
4. **Dealer Turn**: Dealer draws until reaching at least 17.  
5. **Win Condition**:  
   - If you exceed 21, you bust.  
   - If the dealer exceeds 21, the dealer busts.  
   - Otherwise, whoever is closest to 21 wins.  

## Project Structure
A typical structure might look like:
```
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
```

## Contributing
1. **Fork** the repo.  
2. Create a **feature branch**:  
   ```bash
   git checkout -b feature/my-cool-feature
   ```
3. Commit changes:  
   ```bash
   git commit -m "Add my cool feature"
   ```
4. Push to your branch:  
   ```bash
   git push origin feature/my-cool-feature
   ```
5. Open a **Pull Request** on GitHub.

## License
This project is licensed under the MIT License (or whichever you prefer). Make sure to include a LICENSE file in the repository to clarify usage terms.
