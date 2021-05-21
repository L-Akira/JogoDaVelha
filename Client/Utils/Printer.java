package Client.Utils;

public class Printer {
	public Printer() {
	}

	public void makePlay() {
		System.out.println("Selecione qual jogada quer fazer:");
		System.out.println("|");
		System.out.println("V");
		this.plays();
	}

	public void welcome() {
		System.out.println("Bem Vindo: Selecione o tipo de jogo");
		System.out.println("1:PvP");
		System.out.println("2:PvE");
		// System.out.print("Plays: ");
		// this.plays();
	}

	public void aftermatch(int[] results) {
		System.out.println("vitorias: " + results[0]);
		System.out.println("derrotas: " + results[1]);
		System.out.println("empates: " + results[2]);
	}

	private void plays() {
		System.out.println("-- 1:PAPEL | 2:PEDRA | 3:TESOURA --");
	}
}
