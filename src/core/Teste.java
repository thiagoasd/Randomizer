package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;

import util.Batida;
import util.Checker;

public class Teste {

	public static void main(String[] args) {
		LocalDateTime entrada = LocalDateTime.of(2016, 01, 01, 5, 0);
		LocalDateTime saida = LocalDateTime.of(2016, 01, 01, 8, 45);

		saida = Checker.arrumaDirecao(entrada, saida);

		File AFD = new File(args[0]);
		String linha;
		ArrayList<Batida> batidasDia = new ArrayList<Batida>(10);
		try {
			FileReader fileReader = new FileReader(AFD);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((linha = bufferedReader.readLine()) != null) {
				// Se acabou o dia
				if (linha.compareTo("*") == 0) {

					if (batidasDia.size() > 0) {

						Batida bat = Checker.ordemErrada(batidasDia);
						if (bat != null) {
							System.out.println("ordem errada: " + bat);
						}

						if (!Checker.saidaEntrada(batidasDia)) {
							System.out.println("quantidade errada: " + batidasDia.get(0));
						}
					}
					batidasDia = new ArrayList<Batida>(10);

				} else {
					// Se jogar o erro, provavelmente é uma justificativa
					// Se tiver um horario no meio de uma justificativa, seta a flag
					try {
						Batida batida = new Batida(linha);
						batidasDia.add(batida);

					} catch (NumberFormatException e) {

					}

				}

			}

			bufferedReader.close();

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

}
