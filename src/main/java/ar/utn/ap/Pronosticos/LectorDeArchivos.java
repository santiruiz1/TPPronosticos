package ar.utn.ap.Pronosticos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LectorDeArchivos {
	private String url;
	private List<String> datos;
	
	
	public LectorDeArchivos(String url) {
		super();
		this.url = url;
		this.datos = setDatos();
	}


	public List<String> getDatos() {
		return datos;
	}


	public List<String> setDatos() {
		Path archivo = Paths.get(url);
		List<String> lineas = null;
		try {
			lineas = Files.readAllLines(archivo);
		} catch (IOException e) {
			System.out.println("No se pudo leer el archivo");
		}
		return lineas;
	}
	
	
}
