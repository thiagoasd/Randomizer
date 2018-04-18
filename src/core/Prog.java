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
import util.Checker;

public class Prog {

	public static void main(String[] args) {
		File AFD = new File(args[0]);
		String linha;
		String errosJustificativa = "";
		String errosSemSaida = "";
		boolean justificativa = false;
		boolean erroJustificativa = false;
		int saldo = 0;
		ArrayList<Batida> batidasDia = new ArrayList<Batida>(10);
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
							errosJustificativa += "Erro de justificativa no dia: " + batidasDia.get(0)+ "\n";
						}

						// Se é um dia normal
					} else {
						if (count % 2 != 0) {
							errosSemSaida += "Horario sem saida no dia: " + batidasDia.get(0).toString() +"\n";
						}

						for (Batida batida : batidasDia) {
							saldo += batida.randomize();
						}
						
						Checker.diracaoContinua(batidasDia);
						Checker.descanso(batidasDia);
						
						for(Batida batida: batidasDia) {
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
					// Se jogar o erro, provavelmente é uma justificativa
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
		
		
		saldo += Checker.saldo;
		System.out.println(errosJustificativa);
		System.out.println(errosSemSaida);
		System.out.println("Saldo de horas: " + (saldo / 60));
		System.out.println("Minutos: " + (saldo%60));
		System.out.println("fim");

	}

}
