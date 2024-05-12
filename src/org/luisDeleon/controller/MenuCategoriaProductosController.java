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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.luisDeleon.dao.Conexion;
import org.luisDeleon.dto.CategoriaProductoDTO;
import org.luisDeleon.model.CategoriaProducto;
import org.luisDeleon.system.Main;
import org.luisDeleon.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MenuCategoriaProductosController implements Initializable {
    private Main stage;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TableView tblCategoriaProductos;
    @FXML
    TableColumn colCategoriaProductoId, colNombreCategoria, colDescripcionCategoria;
    @FXML
    Button btnAgregar, btnEditar, btnRegresar, btnEliminar, btnBuscar;
    @FXML
    TextField tfCategoriaProductoId;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarLista();
    }    
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnAgregar){
            stage.formCategoriaProuctosView(1);
        } else if (event.getSource() == btnEditar){
            CategoriaProductoDTO.getCategoriaProductoDTO().setCategoriaProducto((CategoriaProducto)tblCategoriaProductos.getSelectionModel().getSelectedItem());
            stage.formCategoriaProuctosView(2);
        } else if (event.getSource() == btnRegresar){
            stage.menuPrinciapalView();
        } else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getIsntance().mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                int catPId = ((CategoriaProducto)tblCategoriaProductos.getSelectionModel().getSelectedItem()).getCategoriaProductosId();
                eliminarCategoriaProducto(catPId);
                cargarLista();
            }
        } else if(event.getSource() == btnBuscar){
            tblCategoriaProductos.getItems().clear();
            if(tfCategoriaProductoId.getText().equals("")){
                cargarLista();
            }else{
                tblCategoriaProductos.getItems().add(buscarCategoriaProducto());
                colCategoriaProductoId.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, Integer>("categoriaProductosId"));
                colNombreCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("nombreCategoria"));
                colDescripcionCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("descripcionCategoria"));                
            }
        }
    }
    
    public void cargarLista(){
        tblCategoriaProductos.setItems(listarCategoriaProductos());
        colCategoriaProductoId.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, Integer>("categoriaProductosId"));
        colNombreCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("nombreCategoria"));
        colDescripcionCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("descripcionCategoria")); 
    }
    
    public ObservableList<CategoriaProducto> listarCategoriaProductos(){
        ArrayList<CategoriaProducto> categoriaProductos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCategoriaProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int categoriaProductosId = resultSet.getInt("categoriaProductosId");
                String nombreCategoria = resultSet.getString("nombreCategoria");
                String descripcionCategoria = resultSet.getString("descripcionCategoria");
                
                categoriaProductos.add(new CategoriaProducto(categoriaProductosId, nombreCategoria, descripcionCategoria));
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
        return FXCollections.observableList(categoriaProductos);
    }
    
    public void eliminarCategoriaProducto(int catPId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_eliminarCategoriaProducto(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, catPId);
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
    
    public CategoriaProducto buscarCategoriaProducto(){
        CategoriaProducto categoriaProducto = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarCategoriaProducto(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCategoriaProductoId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int categoriaProductosId = resultSet.getInt("categoriaProductosId");
                String nombreCategoria = resultSet.getString("nombreCategoria");
                String descripcionCategoria = resultSet.getString("descripcionCategoria");
                
                categoriaProducto = (new CategoriaProducto(categoriaProductosId, nombreCategoria, descripcionCategoria));
                
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
        return categoriaProducto;
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}
