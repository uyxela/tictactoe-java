// Board class
public class Board {

  // Variables
  // 2D Array to store board state
  // Initialize array with spaces
  private char[][] boardState = new char[][] { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

  // Method to get a board cell
  public char getCell(int row, int column) {
    return boardState[row][column];
  }

  // Method to change board value based on player move
  public void nextMove(int row, int column, char player) {

    // Update board with player input
    boardState[row][column] = player;
  }

  // Board toString() method
  public String toString() {
    return ("   1 2 3 \n" + "  -------\n" + "1 |" + boardState[0][0] + "|" + boardState[0][1] + "|" + boardState[0][2]
        + "|\n" + "  -------\n" + "2 |" + boardState[1][0] + "|" + boardState[1][1] + "|" + boardState[1][2] + "|\n"
        + "  -------\n" + "3 |" + boardState[2][0] + "|" + boardState[2][1] + "|" + boardState[2][2] + "|\n"
        + "  -------\n");
  }

  // Method to return the board state
  public char[][] getBoard() {
    return boardState;
  }

  // Method to check if the board is full
  public boolean isFull() {
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        if (boardState[i][j] == ' ') {
          return false;
        }
      }
    }
    return true;
  }

  // Method to check if a row and column are valid
  public boolean valid(int row, int column) {
    if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
      return true;
    }
    System.out.println("Please enter a valid input!");
    return false;
  }
}