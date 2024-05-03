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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.luisDeleon.dao.Conexion;
import org.luisDeleon.dto.CargoDTO;
import org.luisDeleon.model.Cargo;
import org.luisDeleon.system.Main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuCargosController implements Initializable {
    public Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarLista();
    }    
    
    @FXML
    Button btnRegresar,btnReportes,btnAgregar,btnEditar,btnBuscar;
    
    @FXML
    TableView tblCargos;
    
    @FXML
    TableColumn colCargo,colDescripcion,colCargoId;
    
    @FXML
    TextField tfCargoId;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
         if(event.getSource() == btnRegresar){
           stage.menuPrinciapalView();
        }else if(event.getSource() == btnAgregar){
            stage.FormularioCargos(1);
        }else if(event.getSource() == btnEditar){
            CargoDTO.getCargoDTO().setCargo((Cargo)tblCargos.getSelectionModel().getSelectedItem());
            stage.FormularioCargos(2);
        }else if(event.getSource() == btnBuscar){
            tblCargos.getItems().clear();
            if(tfCargoId.getText().equals("")){
                cargarLista();
            }else{
                tblCargos.getItems().add(buscarCargo());
                colCargoId.setCellValueFactory(new PropertyValueFactory<Cargo,Integer>("cargoId"));
                colCargo.setCellValueFactory(new PropertyValueFactory<Cargo,String>("nombreCargo"));
                colDescripcion.setCellValueFactory(new PropertyValueFactory<Cargo,String>("descripcioCargo"));
            }
        }
    }
    
     public Cargo buscarCargo(){
        Cargo cargo = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarCargo(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(tfCargoId.getText()));
            resultset = statement.executeQuery();
            
            if(resultset.next()){
                int cargoId = resultset.getInt("cargoId");
                String nombreCargo = resultset.getString("nombreCargo");
                String descripcion = resultset.getString("descripcionCargo");
                
                cargo = new Cargo(cargoId,nombreCargo,descripcion);
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
        return cargo;
    }
    
    public ObservableList<Cargo>listarCargos(){
        ArrayList<Cargo> cargos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarCargos()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int cargoid = resultset.getInt("cargoid");
                String nombreCargo = resultset.getString("nombreCargo");
                String descripcion = resultset.getString("descripcionCargo");
                
                
                cargos.add(new Cargo(cargoid,nombreCargo,descripcion));
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
        return FXCollections.observableList(cargos); 
    }
    
    public void cargarLista(){
        tblCargos.setItems(listarCargos());
        colCargoId.setCellValueFactory(new PropertyValueFactory<Cargo,Integer>("cargoId"));
        colCargo.setCellValueFactory(new PropertyValueFactory<Cargo,String>("nombreCargo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Cargo,String>("descripcioCargo"));
        
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
}
