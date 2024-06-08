/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.model;

<<<<<<< HEAD
import java.sql.Date;

=======
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
/**
 *
 * @author informatica
 */
public class DetalleFactura {
<<<<<<< HEAD
   private int factura;
   private String producto;
   private String cliente;
   private Date fecha;
=======
   private int detalleFacturaid;
   private int facturaId;
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
   private int productoId;

    public DetalleFactura() {
    }

<<<<<<< HEAD
    public DetalleFactura(int factura, String producto, String cliente, Date fecha) {
        this.factura = factura;
        this.producto = producto;
        this.cliente = cliente;
        this.fecha = fecha;
    }

    public DetalleFactura(int factura, String producto, String cliente, Date fecha, int productoId) {
        this.factura = factura;
        this.producto = producto;
        this.cliente = cliente;
        this.fecha = fecha;
        this.productoId = productoId;
=======
    public DetalleFactura(int detalleFacturaid, int facturaId, int productoId) {
        this.detalleFacturaid = detalleFacturaid;
        this.facturaId = facturaId;
        this.productoId = productoId;
    }

    public int getDetalleFacturaid() {
        return detalleFacturaid;
    }

    public void setDetalleFacturaid(int detalleFacturaid) {
        this.detalleFacturaid = detalleFacturaid;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }
<<<<<<< HEAD
    
    

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getFactura() {
        return factura;
    }

    public String getProducto() {
        return producto;
    }

    public String getCliente() {
        return cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

    

   
    
    

=======

    @Override
    public String toString() {
        return "DetalleFactura{" + "detalleFacturaid=" + detalleFacturaid + ", facturaId=" + facturaId + ", productoId=" + productoId + '}';
    }
   
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
   
}
