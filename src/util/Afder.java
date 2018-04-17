package util;


public class Afder {
	
	public static String createAFD(int NSR, String dataHora, String PIS) {
		String AFD = String.format("%09d3%s%s", NSR, dataHora, PIS);

		return AFD;
	}
}

