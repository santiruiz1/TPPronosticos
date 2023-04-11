package ar.utn.ap.Pronosticos;

public class Partido {
	int index;
	int goles1;
	int goles2;
	String ronda;

	public Partido(String ronda, int index, int goles1, int goles2) {
		super();
		this.ronda = ronda;
		this.index = index;
		this.goles1 = goles1;
		this.goles2 = goles2;
		
	}
	
	public int getIndex() {
		return index;
	}
	public String getRonda() {
		return ronda;
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
