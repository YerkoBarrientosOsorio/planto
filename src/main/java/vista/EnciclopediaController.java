package vista;


import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modelo.Huerto;
import modelo.Planta;

public class EnciclopediaController{
	
	
	
	@FXML private ScrollPane detallesPlanta;
	@FXML private TableView<Planta> tablaPlantas;
	@FXML private TableColumn<Planta, String> columnaNombre;
	@FXML private TableColumn<Planta, String> columnaNCient;
	
	@FXML private TextField barraBusqueda;
	
	@FXML private Text textoNombre;
	@FXML private Text textoNombreCient;
	@FXML private Text textoEpocaSiembra;
	@FXML private Text textoLuz;
	@FXML private Text textoTipoPlantac;
	@FXML private Text textoProfund;
	@FXML private Text textoDistanciaEntre;
	@FXML private Text textoTiempo;
	@FXML private TextFlow textoPasos;
	
	@FXML private Button botonAgregarPlanta;
	@FXML private Button botonBuscar;
	@FXML private AnchorPane apDerecho;
	
	private Planta plantaSeleccionada;
	private Huerto huerto;
	private HuertoController huertoControl;
	
	private ObservableList<Planta> plantasEnci;
	
	
	@FXML
	public void initialize() {

		columnaNombre.setCellValueFactory(celda -> celda.getValue().nombreProperty());
		columnaNCient.setCellValueFactory(celda -> celda.getValue().nombreCientProperty());
		
		interaciones();
		
	}
	
	public void agregarItems(ObservableList<Planta> plantas) {
		
		plantasEnci = plantas;
		mostrarItems(plantasEnci);
	}
	
	private void mostrarItems(ObservableList<Planta> plantas) {
		tablaPlantas.setItems(plantas);
	}
	
	public void mostrarPlanta(Planta planta) {
		if(planta!=null) {
			textoNombre				.setText(			planta.getNombre());
			textoNombreCient		.setText(			planta.getNombreCient());
			
			textoEpocaSiembra		.setText(			planta.getEpocaSiembra());
			textoLuz				.setText(			planta.getLuz());
			textoTipoPlantac		.setText(			planta.getTipoPlantac());
			textoProfund			.setText(			planta.getProfund());
			textoDistanciaEntre		.setText(			planta.getDistanciaEntre());
			textoTiempo				.setText(			planta.getTiempo()+" meses");
			
			textoPasos				.getChildren().setAll(new Text(planta.getPasos()));
		}
	}
	
	public ObservableList<Planta> filtrarPlantas(String planta) {
		ObservableList<Planta> plantas = this.plantasEnci;
		ObservableList<Planta> pFiltradas = FXCollections.observableArrayList(plantas.stream().filter(p -> p.getNombre().toLowerCase().contains(planta.toLowerCase())).collect(Collectors.toList()));
		return pFiltradas;
	}
	
	
	
	public void interaciones() {
		
		//Seleccionar celda de la tabla
		tablaPlantas.getSelectionModel().selectedItemProperty().addListener(
		            (observable, oldValue, newValue) -> {
		            	mostrarPlanta(newValue);
		            	plantaSeleccionada = newValue;
		            	
		            });
		
		//Buscar con barra de busqueda
		barraBusqueda.setOnAction(a -> {
			 
			 if(barraBusqueda.getText().length()>0) mostrarItems(filtrarPlantas(barraBusqueda.getText()));
			 else {mostrarItems(this.plantasEnci); }
			 
		 });
		
		//Buscar con barra de busqueda usando el boton lupa
		botonBuscar.setOnAction(a -> {
			
			if(barraBusqueda.getText().length()>0) mostrarItems(filtrarPlantas(barraBusqueda.getText()));
			else {mostrarItems(this.plantasEnci);}
			
		 });
		
		//Agregar grupo de plantas a huerto con boton +
		botonAgregarPlanta.setOnAction(e -> {
			 
			 boolean res = false; 
			 
			
				 try {
					res = agregarPlantaAHuerto();
					 if(res) { 
						 huerto.actualizarPlantas();
						 huertoControl.agregarItems(huerto.getPlantasHuerto());
						 
					 }
					 
				} catch (Exception e1) {
					textoNombre.setText("Valor no válido");
					e1.printStackTrace();
				}
				 
				  
			
			
		 });
		 
		 
		 
	}
	
	
	@FXML 
	public boolean agregarPlantaAHuerto() {
		
		TextInputDialog input = new TextInputDialog();
		input.setTitle("Número de plantas");
		input.setHeaderText("¿Cuántas?");
		
		
		
		if(plantaSeleccionada!=null) {
			input.setContentText("Número de "+plantaSeleccionada.getNombre()+"/s a agregar: ");
			Optional<String> resp = input.showAndWait();
			
			
			try {
				
				int r = Integer.parseInt(resp.get());
				
				if(r>0) {
					huerto.getConn().registrarGrupoAHuerto(r, plantaSeleccionada);
					
				}
				
				
				return true;
			
				
			} catch (Exception e) {
				
				input.setHeaderText("Número de plantas no válido");
				return false;
				
			}
			
			
			
		}
		
		return true;
		
		
		
		
		
	}
	
	public void setHuerto(Huerto huerto) {
		this.huerto = huerto;
	}
	
	public void setHuertoControl(HuertoController huertoControl) {
		this.huertoControl = huertoControl;
	}
	
	
	
	
	

}
