package util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Batida {

	LocalDateTime batida;
	LocalDateTime batida2;
	String pis;

	public Batida(String linha) throws NumberFormatException {
		this.pis = "";
		this.batida = parser(linha);
	}

	private LocalDateTime parser(String linha) throws NumberFormatException {

		int dia = Integer.parseInt(linha.substring(0, 2));
		int mes = Integer.parseInt(linha.substring(2, 4));
		int ano = Integer.parseInt(linha.substring(4, 8));
		int hora = Integer.parseInt(linha.substring(8, 10));
		int min = Integer.parseInt(linha.substring(10, 12));
		this.pis = linha.substring(12);

		LocalDateTime bat = LocalDateTime.of(ano, mes, dia, hora, min, 0);
		return bat;
	}

	public void randomize() {

		// Se batida exata
		if (batida.getMinute() % 5 == 0) {
			Random random = new Random();
			int rand = random.nextInt(2);
			if (rand == 0) {
				this.batida2 = this.batida.plusMinutes(random.nextInt(5));
			} else {
				this.batida2 = this.batida.minusMinutes(random.nextInt(5));
			}

			// Se não, continua a mesma batida
		} else {
			this.batida2 = this.batida;

		}

	}

	public LocalDateTime getBatida() {
		return batida;
	}

	public void setBatida(LocalDateTime batida) {
		this.batida = batida;
	}

	public LocalDateTime getBatida2() {
		return batida2;
	}

	public String getDataHora2() {

		return this.batida2.format(DateTimeFormatter.ofPattern("ddMMyyyyHHmm"));
	}

	public void setBatida2(LocalDateTime batida2) {
		this.batida2 = batida2;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	@Override
	public String toString() {
		return batida.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm"));
	}

}
