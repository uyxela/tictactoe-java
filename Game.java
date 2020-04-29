import java.util.*;

// Class to manage game logic
public class Game {

  // Variables
  // Name of first player
  private String playerOne = "";

  // Name of second player
  private String playerTwo = "";

  // Type of game (either "single player game" or "two player game")
  private String gameType = "";

  // True if game is over, false if game is ongoing
  private boolean gameOver = false;

  // String to hold name of the winner
  private String winner = "";

  // Scanner object used in the class
  private Scanner sc = new Scanner(System.in);

  // Board object to manage board started
  Board board = new Board();

  // AI player object
  Player AI = new Player();

  // Methods
  // Method called when game is started
  public void startGame() {
    System.out.println("Welcome to the game!");

    // Keep asking for input until provided a value
    while (playerOne.isEmpty()) {
      System.out.println("Please enter the name of the first player:");
      playerOne = sc.nextLine();
    }

    // Check to make sure that player names are not the same
    do {
      System.out.println("Please enter the name of the second player (leave blank to play against AI player):");
      playerTwo = sc.nextLine();
    } while (playerTwo.equals(playerOne));

    // Check if playerTwo is not null
    if (!playerTwo.isEmpty()) {
      this.setPlayers(playerOne, playerTwo);
    } else {
      this.setPlayers(playerOne);
    }

    // Print message 
    System.out.println("Starting a new " + gameType);
    System.out.println(board);
  }

  // Overloaded method to set one player name and game type 
  private void setPlayers(String playerOne) {
    this.playerOne = playerOne;
    this.playerTwo = "AI";
    gameType = "single player game";
  }

  // Overloaded method to set two player names and game type
  private void setPlayers(String playerOne, String playerTwo) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    gameType = "two player game";
  }

  // Method to return whether or not the game is over
  public boolean getGameOver() {
    // First, check to see if a player has won the game
    // Check rows for victory or loss
    for (int row = 0; row < 3; row++) {
      if (board.getCell(row, 0) == board.getCell(row, 1) && board.getCell(row, 1) == board.getCell(row, 2)) {
        if (board.getCell(row, 0) == 'O')
          if (gameType.equals("single player game")) {
            winner = "AI";
            gameOver = true;
          } else {
            winner = playerTwo;
            gameOver = true;
          }
        else if (board.getCell(row, 0) == 'X') {
          winner = playerOne;
          gameOver = true;
        }
      }
    }
    // Check columns for victory or loss
    for (int col = 0; col < 3; col++) {
      if (board.getCell(0, col) == board.getCell(1, col) && board.getCell(1, col) == board.getCell(2, col)) {
        if (board.getCell(0, col) == 'O')
          if (gameType.equals("single player game")) {
            winner = "AI";
            gameOver = true;
          } else {
            winner = playerTwo;
            gameOver = true;
          }
        else if (board.getCell(0, col) == 'X') {
          winner = playerOne;
          gameOver = true;
        }
      }
    }
    // Check diagonals for victory or loss
    if (board.getCell(0, 0) == board.getCell(1, 1) && board.getCell(1, 1) == board.getCell(2, 2)) {
      if (board.getCell(0, 0) == 'O')
        if (gameType.equals("single player game")) {
          winner = "AI";
          gameOver = true;
        } else {
          winner = playerTwo;
          gameOver = true;
        }
      else if (board.getCell(0, 0) == 'X') {
        winner = playerOne;
        gameOver = true;
      }
    }
    if (board.getCell(0, 2) == board.getCell(1, 1) && board.getCell(1, 1) == board.getCell(2, 0)) {
      if (board.getCell(0, 2) == 'O')
        if (gameType.equals("single player game")) {
          winner = "AI";
          gameOver = true;
        } else {
          winner = playerTwo;
          gameOver = true;
        }
      else if (board.getCell(0, 2) == 'X') {
        winner = playerOne;
        gameOver = true;
      }
    }

    // Check if the board is full
    if (board.isFull()) {
      gameOver = true;
    }

    // Return whether or not the game is over
    return gameOver;
  }

  // Method to handle logic for next move of the game
  public void next() {

    int row, column;

    // Loop for player one's input until valid
    do {
      System.out.println(playerOne + " please enter your move:");
      System.out.println("Enter the row number of your move:");
      row = sc.nextInt() - 1;
      System.out.println("Enter the column number of your move:");
      column = sc.nextInt() - 1;
    } while (!board.valid(row, column) || board.getCell(row, column) != ' ');

    // Print out player one's move
    board.nextMove(row, column, 'X');
    System.out.println(board);

    // Check if player one has won, if so, end the function
    if (this.getGameOver()) {
      return;
    }

    // Check whether the game is single player or two player
    if (gameType.equals("single player game")) {
      // Get the next move from the AI
      Move AIMove = AI.nextMove(board);
      board.nextMove(AIMove.row, AIMove.column, 'O');

      // Print out the AI's move
      System.out.println("The AI's move:");
      System.out.println(board);
    } else {
      // Loop for player two's input until valid 
      do {
        System.out.println(playerTwo + " please enter your move:");
        System.out.println("Enter the row number of your move:");
        row = sc.nextInt() - 1;
        System.out.println("Enter the column number of your move:");
        column = sc.nextInt() - 1;
      } while (!board.valid(row, column) || board.getCell(row, column) != ' ');

      // Print out player two's move
      board.nextMove(row, column, 'O');
      System.out.println(board);
    }

  }

  // Method to return the name of the winner
  public String getWinner() {
    return winner;
  }

  // Method to print ending message
  public void endingMessage() {
    // If no winner has been assigned, the game has ended in a tie
    if (winner.isEmpty()) {
      System.out.println("It's a tie!");
      System.out.println("You win this small trophy:\n" +
                         "  .__.\n" +
                         " (|  |)\n" + 
                         "  (  )\n" + 
                         "  _)(_");
      System.out.println("Thanks for playing, better luck next time!");
    } 
    // Print this message if the AI has won
    else if (winner.equals("AI")) {
      System.out.println("You lost!");
      System.out.println("Unfortuantely, you don't win anything :(");
      System.out.println("Thanks for playing, better luck next time!");
    } 
    // Print this message for the winning player
    else {
      System.out.println("Congratulations " + this.getWinner() + "!");
      System.out.println("You win this trophy:\n" + 
                         "             ___________\n" + 
                         "            '._==_==_=_.'\n" + 
                         "            .-\\:      /-.\n" + 
                         "           | (|:.     |) |\n" + 
                         "            '-|:.     |-'\n" + 
                         "              \\::.    /\n" + 
                         "               '::. .'\n" + 
                         "                 ) (\n" + 
                         "               _.' '._\n" + 
                         "              \'\'\"\"\"\"\"\"\"\'");
      System.out.println("Thanks for playing!");
    }
  }
}