class Main {
  public static void main(String[] args) {
    
    // New game object
    Game game = new Game();

    // Start the game
    game.startGame();

    // Continue the game while the game is not over
    while (!game.getGameOver()) {
      game.next();
    }

    // Print the final message
    game.endingMessage();

  }
}