package ar.utn.ap.Pronosticos;
import static org.junit.Assert.assertEquals;

import org.junit.*;

public class PartidoTest {
	
	private Partido partido;
	
	@Before
	public void setUp() {
		this.partido = new Partido(1,1,"argentina","arabia saudita",0,0);
	}
	
	@Test
	public void resultadoEmpate() {
		
		String resultado = this.partido.getResultado();
		
		assertEquals("empate", resultado);
	}
	@Test
	public void resultadoGana1() {
		this.partido.setGoles1(1);
		
		String resultado = this.partido.getResultado();
		
		assertEquals("gana1", resultado);
	}
	@Test
	public void resultadoGana2() {
		this.partido.setGoles2(1);
		
		String resultado = this.partido.getResultado();
		
		assertEquals("gana2", resultado);
	}
	
}
