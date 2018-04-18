package util;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Checker {
	
	public static int saldo;
	
	public static void diracaoContinua(ArrayList<Batida> dia) {

		if (dia.size() % 2 == 0) {
			for (int i = 0; i < dia.size(); i = i + 2) {
				LocalDateTime entrada = dia.get(i).getBatida2();
				LocalDateTime saida = dia.get(i + 1).getBatida2();

				LocalDateTime aux = entrada.plusHours(4);
				if (aux.compareTo(saida) < 1) {
					
					saida = arrumaDirecao(entrada, saida);
					Batida bat = dia.get(i + 1);
					bat.setBatida2(saida);
					dia.set(i + 1, bat);
				}

			}
		}
	}

	public static ArrayList<Batida> descanso(ArrayList<Batida> dia) {
		if (dia.size() % 2 == 0) {
			for (int i = 1; i < dia.size() - 1; i = i + 2) {
				LocalDateTime saida = dia.get(i).getBatida2();
				LocalDateTime entrada = dia.get(i + 1).getBatida2();
				if (saida.plusMinutes(30).compareTo(entrada) > 0) {
					dia = soma30(dia, i + 1);
				}
			}
		}
		return dia;
	}

	private static ArrayList<Batida> soma30(ArrayList<Batida> dia, int index) {
		for (int i = index; i < dia.size(); i++) {
			Batida bat = dia.get(i);
			bat.setBatida2(bat.batida2.plusMinutes(30));
			dia.set(i, bat);
		}
		return dia;
	}

	public static LocalDateTime arrumaDirecao(LocalDateTime entrada, LocalDateTime saida) {
		LocalDateTime saidaMax = entrada.plusHours(4);
		LocalDateTime saidaNova = saida.minusMinutes(5);
		saldo -= 5;
		while(saidaNova.compareTo(saidaMax) >= 0)
		{
			saldo -=5;
			saidaNova = saidaNova.minusMinutes(5);
		}
			
		return saidaNova;
	}
}
