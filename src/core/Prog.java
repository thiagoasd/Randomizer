package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.util.ArrayList;

import util.Batida;

public class Prog {

	public static void main(String[] args) {
		File AFD = new File(args[0]);
		String linha;
		Batida[] batidasDia = new Batida[10];
		try {
			FileReader fileReader = new FileReader(AFD);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int count= 0;
			while ((linha = bufferedReader.readLine()) != null) {
				if (linha.compareTo("*") == 0) {
					//Tratar dia

				} else {
					Batida batida = new Batida(linha);
					batidasDia[count] = batida;
					count++;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
