package accesodatostest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import accesodatos.Conn;
import modelo.Planta;

class ConnTest {
	
	private Conn conn;

	@BeforeEach
	void setUp() throws Exception {
		conn = new Conn();
	}

	@Test
	void testCargarPlantasEnciclopedia() {
		
		assertNotNull(conn.cargarPlantasEnciclopedia());
		
	}

	@Test
	void testCargarPlantasHuerto() {
		assertNotNull(conn.cargarPlantasHuerto());
	}

	@Test
	void testRegistrarGrupoAHuerto() {
		//String id, String nom, String nomCient, String epoca, String l, String tipoP, String prof, String dist, String tmp
		Planta planta = new Planta("100", "Tomate", "Tomatus", "Todo el año", "Toda", "Almácigo", "5", "10", "10", "Pasos...");
		
		assertEquals(true, conn.registrarGrupoAHuerto(10, planta));
	}
	
	@Test
	void testEliminarGrupoDeHuerto() {
		assertEquals(true, conn.eliminarGrupoDeHuerto(100));
	}

}
