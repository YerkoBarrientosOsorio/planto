package control;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Enciclopedia;
import modelo.Huerto;
import vista.EnciclopediaController;
import vista.HuertoController;

public class MainController {
	
	private Enciclopedia modeloEnciclopedia = new Enciclopedia();
	private Huerto modeloHuerto = new Huerto();
	
	private BorderPane rootPane;
	private AnchorPane enciPane, huertoPane;
	
	private EnciclopediaController enciControl;
	private HuertoController huertoControl;
	
	
	public MainController() {
		
		cargarVistas();
		cargarTabs();
		
	}
	
	public void mostrar(Stage stage) {
		Scene scene = new Scene(rootPane, 600, 400);
		stage.getIcons().add(new Image("plantaicon.png"));
		stage.setTitle("Planto");
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	public boolean cargarVistas() {
		try {
			
			FXMLLoader rootLoader = new FXMLLoader();
			rootLoader.setLocation(getClass().getResource("/vista/RootLayout.fxml"));
			rootPane = rootLoader.load();
			
			
			FXMLLoader enciLoader = new FXMLLoader();
			enciLoader.setLocation(getClass().getResource("/vista/Enciclopedia.fxml"));
			enciPane = enciLoader.load();
			enciControl = enciLoader.getController();
			enciControl.agregarItems(modeloEnciclopedia.getPlantas());
			enciControl.setHuerto(modeloHuerto);
			enciPane.getStylesheets().add("/vista/estilo.css");
			
			
			FXMLLoader huertoLoader = new FXMLLoader();
			huertoLoader.setLocation(getClass().getResource("/vista/Huerto.fxml"));
			huertoPane = huertoLoader.load();
			huertoControl = huertoLoader.getController();
			huertoControl.agregarItems(modeloHuerto.getPlantasHuerto());
			
			enciControl.setHuertoControl(huertoControl);
			
			return true;
			
		} catch (IOException e) {
			e.printStackTrace(); 
			
			return false;
			
		}
	}
	
	private void cargarTabs() {
		
		TabPane tabs = new TabPane();
		
		Tab enciTab = new Tab();
		enciTab.setText("Enciclopedia");
		enciTab.setContent(enciPane);
		enciTab.setClosable(false);
		
		Tab huertoTab = new Tab();
		huertoTab.setText("Huerto");
		huertoTab.setContent(huertoPane);
		huertoTab.setClosable(false);
		
		tabs.getTabs().addAll(enciTab, huertoTab);
		
		rootPane.setCenter(tabs);
		
	}

}
