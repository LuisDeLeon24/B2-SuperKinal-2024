/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.model;

<<<<<<< HEAD
import java.sql.Date;

/**
 *
 * @author robin
 */
public class DetalleCompra extends Compra{
    private int detalleCompraId;
    private int cantidadCompra;
    private int productoId;
    private String producto;
    private int compraid;
    private String compra;
=======
/**
 *
 * @author informatica
 */
public class DetalleCompra {
    private int detalleCompraId;
    private int cantidadCompra;
    private int productoId;
    private int compraId;
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db

    public DetalleCompra() {
    }

<<<<<<< HEAD
    public DetalleCompra(int detalleCompraId, int cantidadCompra, int productoId, int compraid, int compraId,  Date fechaCompra, double totalCompra) {
        super(compraId, fechaCompra, totalCompra);
        this.detalleCompraId = detalleCompraId;
        this.cantidadCompra = cantidadCompra;
        this.productoId = productoId;
        this.compraid = compraid;
    }

    public DetalleCompra(int detalleCompraId, int cantidadCompra, String producto, String compra, int compraId, Date fechaCompra, double totalCompra) {
        super(compraId, fechaCompra, totalCompra);
        this.detalleCompraId = detalleCompraId;
        this.cantidadCompra = cantidadCompra;
        this.producto = producto;
        this.compra = compra;
    }

    public DetalleCompra(int cantidadCompra, String producto, int compraId, Date fechaCompra, double totalCompra) {
        super(compraId, fechaCompra, totalCompra);
        this.cantidadCompra = cantidadCompra;
        this.producto = producto;
    }

    public DetalleCompra(int cantidad, String producto, int compraId, String fecha, double total) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DetalleCompra(int compraId, String fecha, double total, int cantidad, String producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

=======
    public DetalleCompra(int detalleCompraId, int cantidadCompra, int productoId, int compraId) {
        this.detalleCompraId = detalleCompraId;
        this.cantidadCompra = cantidadCompra;
        this.productoId = productoId;
        this.compraId = compraId;
    }

>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
    public int getDetalleCompraId() {
        return detalleCompraId;
    }

    public void setDetalleCompraId(int detalleCompraId) {
        this.detalleCompraId = detalleCompraId;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

<<<<<<< HEAD
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCompraid() {
        return compraid;
    }

    public void setCompraid(int compraid) {
        this.compraid = compraid;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
=======
    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "ID " + detalleCompraId + " | " + cantidadCompra ;
    }
    
=======
        return "DetalleCompra{" + "detalleCompraId=" + detalleCompraId + ", cantidadCompra=" + cantidadCompra + ", productoId=" + productoId + ", compraId=" + compraId + '}';
    }
    
    
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
}
