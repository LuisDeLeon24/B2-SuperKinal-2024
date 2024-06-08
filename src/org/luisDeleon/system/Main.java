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
<<<<<<< HEAD
import org.luisDeleon.controller.DetalleFacturasController;
import org.luisDeleon.controller.FormCategoriaProductosController;
import org.luisDeleon.controller.FormDistribuidoresController;
import org.luisDeleon.controller.FormUsuarioController;
=======
import org.luisDeleon.controller.FormCategoriaProductosController;
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
import org.luisDeleon.controller.FormularioCargosController;
import org.luisDeleon.controller.FormularioController;
import org.luisDeleon.controller.LoginController;
import org.luisDeleon.controller.MenuCargosController;
import org.luisDeleon.controller.MenuCategoriaProductosController;
import org.luisDeleon.controller.MenuClientesController;
import org.luisDeleon.controller.MenuComprasController;
import org.luisDeleon.controller.MenuEmpleadosController;
import org.luisDeleon.controller.MenuPrincipalController;
import org.luisDeleon.controller.MenuTicketSoporteController;
import org.luisDeleon.controller.MenuDistribuidoresController;
import org.luisDeleon.controller.MenuFacturasController;
import org.luisDeleon.controller.MenuProductosController;
import org.luisDeleon.controller.MenuPromocionesController;

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
       //menuPrinciapalView();
        loginView();
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
    
    public void MenuEmpleados(int op){
       try{
            MenuEmpleadosController menuEmpleadosView = (MenuEmpleadosController)switchScene("MenuEmpleadosView.fxml",1200,800);
            menuEmpleadosView.setOp(op);
            menuEmpleadosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } 
    }
    
    public void menuCategoriaProductosView(){
        try{
            MenuCategoriaProductosController menuCategoriaProductoView = (MenuCategoriaProductosController)switchScene("MenuCategoriaProductosView.fxml", 1200, 750);
            menuCategoriaProductoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formCategoriaProuctosView(int op){
        try{
            FormCategoriaProductosController formCategoriaProductosView = (FormCategoriaProductosController)switchScene("FormCategoriaProductosView.fxml", 500, 600);
            formCategoriaProductosView.setOp(op);
            formCategoriaProductosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
<<<<<<< HEAD
    
    public void menuDistribuidorView(){
        try{
            MenuDistribuidoresController menuDistribuidorView = (MenuDistribuidoresController)switchScene("MenuDistribuidoresView.fxml", 950, 600);
            menuDistribuidorView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void formDistribuidoresView(int op){
        try{
            FormDistribuidoresController formDistribuidoresView = (FormDistribuidoresController)switchScene("FormDistribuidoresView.fxml", 450, 600);
            formDistribuidoresView.setOp(op);
            formDistribuidoresView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void menuProductoView(){
        try{
            MenuProductosController menuProductoController = (MenuProductosController)switchScene("MenuProductosView.fxml",1150, 600);
            menuProductoController.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void menuPromocionView(){
        try{
            MenuPromocionesController menuPromocionesView = (MenuPromocionesController)switchScene("MenuPromocionesView.fxml" , 1000, 600);
            menuPromocionesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void loginView(){
        try{
            LoginController loginView = (LoginController)switchScene("LoginView.fxml" , 500, 600);
            loginView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void formUsuarioView(){
        try{
            FormUsuarioController formUsuarioView = (FormUsuarioController)switchScene("FormUsuarioView.fxml" , 500, 600);
            formUsuarioView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void menuFacturasView(){
        try{
            MenuFacturasController menuFacturasView = (MenuFacturasController)switchScene("MenuFacturasView.fxml" , 1200, 700);
            menuFacturasView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void DetalleFacturasView(){
        try{
            DetalleFacturasController detalleFacturasView = (DetalleFacturasController)switchScene("DetalleFacturasView.fxml" , 600, 400);
            detalleFacturasView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
=======
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
}
