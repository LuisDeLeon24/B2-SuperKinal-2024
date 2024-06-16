package org.luisDeleon.reports;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.luisDeleon.dao.Conexion;
import win.zqxu.jrviewer.JRViewerFX;

/**
 *
 * @author Usuario
 */
public class GenerarReporte {
    private static GenerarReporte instance;
    
    private static Connection conexion = null;

    private GenerarReporte() {
    }
    
    public static GenerarReporte getInstance(){
        if(instance == null){
            instance = new GenerarReporte();
        }
        return instance;
    }
    
    public void generarFactura(int facId){
        try{
            Stage reportStage = null;
            conexion = Conexion.getInstance().obtenerConexion();
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("facId", facId);
            
            InputStream jasperPath = GenerarReporte.class.getResourceAsStream("/org/luisDeleon/report/Factura.jasper");
            JasperPrint reporte = JasperFillManager.fillReport(jasperPath, parametros, conexion);
            
            reportStage = new Stage();
            
            JRViewerFX reportViewer = new JRViewerFX(reporte);
            
            Pane root = new Pane();
            
            root.getChildren().add(reportViewer);
            
            reportViewer.setPrefSize(1000, 800);
            
            Scene scene = new Scene(root);
            reportStage.setScene(scene);
            reportStage.setTitle("Factura");
            reportStage.show();
                    
                    
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
        public void generarClientes(int cliId){
            try{
            
                conexion = Conexion.getInstance().obtenerConexion();
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("cliId", cliId);
            
                InputStream jasperPath = GenerarReporte.class.getResourceAsStream("/org/luisDeleon/report/Clientes.jasper");
                JasperPrint reporte = JasperFillManager.fillReport(jasperPath, parametros, conexion);
            
                Stage reportStage = new Stage();
            
                JRViewerFX reportViewer = new JRViewerFX(reporte);
            
                Pane root = new Pane();
            
                root.getChildren().add(reportViewer);
            
                reportViewer.setPrefSize(1000, 800);
            
                Scene scene = new Scene(root);
                reportStage.setScene(scene);
                reportStage.setTitle("Clientes");
                reportStage.show();
                    
                    
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    public void generarProducto(int proId){
            try{
            
                conexion = Conexion.getInstance().obtenerConexion();
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("proId", proId);
            
                InputStream jasperPath = GenerarReporte.class.getResourceAsStream("/org/luisDeleon/report/Producto.jasper");
                JasperPrint reporte = JasperFillManager.fillReport(jasperPath, parametros, conexion);
            
                Stage reportStage = new Stage();
            
                JRViewerFX reportViewer = new JRViewerFX(reporte);
            
                Pane root = new Pane();
            
                root.getChildren().add(reportViewer);
            
                reportViewer.setPrefSize(1000, 800);
            
                Scene scene = new Scene(root);
                reportStage.setScene(scene);
                reportStage.setTitle("Producto");
                reportStage.show();
                    
                    
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
}
