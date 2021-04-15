package Server.tictactoe;

public class TicTacToe {
  private enum ButtonState {
    Blank, X, O
  };

  private enum victoryState {
    Blank, Draw
  };

  private String victory;

  private int moveCount;
  private ButtonState[][] hash;

  public TicTacToe() {
    this.hash = new ButtonState[3][3];
  }

  public void Inicializar() {
    this.moveCount = 0;
    this.victory = victoryState.Blank.toString();
    for (int i = 0; i < hash.length; i++) {
      for (int j = 0; j < hash.length; j++) {
        hash[i][j] = ButtonState.Blank;
      }
    }
  }

  public void makeMove(int x, int y, ButtonState state) {
    if (this.hash[x][y] != ButtonState.Blank)
      return;
    this.hash[x][y] = state;
    this.moveCount++;

    this.checkVictory(x, y, state);
  }

  public void checkVictory(int x, int y, ButtonState state) {
    // row
    for (int i = 0; i < this.hash.length; i++) {
      if (this.hash[i][y] != state)
        break;
      if (i == this.hash.length - 1) {
        this.victory = state.toString();
      }
    }
    // col
    for (int i = 0; i < this.hash.length; i++) {
      if (this.hash[x][i] != state)
        break;
      if (i == this.hash.length - 1) {
        this.victory = state.toString();
      }
    }

    if (x == y) {
      // we're on a diagonal
      for (int i = 0; i < this.hash.length; i++) {
        if (this.hash[i][i] != state)
          break;
        if (i == this.hash.length - 1) {
          this.victory = state.toString();
        }
      }
    }

    // check anti diag
    if (x + y == this.hash.length - 1) {
      for (int i = 0; i < this.hash.length; i++) {
        if (this.hash[i][(this.hash.length - 1) - i] != state)
          break;
        if (i == this.hash.length - 1) {
          this.victory = state.toString();
        }
      }
    }

    // check draw
    if (moveCount == (Math.pow(this.hash.length, 2) - 1)) {
      this.victory = victoryState.Draw.toString();
    }
  }

  public String getVictory() {
    return this.victory;
  }

  public ButtonState[][] getHash() {
    return this.hash;
  }
}
