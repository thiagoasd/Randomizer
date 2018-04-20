package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
					dia = somaMinutos(dia, i + 1, 30);
				}
			}
		}
		return dia;
	}

	private static ArrayList<Batida> somaMinutos(ArrayList<Batida> dia, int index, int minutos) {
		for (int i = index; i < dia.size(); i++) {
			Batida bat = dia.get(i);
			bat.setBatida2(bat.batida2.plusMinutes(minutos));
			dia.set(i, bat);
		}
		return dia;
	}

	public static Batida ordemErrada(ArrayList<Batida> dia) {
		LocalDateTime bat1 = dia.get(0).getBatida2();
		Batida erro = null;
		for (int i = 1; i < dia.size(); i++) {
			LocalDateTime bat2 = dia.get(i).getBatida2();
			if (bat1.compareTo(bat2) >= 0) {
				erro = dia.get(i);
				break;
			}
			bat1 = bat2;
		}

		return erro;
	}

	public static boolean saidaEntrada(ArrayList<Batida> dia) {
		return ((dia.size() % 2) == 0);

	}

	public static LocalDateTime arrumaDirecao(LocalDateTime entrada, LocalDateTime saida) {
		LocalDateTime saidaMax = entrada.plusHours(4);
		LocalDateTime saidaNova = saida.minusMinutes(5);
		saldo -= 5;
		int ajuste = 0;
		while (saidaNova.compareTo(saidaMax) >= 0) {
			saldo -= 5;
			ajuste -= 5;
			saidaNova = saidaNova.minusMinutes(5);
		}
		if (ajuste < -90)
			System.out.println("Ajuste grande: " + entrada.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm")));
		return saidaNova;
	}

	public static void fechamento(ArrayList<Batida> dia) {

		boolean quatroManha = false;
		LocalDateTime entrada = dia.get(0).getBatida2();
		LocalDateTime saida = dia.get(1).getBatida2();

		if (dia.get(0).getBatida2().getHour() == 4)
			quatroManha = true;

		if (quatroManha) {
			if (dia.size() > 2) {
				LocalDateTime entrada2 = dia.get(2).getBatida2();
				if (saida.plusHours(1).compareTo(entrada2) < 0) {
					entrada = entrada.plusHours(1);
					dia.get(0).setBatida2(entrada);
					saida = saida.plusHours(1);
					dia.get(1).setBatida2(saida);
				} else {
					dia = somaMinutos(dia, 0, 60);
				}
			} else {
				entrada = entrada.plusHours(1);
				dia.get(0).setBatida2(entrada);
				saida = saida.plusHours(1);
				dia.get(1).setBatida2(saida);
			}
		}
	}
}
