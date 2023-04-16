package ar.utn.ap.Pronosticos;

public class Usuario {
	int id;
	String nombre;
	int puntos;
	int aciertos;
	
	public Usuario(int id, String nombre,int puntos, int aciertos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
		this.aciertos = aciertos;
	}
	
	public int getId() {
		return id;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = this.puntos + puntos;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getAciertos() {
		return aciertos;
	}
	public void increaseAciertos(int puntos) {
		this.aciertos = this.aciertos + puntos;
	}
	
	
}
