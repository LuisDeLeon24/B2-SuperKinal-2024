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
    MenuItem btnMenuClientes, btnTicketSoporte, btnCompras,btnCargos,btnEmpleados,btnCatpro;
    
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
            stage.MenuEmpleados();
        }else if(event.getSource() == btnCatpro){
            stage.menuCategoriaProductosView();
        }
    }
}
