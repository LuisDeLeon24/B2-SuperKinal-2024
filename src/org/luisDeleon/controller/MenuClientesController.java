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
import org.luisDeleon.dto.ClienteDTO;
import org.luisDeleon.model.Cliente;
import org.luisDeleon.system.Main;

/**
 * FXML Controller class
 *
 * @author Luis De León
 */
public class MenuClientesController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    TableView tblClientes;
    @FXML
    TableColumn colClienteid,colNombre,colApellido,colTelefono,colDireccion,colNIT;
    @FXML
    Button btnRegresar,btnAgregar,btnEditar,btnEliminar,btnBuscar;
    @FXML
    TextField  tfClienteID;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnRegresar){
           stage.menuPrinciapalView();
        }else if(event.getSource() == btnAgregar){
            stage.formularioView(1);
        }else if(event.getSource() == btnEditar){
            ClienteDTO.getClienteDTO().setCliente((Cliente)tblClientes.getSelectionModel().getSelectedItem());
            stage.formularioView(2);
        }else if(event.getSource() == btnEliminar){
            int cliID = ((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getClienteId();
            eliminarCliente(cliID);
            cargarLista();
        }else if(event.getSource() == btnBuscar){
            tblClientes.getItems().clear();
            if(tfClienteID.getText().equals("")){
                cargarLista();
            }else{
                tblClientes.getItems().add(buscarCliente());
                colClienteid.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("clienteId"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
                colApellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellido"));
                colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
                colDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
                colNIT.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nit"));
            }
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resource) {
        // TODO
        cargarLista();
    } 
    
    
    
    public void cargarLista(){
        tblClientes.setItems(listarClientes());
        colClienteid.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("clienteId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
        colNIT.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nit"));
    }
    
    public ObservableList<Cliente>listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarClientes()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int clienteid = resultset.getInt("clienteid");
                String nombre = resultset.getString("nombre");
                String apellido = resultset.getString("apellido");
                String telefono = resultset.getString("telefono");
                String direccion = resultset.getString("direccion");
                String nit = resultset.getString("nit");
                
                clientes.add(new Cliente(clienteid,nombre,apellido,telefono,direccion,nit));
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
        return FXCollections.observableList(clientes); 
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public void eliminarCliente(int cliID){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EliminarCliente(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,cliID);
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
    
    public Cliente buscarCliente(){
        Cliente cliente = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarCliente(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(tfClienteID.getText()));
            resultset = statement.executeQuery();
            
            if(resultset.next()){
                int clienteid = resultset.getInt("clienteid");
                String nombre = resultset.getString("nombre");
                String apellido = resultset.getString("apellido");
                String telefono = resultset.getString("telefono");
                String direccion = resultset.getString("direccion");
                String nit = resultset.getString("nit");
                
                cliente = new Cliente(clienteid,nombre,apellido,telefono,direccion,nit);
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
        return cliente;
    }
    
}
