package modelo;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Grupo {
	
	
	private Planta planta;
	private final StringProperty tiempoInicio;
	private final IntegerProperty numPlantas, grupo;
	
	
	
	public Grupo(int numPlantas, int grupo, String tiempoInicio, Planta p) {
		planta = p;
		this.numPlantas = new SimpleIntegerProperty(numPlantas);
		this.grupo = new SimpleIntegerProperty(grupo);
		this.tiempoInicio = new SimpleStringProperty(tiempoInicio);
	}
	
	public final IntegerProperty numPlantasProperty() {
		return this.numPlantas;
	}
	


	public final int getNumPlantas() {
		return this.numPlantasProperty().get();
	}
	


	public final void setNumPlantas(final int numPlantas) {
		this.numPlantasProperty().set(numPlantas);
	}
	
	public final IntegerProperty grupoProperty() {
		return this.grupo;
	}
	


	public final int getGrupo() {
		return this.grupoProperty().get();
	}
	


	public final void setGrupo(final int grupo) {
		this.grupoProperty().set(grupo);
	}

	
	
	public final StringProperty tiempoInicioProperty() {
		return this.tiempoInicio;
	}
	


	public final String getTiempoInicio() {
		return this.tiempoInicioProperty().get();
	}
	


	public final void setTiempoInicio(final String tiempoInicio) {
		this.tiempoInicioProperty().set(tiempoInicio);
	}
	
	


	public Planta getPlanta() {
		return planta;
	}

}
