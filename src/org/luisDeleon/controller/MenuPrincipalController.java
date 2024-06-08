/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.luisDeleon.system.Main;

/**
 *
 * @author Luis De León
 */
public class MenuPrincipalController implements Initializable {
    private Main stage;
    
    @FXML
<<<<<<< HEAD
    MenuItem btnMenuClientes, btnTicketSoporte, btnCompras,btnCargos,btnEmpleados,btnCP,btnDistribuidores,btnProductos,btnPromociones,btnFacturas;
=======
    MenuItem btnMenuClientes, btnTicketSoporte, btnCompras,btnCargos,btnEmpleados,btnCatpro;
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
    
    @Override
    public void initialize(URL location,ResourceBundle resources){
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnMenuClientes){
            stage.menuClientesView();
        }else if(event.getSource() == btnTicketSoporte){
            stage.MenuTicketSoporte();
        }else if(event.getSource() == btnCompras){
            stage.MenuCompras();
        }else if(event.getSource() == btnCargos){
            stage.MenuCargos();
        }else if(event.getSource() == btnEmpleados){
<<<<<<< HEAD
            stage.MenuEmpleados(1);
        }else if(event.getSource() == btnCP){
            stage.menuCategoriaProductosView();
        }else if(event.getSource() == btnDistribuidores){
            stage.menuDistribuidorView();
        }else if(event.getSource() == btnProductos){
            stage.menuProductoView();
        }else if(event.getSource() == btnPromociones){
            stage.menuPromocionView();
        }else if(event.getSource() == btnFacturas){
            stage.menuFacturasView();
=======
            stage.MenuEmpleados();
        }else if(event.getSource() == btnCatpro){
            stage.menuCategoriaProductosView();
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
        }
        
    }
}
