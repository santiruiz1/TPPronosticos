package ar.utn.ap.Pronosticos;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.*;

public class ResultadoFinalTest {
	
	Collection<Usuario> usuarios = new ArrayList<Usuario>();
	ResultadoFinal resultadoFinal;
	Usuario usuario1 = new Usuario(0,"Santiago",4,2);
	Usuario usuario2 = new Usuario(0,"Gonzalo",1,1);
	Usuario usuario3 = new Usuario(0,"Facundo",3,1);
	
	
	@Before
	public void setUp() {
		usuarios.add(usuario1);
		usuarios.add(usuario2);
		usuarios.add(usuario3);
		this.resultadoFinal = new ResultadoFinal(usuarios);
		this.resultadoFinal.setMaxPts();
		this.resultadoFinal.setGanadores();
		this.resultadoFinal.setResultado();
	}
	
	@Test
	public void maxPts() {
		
		int maxPts = this.resultadoFinal.getMaxPts();
		
		
		assertEquals(4, maxPts);
	}
	@Test
	public void ganadoresTest() {
		Collection<Usuario> ganadores = this.resultadoFinal.getGanadores();
		Collection<Usuario> expected = new ArrayList<Usuario>();
		expected.add(usuario1);
		
		assertEquals(expected, ganadores);
	}
	@Test
	public void resultadoTest() {
		
		String resultado = this.resultadoFinal.getResultado();
		
		assertEquals("Santiago", resultado);
	}
	
}
