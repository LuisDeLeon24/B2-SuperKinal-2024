/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.model;

import java.sql.Date;

/**
 *
 * @author informatica
 */
public class DetalleFactura {
   private int factura;
   private String producto;
   private String cliente;
   private Date fecha;
   private int productoId;

    public DetalleFactura() {
    }

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
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }
    
    

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

    

    

   
    
    

   
}
