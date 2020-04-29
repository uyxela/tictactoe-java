// Class for the AI player
// The AI player uses the minimax algorithm to decide on the best next move

// Explain minimax
public class Player {

  int minimax(Board board, int depth, boolean isMax) {
    int score = checkBoard(board);

    // If the maximizer has won, return their score
    if (score == 10) {
      return score;
    }

    // If the minimzer has won, return their score
    if (score == -10) {
      return score;
    }

    // If their are no moves left, return 0
    if (board.isFull() == true) {
      return 0;
    }

    // If it is the maximizer's move
    if (isMax) {
      // Find the best move
      int best = 1000;

      // Visit all cells
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          // Check if cell is empty
          if (board.getCell(i, j) == ' ') {
            // Make the move
            board.nextMove(i, j, 'O');

            // Recursive minimax call to choose the max value
            best = Math.max(best, minimax(board, depth + 1, !isMax));

            // Undo the move
            board.nextMove(i, j, ' ');
          }
        }
      }
      return best;
    } else {
      // Find the best move
      int best = 1000;

      // Visit all cells
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          // Check if cell is empty
          if (board.getCell(i, j) == ' ') {
            // Make the move
            board.nextMove(i, j, 'X');

            // Recursive minimax call to choose the max value
            best = Math.min(best, minimax(board, depth + 1, !isMax));

            // Undo the move
            board.nextMove(i, j, ' ');
          }
        }
      }
      return best;
    }
  }

  int checkBoard(Board board) {
    // Check rows for victory or loss
    for (int row = 0; row < 3; row++) {
      if (board.getCell(row, 0) == board.getCell(row, 1) && board.getCell(row, 1) == board.getCell(row, 2)) {
        if (board.getCell(row, 0) == 'O')
          return +10;
        else if (board.getCell(row, 0) == 'X')
          return -10;
      }
    }
    // Check columns for victory or loss
    for (int col = 0; col < 3; col++) {
      if (board.getCell(0, col) == board.getCell(1, col) && board.getCell(1, col) == board.getCell(2, col)) {
        if (board.getCell(0, col) == 'O')
          return +10;

        else if (board.getCell(0, col) == 'X')
          return -10;
      }
    }
    // Check diagonals for victory or loss
    if (board.getCell(0, 0) == board.getCell(1, 1) && board.getCell(1, 1) == board.getCell(2, 2)) {
      if (board.getCell(0, 0) == 'O')
        return +10;
      else if (board.getCell(0, 0) == 'X')
        return -10;
    }
    if (board.getCell(0, 2) == board.getCell(1, 1) && board.getCell(1, 1) == board.getCell(2, 0)) {
      if (board.getCell(0, 2) == 'O')
        return +10;
      else if (board.getCell(0, 2) == 'X')
        return -10;
    }
    // if no wins possible
    return 0;
  }

  public Move nextMove(Board board) {
    int bestVal = -1000;
    Move bestMove = new Move();
    bestMove.row = -1;
    bestMove.column = -1;

    // Traverse all cells, evaluate minimax function
    // for all empty cells. And return the cell
    // with optimal value.
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        // Check if cell is empty
        if (board.getCell(i, j) == ' ') {

          // Make the move
          board.nextMove(i, j, 'O');

          // Evaluate the score for this move
          int moveVal = minimax(board, 0, false);

          // Undo the move
          board.nextMove(i, j, ' ');
          ;

          // Update the bestMove if the score is higher
          if (moveVal > bestVal) {
            bestMove.row = i;
            bestMove.column = j;
            bestVal = moveVal;
          }
        }
      }
    }
    return bestMove;
  }

}