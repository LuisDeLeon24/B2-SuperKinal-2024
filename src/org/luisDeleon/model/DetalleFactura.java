/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.model;

/**
 *
 * @author informatica
 */
public class DetalleFactura {
   private int detalleFacturaid;
   private int facturaId;
   private int productoId;

    public DetalleFactura() {
    }

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
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    @Override
    public String toString() {
        return "DetalleFactura{" + "detalleFacturaid=" + detalleFacturaid + ", facturaId=" + facturaId + ", productoId=" + productoId + '}';
    }
   
   
}
