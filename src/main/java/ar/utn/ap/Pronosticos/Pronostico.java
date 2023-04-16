package ar.utn.ap.Pronosticos;

public class Pronostico {
	int id;
	int user;
	int partido;
	String resultado;
	int goles1;
	int goles2;
	
	public Pronostico(int id, int user, int partido, String resultado, int goles1, int goles2) {
		super();
		this.id = id;
		this.user = user;
		this.partido = partido;
		this.resultado = resultado;
		this.goles1 = goles1;
		this.goles2 = goles2;
	}

	public int getUser() {
		return user;
	}

	public int getPartido() {
		return partido;
	}

	public String getResultado() {
		return resultado;
	}
	
}
