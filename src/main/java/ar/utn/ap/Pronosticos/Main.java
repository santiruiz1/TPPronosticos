package ar.utn.ap.Pronosticos;

import java.util.ArrayList;
import java.util.Collection;

import java.sql.*;

import static ar.utn.ap.Pronosticos.Conexion.DB_URL;
import static ar.utn.ap.Pronosticos.Conexion.USER;
import static ar.utn.ap.Pronosticos.Conexion.PASS;

public class Main {

	public static void main(String[] args) {
		
		Connection conexion = null;
        Statement consulta = null;
        
        try {
            // Abrir la conexión
            System.out.println("conectando a la base de datos...");

            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // Obtener Settings
            consulta = conexion.createStatement();
            String sql;
            sql = "SELECT * FROM tppronosticos.settings";
            ResultSet resultado = consulta.executeQuery(sql);
            int pts_partido = 0;
            int pts_acierto = 0;
            int rondas = 0;
            
            while (resultado.next()) {
            	pts_partido = resultado.getInt("pts_partido");
            	pts_acierto = resultado.getInt("pts_acierto");
            	rondas = resultado.getInt("rondas");
            }
            
            resultado.close();
            consulta.close();
            System.out.println("Settings guardados");

            // Ejecutar consulta partidos
            
            Collection<Partido> partidos = new ArrayList<Partido>(); // Crear coleccion de partidos
            System.out.println("Obteniendo partidos...");
            consulta = conexion.createStatement();
            sql = "SELECT * FROM tppronosticos.partidos";

            //En la variable resultado obtendremos las distintas filas que nos devolvió la base
            resultado = consulta.executeQuery(sql);
            
            // Obtener las distintas filas de la consulta
            while (resultado.next()) {
            	//Obtener el valor de cada columna
                int id = resultado.getInt("id");
                String eq1 = resultado.getString("eq1");
                String eq2 = resultado.getString("eq2");
                int goles1 = resultado.getInt("goles1");
                int goles2 = resultado.getInt("goles2");
                int ronda = resultado.getInt("ronda");
                
                //Guardar partidos
    			Partido partido = new Partido(id,ronda,eq1,eq2,goles1,goles2);
    			partidos.add(partido);
            }
            // Cierro consulta actual
            resultado.close();
            consulta.close();
            System.out.println("Partidos guardados");
		
            // Leer Pronosticos
            Collection<Pronostico> pronosticos = new ArrayList<Pronostico>(); // Crear coleccion de pronosticos
            System.out.println("Obteniendo pronosticos...");
            consulta = conexion.createStatement();
            sql = "SELECT * FROM tppronosticos.pronosticos";

            //En la variable resultado obtendremos las distintas filas que nos devolvió la base
            resultado = consulta.executeQuery(sql);
            
            // Obtener las distintas filas de la consulta
            while (resultado.next()) {
            	
            	//Obtener el valor de cada columna
                int id = resultado.getInt("id");
                int user = resultado.getInt("user");
                int partido = resultado.getInt("partido");
                String prediccion = resultado.getString("resultado");
                int goles1 = resultado.getInt("goles1");
                int goles2 = resultado.getInt("goles2");
                
                //Guardar partidos
    			Pronostico pronostico = new Pronostico(id,user,partido,prediccion,goles1,goles2);
    			pronosticos.add(pronostico);
            }
            // Cierro consulta actual
            resultado.close();
            consulta.close();
            System.out.println("Pronosticos guardados");
            System.out.println();
            
            
            
            for (int i = 1; i <= rondas; i++) {
            	Collection<Usuario> usuarios = new ArrayList<Usuario>(); // Crear coleccion de pronosticos
            	System.out.println("RONDA " + i);
            	consulta = conexion.createStatement();
            	sql = "SELECT * FROM tppronosticos.users";

            	//En la variable resultado obtendremos las distintas filas que nos devolvió la base
            	resultado = consulta.executeQuery(sql);
            
            	// Obtener las distintas filas de la consulta
            	while (resultado.next()) {
            	
            		//Obtener el valor de cada columna
            		int id = resultado.getInt("id");
            		String nombre = resultado.getString("nombre");
            		int puntos = resultado.getInt("puntos");
            		int aciertos = resultado.getInt("aciertos");
                
            		//Guardar partidos
            		Usuario usuario = new Usuario(id,nombre,puntos,aciertos);
            		usuarios.add(usuario);
            	}
            	// Cierro consulta actual
            	resultado.close();
            	consulta.close();
            	
            	for (Usuario usuario : usuarios) {
            		int puntosRonda = 0;
            		int aciertosRonda = 0;
            		for (Pronostico pronostico : pronosticos) {
            			if (pronostico.getUser() == usuario.getId()) {
            				for (Partido partido : partidos) {
            					if (partido.getRonda() == i && partido.getIndex() == pronostico.getPartido()) {
            						if (partido.getResultado().equals(pronostico.getResultado())) {
            							puntosRonda += pts_partido;
            							aciertosRonda += 1;
            							if (partido.goles1 == pronostico.goles1) {
            								puntosRonda += pts_acierto;
            								aciertosRonda += 1;
            							}
            							if (partido.goles2 == pronostico.goles2) {
            								puntosRonda += pts_acierto;
            								aciertosRonda += 1;
            							}
            						}
            					}
            				}
            			}
            		}
            		System.out.println("El puntaje y aciertos de " + usuario.getNombre() + " fueron: " + puntosRonda + " y " + aciertosRonda);
            		int nuevoPuntaje = usuario.puntos + puntosRonda;
            		int nuevoAciertos = usuario.aciertos + aciertosRonda;
            		consulta = conexion.createStatement();
                	sql = "UPDATE tppronosticos.users SET puntos = " + nuevoPuntaje + ", aciertos = " + nuevoAciertos + " WHERE id = " + usuario.getId();

                	// Actualizamos los puntos del usuario en la db
                	consulta.executeUpdate(sql);
                	// Cierro consulta actual
                	consulta.close();
            		
            	}
            	
            	System.out.println("Terminó la ronda " + i);
            	System.out.println();
            }
            
            consulta = conexion.createStatement();
            sql = "SELECT * FROM tppronosticos.users";
            resultado = consulta.executeQuery(sql);
            Collection<Usuario> usuarios = new ArrayList<Usuario>();
            
            while (resultado.next()) {
            	
        		//Obtener el valor de cada columna
        		int id = resultado.getInt("id");
        		String nombre = resultado.getString("nombre");
        		int puntos = resultado.getInt("puntos");
        		int aciertos = resultado.getInt("aciertos");
            
        		//Guardar partidos
        		Usuario usuario = new Usuario(id,nombre,puntos,aciertos);
        		usuarios.add(usuario);
            }
            
            resultado.close();
            consulta.close();
            
            ResultadoFinal resultadoFinal = new ResultadoFinal(usuarios);
            System.out.println("RESULTADO FINAL");
            
            resultadoFinal.setMaxPts();
            resultadoFinal.setGanadores();
            resultadoFinal.setResultado();
            System.out.println();
            System.out.println("El/Los ganador/es es/son " + resultadoFinal.getResultado());
		
            // Esto se utiliza par cerrar la conexión con la base de datos
            conexion.close();
		
        } catch (SQLException se) {
            // Execpción ante problemas de conexión
            se.printStackTrace();
        } finally {
            // Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
            try {
                if (consulta != null)
                    consulta.close();
            } catch (SQLException se2) {
            }
            try {
                if (conexion != null)
                    conexion.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
	}

}
