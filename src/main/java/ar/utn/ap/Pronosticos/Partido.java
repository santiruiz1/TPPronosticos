package ar.utn.ap.Pronosticos;

public class Partido {
	int index;
	int goles1;
	int goles2;
	int ronda;
	String eq1;
	String eq2;

	public Partido(int index, int ronda, String eq1, String eq2, int goles1, int goles2) {
		super();
		this.ronda = ronda;
		this.index = index;
		this.goles1 = goles1;
		this.goles2 = goles2;
		this.eq1 = eq1;
		this.eq2 = eq2;
		
	}
	
	public int getIndex() {
		return index;
	}
	public int getRonda() {
		return ronda;
	}
	
	public int getGoles1() {
		return goles1;
	}
	public int getGoles2() {
		return goles2;
	}

	
	public String getResultado() {
		String resultado = "error";
		if (goles1 > goles2) {
			resultado = "gana1";
		}
		if (goles1 == goles2) {
			resultado = "empate";
		}
		if (goles1 < goles2) {
			resultado = "gana2";
		}
		return resultado;
	}
	
}
