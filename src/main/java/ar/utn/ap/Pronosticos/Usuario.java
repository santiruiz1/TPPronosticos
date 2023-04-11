package ar.utn.ap.Pronosticos;

import java.util.Collection;

public class Usuario {
	String nombre;
	int puntos;
	Collection<Pronostico> pronosticos;
	int aciertos;
	
	public Usuario(String nombre, Collection<Pronostico> pronosticos) {
		super();
		this.nombre = nombre;
		this.puntos = 0;
		this.pronosticos = pronosticos;
		this.aciertos = 0;
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
	public void increaseAciertos() {
		this.aciertos = this.aciertos + 1;
	}

	public Collection<Pronostico> getPronosticos() {
		return pronosticos;
	}
	public void setPronosticos(Pronostico pronostico) {
		pronosticos.add(pronostico);
	}
	
	
}
