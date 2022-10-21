package accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Grupo;
import modelo.Planta;

public class Conn {
	
private String url = "";
	

	private Connection conectar(String db) {
		
		Connection conn = null;
		
		url = "jdbc:sqlite:"+db+".db";
		
		try { 						conn = DriverManager.getConnection(url); 	}
		catch(SQLException ex) { 	System.out.println(ex.getMessage()); 		}
		
		return conn;
		
	}
	
	
	
	public ObservableList<Planta> cargarPlantasEnciclopedia() {
			
			ObservableList<Planta> plantas = FXCollections.observableArrayList();
			
			String cmd = "SELECT id, nombre, nombreCient, epocaSiembra, luz, tipoPlantac, profund, distanciaEntre, tiempo, pasos FROM Plantas";
			
			try{
				
				Connection conn = this.conectar("plantas");
				Statement stmt  = conn.createStatement();
				ResultSet rs    = stmt.executeQuery(cmd);
				
				while(rs.next()) {
					
					
					plantas.add(new Planta(
							Integer.toString(rs.getInt("id")),
							rs.getString("nombre"),
			                rs.getString("nombreCient"),
			                rs.getString("epocaSiembra"),
			                rs.getString("luz"),
			                rs.getString("tipoPlantac"),
			                rs.getString("profund"),
			                rs.getString("distanciaEntre"),
			                rs.getString("tiempo"),
			                rs.getString("pasos")
							));
				}
				
				return plantas;	
				
			} catch(SQLException ex) {
				System.out.println(ex.getMessage());
				return null;
			}
			
			
		
	}
	
	public ObservableList<Grupo> cargarPlantasHuerto(){
		
		ObservableList<Grupo> grupos = FXCollections.observableArrayList();
		
		String cmd = "SELECT numPlantas, grupo, id, nombre, nombreCient, epocaSiembra, luz, tipoPlantac, profund, distanciaEntre, tiempo, tiempoInicio, pasos FROM PlantasHuerto";
		
		try {
			
			Connection conn = this.conectar("plantasHuerto");
			Statement stmt  = conn.createStatement();
			ResultSet rs    = stmt.executeQuery(cmd);
			
			while(rs.next()) {
				
				/*rs.getInt("numPlantas"),
				rs.getInt("grupo"),
				Integer.toString(rs.getInt("id")),
				rs.getString("nombre"),
                rs.getString("nombreCient"),
                rs.getString("epocaSiembra"),
                rs.getString("luz"),
                rs.getString("tipoPlantac"),
                rs.getString("profund"),
                rs.getString("distanciaEntre"),
                rs.getString("tiempo")*/
				
				
				grupos.add(new Grupo(
						
							rs.getInt("numPlantas"),
							rs.getInt("grupo"),
							rs.getString("tiempoInicio"),
							
							new Planta(	Integer.toString(rs.getInt("id")),
										rs.getString("nombre"),
						                rs.getString("nombreCient"),
						                rs.getString("epocaSiembra"),
						                rs.getString("luz"),
						                rs.getString("tipoPlantac"),
						                rs.getString("profund"),
						                rs.getString("distanciaEntre"),
						                rs.getString("tiempo"),
										rs.getString("pasos"))
						
						
						));
			}
			return grupos;	
			
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public boolean registrarGrupoAHuerto(int num, Planta planta) {
		String cmd = "INSERT INTO PlantasHuerto(numPlantas, id, nombre, nombreCient, epocaSiembra, luz, tipoPlantac, profund, distanciaEntre, tiempo, tiempoInicio, pasos) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Connection conn = conectar("plantasHuerto");
			PreparedStatement ps = conn.prepareStatement(cmd);
			
			ps.setString(1, Integer.toString(num));
			ps.setString(2, planta.getId());
			ps.setString(3, planta.getNombre());
			ps.setString(4, planta.getNombreCient());
			ps.setString(5, planta.getEpocaSiembra());
			ps.setString(6, planta.getLuz());
			ps.setString(7, planta.getTipoPlantac());
			ps.setString(8, planta.getProfund());
			ps.setString(9, planta.getDistanciaEntre());
			ps.setString(10, planta.getTiempo());
			ps.setString(11, LocalDate.now().toString());
			ps.setString(12, planta.getPasos());	
			
			ps.executeUpdate();
			
			return true;
			
		} catch(SQLException exc) {
			exc.printStackTrace();
			
			return false;
		}
		
	}
	
	public boolean eliminarGrupoDeHuerto(int grupo) {
		String cmd = "DELETE FROM PlantasHuerto WHERE grupo = ?";
		
		try {
			Connection conn = conectar("plantasHuerto");
			PreparedStatement ps = conn.prepareStatement(cmd);
			
			ps.setInt(1, grupo);
			ps.executeUpdate();
			
			return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	

}
