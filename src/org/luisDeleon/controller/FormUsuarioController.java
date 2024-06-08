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
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.luisDeleon.dao.Conexion;
import org.luisDeleon.model.Empleado;
import org.luisDeleon.model.NivelAcceso;
import org.luisDeleon.system.Main;
import org.luisDeleon.utils.PaswordUtils;

/**
 * FXML Controller class
 *
 * @author Luis De León
 */
public class FormUsuarioController implements Initializable {
    private Main stage;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField tfUser,tfPassword;
    @FXML
    ComboBox cmbEmpleado,cmbNivelAcceso; 
    @FXML
    Button btnRegistrar,btnEmpleado;
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegistrar){
            agregarUsuario();
            stage.loginView();
                    
        }else if(event.getSource() == btnEmpleado){
            stage.MenuEmpleados(3);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbEmpleado.setItems(listarEmpleados());
        cmbNivelAcceso.setItems(listarNivelesAcceso());
    }
    

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    

    public void agregarUsuario(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarUsuario(?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1,tfUser.getText());
            statement.setString(2,PaswordUtils.getInstance().encryptedPassword(tfPassword.getText()));
            statement.setInt(3,((NivelAcceso)cmbNivelAcceso.getSelectionModel().getSelectedItem()).getNivelccesoId());
            statement.setInt(4,((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
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
    
    public ObservableList<Empleado>listarEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = ("call sp_ListarEmpleados()");
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                double sueldo = resultSet.getDouble("sueldo");
                Time horaEntrada = resultSet.getTime("horaEntrada");
                Time horaSalida = resultSet.getTime("horaSalida");
                int cargoId = resultSet.getInt("cargoId");
                int encargadoId = resultSet.getInt("encargadoId");
                
                empleados.add(new Empleado(empleadoId,nombreEmpleado,apellidoEmpleado,sueldo,horaEntrada,horaSalida,cargoId,encargadoId));
                        
            }
                    
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
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
  
    public ObservableList<NivelAcceso>listarNivelesAcceso(){
        ArrayList<NivelAcceso> nivelesAcceso = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarNivelAcceso()";
            statement = conexion.prepareStatement(sql);
            resultSet =statement.executeQuery();
            
            while(resultSet.next()){
                int nivelAccesoId = resultSet.getInt("nivelAccesoId");
                String nivelAcceso = resultSet.getString("nivelAcceso");
                
                nivelesAcceso.add(new NivelAcceso(nivelAccesoId,nivelAcceso));
            }
        }catch(SQLException e){
                System.out.println(e.getMessage());

        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
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
        
        return FXCollections.observableArrayList(nivelesAcceso);
    }
}
