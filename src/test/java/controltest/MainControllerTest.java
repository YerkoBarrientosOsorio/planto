package controltest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import control.MainController;

class MainControllerTest {
	
	MainController mainControl;

	@BeforeEach
	void setUp() throws Exception {
		mainControl = new MainController();
	}

	
	/*@Test
	void testCargarVistas() {
		assertEquals(true, mainControl.cargarVistas());
	}*/

}
