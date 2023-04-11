package ar.utn.ap.Pronosticos;

import java.util.ArrayList;
import java.util.Collection;

public class Main {

	public static void main(String[] args) {
		
		// Leer Partidos
		
		LectorDeArchivos partidosCSV = new LectorDeArchivos("src/test/resources/partidos.csv"); // Buscar archivo de partidos
		Collection<Partido> partidos = new ArrayList<Partido>(); // Crear coleccion de partidos
		
		for (String partidoString : partidosCSV.getDatos()) { // Leer partidos y llenar la coleccion
			String[] valores = partidoString.split(",");
			Partido partido = new Partido(valores[0],Integer.parseInt(valores[1]),Integer.parseInt(valores[3]),Integer.parseInt(valores[4]));
			partidos.add(partido);
		}
		
		// Leer Pronosticos y crear Usuarios
		LectorDeArchivos pronosticosCSV = new LectorDeArchivos("src/test/resources/pronostico.csv"); // Buscar archivo de usuarios
		Collection<Usuario> usuarios = new ArrayList<Usuario>(); // Crear coleccion de usuarios
		
		PRONOSTICO_LOOP: for (String pronosticoCSV : pronosticosCSV.getDatos()) { // Recorrer archivo de pronosticos
			String[] valores = pronosticoCSV.split(",");
			
			Pronostico pronostico = new Pronostico(valores[0],Integer.parseInt(valores[1]),valores[2]); // Crear pronosticos
			if (usuarios.size() == 0) { // En la primera vuelta tengo que crear un nuevo usuario y asignarle ese pronostico
				Collection<Pronostico> pronosticos = new ArrayList<Pronostico>();
				pronosticos.add(pronostico);
				Usuario usuario = new Usuario(valores[0],pronosticos);
				usuarios.add(usuario);
				continue; // Salto al siguiente pronostico
			}
			
			// Si llego aca es xq no es la primera vuelta, entonces me  
			// fijo si el pronostico es de un usuario existente
			for (Usuario usuario : usuarios) { // Si es así asigno el pronostico a ese usuario
				if (pronostico.getNombre().equals(usuario.getNombre())) {
					usuario.setPronosticos(pronostico);
					continue PRONOSTICO_LOOP; // Salto al siguiente pronostico
				}
			}
			
			// Si llego acá es xq el pronostico no corresponde 
			// a ningun usuario existente
			// Entonces creo el usuario y le asigno el pronostico
			Collection<Pronostico> pronosticos = new ArrayList<Pronostico>();
			pronosticos.add(pronostico);
			Usuario usuario = new Usuario(valores[0],pronosticos);
			usuarios.add(usuario);
			
		}
		
		for (Usuario usuario : usuarios ) { // Recorrer usuarios
			for (Pronostico pronostico : usuario.getPronosticos()) { // Recorrer pronosticos del usuario
				for (Partido partido : partidos) { // Recorrer partidos
					if (partido.getIndex() == pronostico.getPartido()) { // Encontrar el pronostico correspondiente al partido
						if (partido.getResultado().equals(pronostico.getResultado())) { // Comparar resultado y la prediccion
							usuario.increaseAciertos();
							usuario.setPuntos(1); // Si acertó la predicción, sumar 1 punto al usuario
						}
					}
				}
			}
		}
		
		for (Usuario usuario : usuarios) {
			System.out.println("El puntaje de " + usuario.getNombre() + " es: " + usuario.getPuntos());
			System.out.println("Habiendo acertado " + usuario.getAciertos() + " pronóstico(s).");
			System.out.println();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//int puntos = 0;
		//for (String pronosticoCSV : pronosticosCSV.getDatos()) {
		//	String[] valores = pronosticoCSV.split(",");
		//	for (Partido partido : partidos) {
		//		if (partido.getIndex() == Integer.parseInt(valores[0])) {
		//			if (partido.getResultado().equals(valores[1])) {
		//				puntos += 1;
		//			}
		//		}
		//	}
		//}
		//System.out.println("El puntaje del usuario es: " + puntos);
	}

}
