package modelo;

import accesodatos.Conn;
import javafx.collections.ObservableList;

public class Enciclopedia {
	
	private Conn conn;
	private ObservableList<Planta> plantas;
		
	public Enciclopedia(){
			conn = new Conn();
			plantas = conn.cargarPlantasEnciclopedia();
	}
		
		
		
	public ObservableList<Planta> getPlantas() {
			plantas = conn.cargarPlantasEnciclopedia();
			
			return plantas;
	}
		
	

}
