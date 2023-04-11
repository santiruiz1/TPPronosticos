package ar.utn.ap.Pronosticos;

public class Pronostico {
	String nombre;
	int partido;
	String resultado;
	
	public Pronostico(String nombre, int partido, String resultado) {
		super();
		this.nombre = nombre;
		this.partido = partido;
		this.resultado = resultado;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPartido() {
		return partido;
	}

	public String getResultado() {
		return resultado;
	}
	
}
