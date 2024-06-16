/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.controller;

import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.luisDeleon.dao.Conexion;
import org.luisDeleon.model.Cliente;
import org.luisDeleon.model.DetalleFactura;
import org.luisDeleon.model.Empleado;
import org.luisDeleon.model.Factura;
import org.luisDeleon.model.Producto;
import org.luisDeleon.reports.GenerarReporte;
import org.luisDeleon.system.Main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuFacturasController implements Initializable {
    private Main stage;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    Button btnRegresar,btnEliminar,btnGuardar,btnBuscar,btnVaciar,btnPlus,btnVer;
    
    @FXML
    TextField tfTotal,tfHora,tfBuscar,tfFactura;
    
    @FXML
    ComboBox cmbProducto,cmbEmpleado,cmbCliente;
    
    @FXML
    TableColumn colFacturaId,colFecha,colHora,colCliente,colEmpleado,colTotal;
    
    @FXML
    TableView tblFacturas;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnRegresar){
            stage.menuPrinciapalView();
        }else if(event.getSource() == btnVer){
            GenerarReporte.getInstance().generarFactura(Integer.parseInt(tfFactura.getText()));
        }else if(event.getSource() == btnVaciar){
            vaciarCampos();
        }else if(event.getSource() == btnGuardar){
            if(tfFactura.getText().isEmpty()){
                agregarFacturas();
                cargarDatos();
            } else {
                //agregarDetalleFactura();
                cargarDatos();
                vaciarCampos();
            }
        }else if(event.getSource() == btnBuscar){
            tblFacturas.getItems().clear();
            if(tfBuscar.getText().equals("")){
                cargarDatos();
            }else{
                tblFacturas.getItems().add(buscarFactura());
                colFacturaId.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("facturaId"));
                colFecha.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Date>("fecha"));
                colHora.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Time>("hora"));
                colCliente.setCellValueFactory(new PropertyValueFactory<DetalleFactura, String>("cliente"));
                colEmpleado.setCellValueFactory(new PropertyValueFactory<DetalleFactura, String>("empleado"));
                colTotal.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Double>("total"));
            }
        }else if(event.getSource() == btnPlus){
            stage.DetalleFacturasView();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbEmpleado.setItems(listarEmpleados());
        cmbCliente.setItems(listarClientes());
        cmbProducto.setItems(listarProductos());
        cargarDatos();
    } 

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public void vaciarCampos(){
        tfFactura.clear();
        tfTotal.clear();
        tfHora.clear();
        cmbCliente.getSelectionModel().clearSelection();
        cmbEmpleado.getSelectionModel().clearSelection();
        cmbProducto.getSelectionModel().clearSelection();
        
    }
    
    public void cargarDatosEditar(){
        Factura fa = (Factura)tblFacturas.getSelectionModel().getSelectedItem();
        if(fa != null){
            tfFactura.setText(Integer.toString(fa.getFacturaId()));
            cmbCliente.getSelectionModel().select(obtenerIndexCliente());
            cmbEmpleado.getSelectionModel().select(obtenerIndexEmpleado());
        }
    }
    
    public void cargarDatos(){
        tblFacturas.setItems(listarFacturas());
        colFacturaId.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("facturaId"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Factura, Date>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<Factura, Time>("hora"));
        colCliente.setCellValueFactory(new PropertyValueFactory<Factura, String>("cliente"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Factura, String>("empleado"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Factura, Double>("total"));
        tblFacturas.getSortOrder().add(colFacturaId);
    }
    
    public ObservableList<Factura> listarFacturas(){
        ArrayList<Factura> factura = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarFacturas()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int facturaId = resultSet.getInt("facturaId");
                Date fecha = resultSet.getDate("fecha");
                Time hora = resultSet.getTime("hora");
                String clienteN = resultSet.getString("nombreCliente");
                String empleadoN = resultSet.getString("nombreEmpleado");
                double total = resultSet.getDouble("total");
                factura.add(new Factura(facturaId, fecha, hora,clienteN,empleadoN,total));
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
        
        return FXCollections.observableList(factura);
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
    
    public ObservableList<Cliente>listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarClientes()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int clienteid = resultSet.getInt("clienteid");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                String nit = resultSet.getString("nit");
                
                clientes.add(new Cliente(clienteid,nombre,apellido,telefono,direccion,nit));
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
        return FXCollections.observableList(clientes); 
    }
    
    public ObservableList<Producto>listarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarProducto()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int productoId = resultSet.getInt("productoId");
                String nombreProducto = resultSet.getString("nombreProducto");
                String descripcionProducto = resultSet.getString("descripcionProducto");
                int cantidadStock = resultSet.getInt("cantidadStock");
                double precioVentaUnitario = resultSet.getDouble("precioVentaUnitario");
                double precioVentaMayor = resultSet.getDouble("precioVentaMayor");
                double precioCompra = resultSet.getDouble("precioCompra");
                Blob imagenProducto = resultSet.getBlob("imagenProducto");
                String distribuidor = resultSet.getString("distribuidorId");
                String categoriaProductos = resultSet.getString("categoriaProductosId");
                productos.add(new Producto(productoId,nombreProducto,descripcionProducto,cantidadStock,precioVentaUnitario,precioVentaMayor,precioCompra,imagenProducto,distribuidor,categoriaProductos));
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
        return FXCollections.observableArrayList(productos);
    }
    
    public int obtenerIndexCliente(){
        int index = 0;
        for(int i = 0 ; i <  cmbCliente.getItems().size() ; i++){
            String clienteCmb = cmbCliente.getItems().get(i).toString();
            String clienteTbl = ((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getCliente();
            if(clienteCmb.equals(clienteTbl)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    public int obtenerIndexEmpleado(){
        int index = 0;
        for(int i = 0 ; i < cmbEmpleado.getItems().size() ; i++){
            String empleadoCmb = cmbEmpleado.getItems().get(i).toString();
            int empleadoTbl = ((Factura)tblFacturas.getSelectionModel().getSelectedItem()).getEmpleadoId();
            if(empleadoCmb.equals(empleadoTbl)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    public int obtenerIndexProducto(){
        int index = 0;
        for(int i = 0 ; i < cmbProducto.getItems().size() ; i++){
            String productoCmb = cmbProducto.getItems().get(i).toString();
            int productoTbl = ((DetalleFactura)tblFacturas.getSelectionModel().getSelectedItem()).getProductoId();
            if(productoCmb.equals(productoTbl)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    public void agregarFacturas(){
        LocalTime horaActual = LocalTime.now();
        Time hora = java.sql.Time.valueOf(horaActual);
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarFacturas(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(2, ((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.setInt(3, ((Producto)cmbProducto.getSelectionModel().getSelectedItem()).getProductoId());
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
    
    public Factura buscarFactura(){
        Factura factura = null;
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarFactura(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfBuscar.getText()));
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int facturaId = resultSet.getInt("facturaId");
                Date fecha = resultSet.getDate("fecha");
                Time hora = resultSet.getTime("hora");
                double total = resultSet.getDouble("total");
                String cliente = resultSet.getString("nombreCliente");
                String empleado = resultSet.getString("nombreEmpleado");
                
                factura = (new Factura(facturaId,fecha,hora,cliente,empleado,total));
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
              System.out.println(e.getMessage());
            }
        }
        
        return factura;
    }
}
