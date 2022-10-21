package vista;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import modelo.Grupo;
import modelo.Huerto;

public class HuertoController {
	
	
	@FXML private TableView<Grupo> 					tablaPlantasHuerto;
	
	@FXML private TableColumn<Grupo, String> 		columnaNombre;
	@FXML private TableColumn<Grupo, Number> 		columnaNumero;
	@FXML private TableColumn<Grupo, Number> 		columnaGrupo;

	@FXML private Button 							botonEliminarGrupo;
	
	@FXML private Text textoNombre;
	@FXML private Text textoNombreCient;
	
	@FXML private Text textoTiempoRestante;
	@FXML private Text textoFechaInicio;
	@FXML private Text textoFechaTermino;
	
	@FXML private ProgressBar barraProgreso;
	
	private ObservableList<Grupo> gruposHuerto;
	private Grupo grupoSeleccionado;
	
	private Huerto huerto = new Huerto();
	
	@FXML public void initialize() {
		columnaNombre.setCellValueFactory(celda -> celda.getValue().getPlanta().nombreProperty());
		columnaNumero.setCellValueFactory(celda -> celda.getValue().numPlantasProperty());
		columnaGrupo.setCellValueFactory(celda -> celda.getValue().grupoProperty());
		
		interacciones();
	}
	
	
	
	public void agregarItems(ObservableList<Grupo> grupos) {
		gruposHuerto = grupos;
		mostrarItems(gruposHuerto);
	}
	
	public void mostrarItems(ObservableList<Grupo> grupos) {
		tablaPlantasHuerto.setItems(grupos);
	}
	
	public void mostrarGrupo(Grupo grupo) {
		
		if(grupo!=null) {
		
		long diasFinal = LocalDate.parse(grupo.getTiempoInicio()).until(LocalDate.parse(grupo.getTiempoInicio()).plusMonths(Integer.parseInt(grupo.getPlanta().getTiempo())), ChronoUnit.DAYS);
		long diasRestantes = LocalDate.now().until(LocalDate.parse(grupo.getTiempoInicio()).plusMonths(Integer.parseInt(grupo.getPlanta().getTiempo())), ChronoUnit.DAYS);
		
		double da = (double)diasFinal;
		double df = (double)diasRestantes;
		
		
			
			
			barraProgreso.setProgress(((da - df + 0) / 100.0) + 0.02);
			
			if(grupo.getNumPlantas()>1) {
				textoNombre.setText(grupo.getNumPlantas()+" "+grupo.getPlanta().getNombre()+"/s");
			} else { textoNombre.setText(grupo.getNumPlantas()+" "+grupo.getPlanta().getNombre()); }
			
			textoNombreCient.setText(grupo.getPlanta().getNombreCient());
			
			textoTiempoRestante.setText(diasRestantes+" días aprox.");
			textoFechaInicio.setText(grupo.getTiempoInicio());
			textoFechaTermino.setText(grupo.getPlanta().getTiempo()+" meses");
		}
	}
	
	public void interacciones() {
		
		//Mostrar grupo seleccionando celda
		tablaPlantasHuerto.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> {
	            	try {
	            		
	            		grupoSeleccionado = newValue;
	            		
	            		if(grupoSeleccionado!=null) {
	            			
							mostrarGrupo(grupoSeleccionado);
							
	            		}
					} catch (Exception e) {
						e.printStackTrace();
					}
	            });
		
		//Eliminar grupo seleccionando grupo
		botonEliminarGrupo.setOnAction(a -> {
			
			int grupo = grupoSeleccionado.getGrupo();
			
			try {
				if(grupoSeleccionado!=null) {
				        	
					eliminarGrupo(grupo);
				    gruposHuerto.removeIf(item -> item.getGrupo() == grupo);
				        	
				        	
				        	
				        }
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		});
		
		
		
	}
	
	public void eliminarGrupo(int id) {
		huerto.getConn().eliminarGrupoDeHuerto(id);
	}
	
	
	

	
	

}
