package Server.Jokempo;

public class Jokenpo {
  private int player1;
  private int player2;
  private int victory;

  public Jokenpo() {
    this.victory = 0;
    this.player1 = 0;
    this.player2 = 0;
  }

  // vitoria
  // vitoria para player1
  // victory = 1
  // vitoria para player2
  // victory = 2
  // victory = 3 empate

  // variavel players
  // papel = 1
  // pedra = 2
  // tesoura = 3

  public void setPlayer1(int player1) {
    this.player1 = player1;
  }

  public void setPlayer2(int player2) {
    this.player2 = player2;
  }

  public int play() {
    if (this.player1 == this.player2) {
      this.victory = 3;
    } else if (this.player1 - this.player2 == -1 || this.player1 - this.player2 == 2) {
      this.victory = 1;
    } else {
      this.victory = 2;
    }
  }
}
