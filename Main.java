class Main {
  public static void main(String[] args) {
    Game game = new Game();
    game.startGame();

    // Continue the game while the game is not over
    while (!game.getGameOver()) {
      game.next();
    }

    game.endingMessage();

  }
}