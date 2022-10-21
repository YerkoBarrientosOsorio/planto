package modelo;

import accesodatos.Conn;
import javafx.collections.ObservableList;

public class Huerto {
	
	private Conn con;
	public ObservableList<Grupo> plantasHuerto;
	
	public Huerto() {
		con = new Conn();
		plantasHuerto = con.cargarPlantasHuerto();
		
	}
	
	public ObservableList<Grupo> getPlantasHuerto() {
		
		return plantasHuerto;
	}
	
	public Conn getConn() {
		return con;
	}
	
	public void actualizarPlantas() {
		plantasHuerto = con.cargarPlantasHuerto();
	}

}
