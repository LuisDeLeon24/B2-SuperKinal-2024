/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.luisDeleon.dao.Conexion;
import org.luisDeleon.model.Empleado;
import org.luisDeleon.system.Main;

/**
 * FXML Controller class
 *
 * @author Luis De León
 */
public class MenuEmpleadosController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    /**
     * Initializes the controller class.
     */
    
    
    
    @FXML
    TableView tblEmpleados;
    
    @FXML
    TableColumn colEmpleadoId,colNombre,colSueldo,colEntrada,colSalida;
    
    @FXML
    ComboBox cmbCargo,cmbEncargado;
    
    @FXML
    Button btnRegresar,btnGuardar;
    
    @FXML
    TextField tfEmpleadoId,tfNombre,tfSueldo,tfCargo,tfEncargado,tfEntrada;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void vaciarCampos(){
        tfEmpleadoId.clear();
        tfNombre.clear();
        tfSueldo.clear();
        tfCargo.clear();
        tfEncargado.clear();
        tfEntrada.clear();
        cmbCargo.getSelectionModel().clearSelection();
        cmbEncargado.getSelectionModel().clearSelection();
    }
    
    //public void cargarDatos(){
        //tblEmpleados.setItems(listarEmpleados());
        //colEmpleadoId.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("ticketSoporteId"));
        //colNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("descripcionTicket"));
        //colEstatus.setCellValueFactory(new PropertyValueFactory<Empleado, String>("estatus"));
        //colCliente.setCellValueFactory(new PropertyValueFactory<Empleado, String>("cliente"));
        //colFacturaId.setCellValueFactory(new PropertyValueFactory<Empleado, String>("facturaId"));
        //tblTickets.getSortOrder().add(colTicketId);
    //} 
    
    public ObservableList<Empleado>listarEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int empleadoId = resultset.getInt("empleadoId");
                String nombre = resultset.getString("nombreEmpleado");
                String apellido = resultset.getString("apellidoEmpleado");
                double sueldo = resultset.getDouble("sueldo");
                String horaEntrada = resultset.getString("horaEntrada");
                String horaSalida = resultset.getString("horaSalida");
                int cargoId = resultset.getInt("cargoId");
                int encargadoId = resultset.getInt("encargadoId");
                
                empleados.add(new Empleado(empleadoId,nombre,apellido,sueldo,horaEntrada,horaSalida,cargoId,encargadoId));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
                }
                
                if(statement != null){
                    statement.close();
                }
                
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableList(empleados); 
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
}
