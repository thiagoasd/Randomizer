package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import util.Afder;
import util.Batida;

public class Prog {

	public static void main(String[] args) {
		File AFD = new File(args[0]);
		String linha;
		ArrayList<Batida> batidasDia = new ArrayList<Batida>(10);
		boolean justificativa = false;
		boolean erroJustificativa = false;

		try {
			FileReader fileReader = new FileReader(AFD);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWriter = new FileWriter(args[1]);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			int count = 0;
			int sequencial = 1;

			while ((linha = bufferedReader.readLine()) != null) {
				// Se acabou o dia
				if (linha.compareTo("*") == 0) {

					// Se tem justificativa
					if (justificativa) {
						// Se tem horario no meio das justificativas
						if (erroJustificativa) {
							System.out.println("Erro de justificativa no dia: " + batidasDia.get(0));
						}

						// Se � um dia normal
					} else {
						if (count % 2 != 0) {
							System.out.println("Horario sem saida no dia: " + batidasDia.get(0).toString());
						}

						for (Batida batida : batidasDia) {
							batida.randomize();
							String batidaAFD = Afder.createAFD(sequencial, batida.getDataHora2(), batida.getPis());
							printWriter.println(batidaAFD);
							sequencial++;
						}
						printWriter.flush();

					}

					count = 0;
					justificativa = erroJustificativa = false;
					batidasDia = new ArrayList<Batida>(10);

					// Se ainda tem batida nesse dia
				} else {
					// Se jogar o erro, provavelmente � uma justificativa
					// Se tiver um horario no meio de uma justificativa, seta a flag
					try {
						Batida batida = new Batida(linha);
						erroJustificativa = justificativa;
						batidasDia.add(batida);
						count++;
					} catch (NumberFormatException e) {
						justificativa = true;
					}

				}
			}
			
			bufferedReader.close();
			printWriter.flush();
			printWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		System.out.println("fims");

	}

}
