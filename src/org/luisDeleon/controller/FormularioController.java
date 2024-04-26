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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.luisDeleon.dao.Conexion;
import org.luisDeleon.dto.ClienteDTO;
import org.luisDeleon.model.Cliente;
import org.luisDeleon.system.Main;
import org.luisDeleon.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Luis De León
 */
public class FormularioController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    
    @FXML
    TextField tfClienteID,tfNombre,tfApellido,tfTelefono,tfDireccion,tfNIT;
    @FXML
    Button btnCancelar,btnGuardar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ClienteDTO.getClienteDTO().getCliente() != null){
           cargarDatos(ClienteDTO.getClienteDTO().getCliente()); 
        }
        
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            ClienteDTO.getClienteDTO().setCliente(null);
            stage.menuClientesView();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombre.getText().equals("") && !tfApellido.getText().equals("") && !tfDireccion.getText().equals("")){
                   SuperKinalAlert.getIsntance().mostrarAlertaInfo(401);
                    agregarCliente();
                   stage.menuClientesView(); 
                }else{
                    SuperKinalAlert.getIsntance().mostrarAlertaInfo(400);
                    tfNombre.requestFocus();
                    return;
                }
                
            }else if(op == 2){
                if(!tfNombre.getText().equals("") && !tfApellido.getText().equals("") && !tfDireccion.getText().equals("")){
                    if(SuperKinalAlert.getIsntance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK){
                        editarCliente();
                        ClienteDTO.getClienteDTO().setCliente(null);
                        stage.menuClientesView();
                    }
                }else{
                    SuperKinalAlert.getIsntance().mostrarAlertaInfo(400);
                    tfNombre.requestFocus();
                    return;
                }           
            }
        }
    }
    
    public void cargarDatos(Cliente cliente){
        tfClienteID.setText(Integer.toString(cliente.getClienteId()));
        tfNombre.setText(cliente.getNombre());
        tfApellido.setText(cliente.getApellido());
        tfTelefono.setText(cliente.getTelefono());
        tfDireccion.setText(cliente.getDireccion());
        tfNIT.setText(cliente.getNit());
    }
    
    public void agregarCliente(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarCliente(?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1,tfNombre.getText());
            statement.setString(2,tfApellido.getText());
            statement.setString(3,tfTelefono.getText());
            statement.setString(4,tfDireccion.getText());
            statement.setString(5,tfNIT.getText());
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
    
    public void editarCliente(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarCliente(?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfClienteID.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3,tfApellido.getText());
            statement.setString(4,tfTelefono.getText());
            statement.setString(5,tfDireccion.getText());
            statement.setString(6,tfNIT.getText());
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
