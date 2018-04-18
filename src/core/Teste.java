package core;

import java.time.LocalDateTime;
import util.Checker;

public class Teste {

	public static void main(String[] args) {
		LocalDateTime entrada = LocalDateTime.of(2016, 01, 01, 4, 0);
		LocalDateTime saida = LocalDateTime.of(2016, 01, 01, 8, 30);
		saida = Checker.arrumaDirecao(entrada, saida);
		System.out.println(entrada.plusHours(4).compareTo(saida));
	}

}