/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.system;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.luisDeleon.controller.FormularioCargosController;
import org.luisDeleon.controller.FormularioController;
import org.luisDeleon.controller.MenuCargosController;
import org.luisDeleon.controller.MenuClientesController;
import org.luisDeleon.controller.MenuComprasController;
import org.luisDeleon.controller.MenuPrincipalController;
import org.luisDeleon.controller.MenuTicketSoporteController;

/**
 *
 * @author Luis De León
 */
public class Main extends Application {
    private Stage stage;
    private Scene scene;
    private final String URLVIEW = "/org/luisDeleon/view/";
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("SuperKinal APP");
        menuPrinciapalView();
        stage.show();
    }
    
    public Initializable switchScene(String fxmlName, int width, int heigh) throws Exception{
        Initializable resultado;
        FXMLLoader loader = new FXMLLoader();
        
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        
        scene = new Scene((AnchorPane)loader.load(file),width,heigh);
        stage.setScene(scene);
        stage.sizeToScene();
        
        resultado = (Initializable) loader.getController();
        
        return resultado;
    }
    
    public void menuPrinciapalView(){
        try{
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml",900,700);
            menuPrincipalView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void menuClientesView(){
        try{
            MenuClientesController menuClientesView = (MenuClientesController)switchScene("MenuClientesView.fxml",1200,700);
            menuClientesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formularioView(int op){
        try{
            FormularioController formularioView = (FormularioController)switchScene("FormularioView.fxml",320,500);
            formularioView.setOp(op);
            formularioView.setStage(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void MenuTicketSoporte(){
        try{
            MenuTicketSoporteController MenuTicketSoporteView = (MenuTicketSoporteController)switchScene("MenuTicketSoporteView.fxml",1200,800);
            MenuTicketSoporteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void MenuCompras(){
        try{
            MenuComprasController menuComprasView = (MenuComprasController)switchScene("MenuComprasView.fxml",1017,700);
            menuComprasView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void MenuCargos(){
        try{
            MenuCargosController menuCargosView = (MenuCargosController)switchScene("MenuCargosView.fxml",800,700);
            menuCargosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void FormularioCargos(int op){
        try{
            FormularioCargosController formularioCargosView = (FormularioCargosController)switchScene("FormularioCargosView.fxml",320,500);
            formularioCargosView.setOp(op);
            formularioCargosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
