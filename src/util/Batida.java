package util;
import java.time.*;

public class Batida {
	
	LocalDateTime batida;
	LocalDateTime batida2;
	
	public Batida(String linha) {
		this.batida = parser(linha);		
	}
	
	
	LocalDateTime parser(String linha) {
		int dia = Integer.parseInt(linha.substring(0, 2));
		int mes = Integer.parseInt(linha.substring(2, 4));
		int ano = Integer.parseInt(linha.substring(4, 8));
		int hora = Integer.parseInt(linha.substring(8, 10));
		int min = Integer.parseInt(linha.substring(10, 12));
		
		LocalDateTime bat = LocalDateTime.of(ano, mes, dia, hora, min, 0);
		return bat;		
	}
	
	
	
	

}
