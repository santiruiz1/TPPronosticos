package ar.utn.ap.Pronosticos;

import java.util.ArrayList;
import java.util.Collection;

public class ResultadoFinal {
	private String resultado;
	private Collection<Usuario> usuarios;
	private int maxPts;
	private Collection<Usuario> ganadores;
	
	
	public ResultadoFinal(Collection<Usuario> usuarios) {
		super();
		this.usuarios = usuarios;
		this.maxPts = 0;
		this.resultado = "";
		this.ganadores = new ArrayList<Usuario>();
	}


	public String getResultado() {
		return resultado;
	}
	public int getMaxPts() {
		return this.maxPts;
	}
	public Collection<Usuario> getGanadores() {
		return this.ganadores;
	}


	public void setResultado() {
		if (this.maxPts > 0) {
        	if (this.ganadores.size() == 1) {
        		for (Usuario ganador : this.ganadores) {
        			this.resultado = ganador.nombre;
       			}
        	}
        	if (this.ganadores.size() == 2) {
       			for (Usuario ganador : this.ganadores) {
       				this.resultado = this.resultado.concat(ganador.nombre + " | ");
       			}
        	}
        	if (ganadores.size() > 2) {
        		this.resultado = "Hay más de dos personas con el máximo puntaje, no hay ganadores.";
        	}
        } else {
        	this.resultado = "Nadie acertó una prediccíon, no hay ganadores.";
        }
	}
	
	public void setMaxPts() {
		for (Usuario usuario : this.usuarios) {
        	System.out.println("El puntaje y aciertos FINALES de " + usuario.getNombre() + " fueron: " + usuario.puntos + " y " + usuario.aciertos);
        	
        	if (usuario.puntos > this.maxPts) {
        		this.maxPts = usuario.puntos;
        	}
        }
	}
	
	public void setGanadores() {
		for (Usuario usuario : this.usuarios) {
        	if (usuario.puntos == this.maxPts) {
        		this.ganadores.add(usuario);
        	}
        }
	}
	
	
}
