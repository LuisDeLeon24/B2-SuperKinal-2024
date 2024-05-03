/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.luisDeleon.dao.Conexion;
import org.luisDeleon.dto.CargoDTO;
import org.luisDeleon.model.Cargo;
import org.luisDeleon.system.Main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormularioCargosController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnCancelar,btnGuardar;
    
    @FXML
    TextField tfCargoId,tfNombreCargo;
    
    @FXML
    TextArea taDescripcion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(CargoDTO.getCargoDTO().getCargo() != null){
           cargarDatos(CargoDTO.getCargoDTO().getCargo()); 
        }
    }  
    
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.MenuCargos();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                agregarCargo();
                stage.MenuCargos();
            }else if(op == 2){
                editarCargo();
                CargoDTO.getCargoDTO().setCargo(null);
                stage.MenuCargos();
            }  
        }
    }
    
    public void editarCargo(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarCargos(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCargoId.getText()));
            statement.setString(2, tfNombreCargo.getText());
            statement.setString(3,taDescripcion.getText());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
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
    }
    
    public void agregarCargo(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarCargo(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1,tfNombreCargo.getText());
            statement.setString(2,taDescripcion.getText());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
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
    }
    
    public void cargarDatos(Cargo cargo){
        tfCargoId.setText(Integer.toString(cargo.getCargoId()));
        tfNombreCargo.setText(cargo.getNombreCargo());
        taDescripcion.setText(cargo.getDescripcioCargo());
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public void setOp(int op) {
        this.op = op;
    } 
    
}
