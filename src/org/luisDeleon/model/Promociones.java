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
public class Promociones {
    private int promociones;
    private double precioPromocion;
    private String descripcionPromocion;
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private int productoId;

    public Promociones() {
    }

    public Promociones(int promociones, double precioPromocion, String descripcionPromocion, Date fechaInicio, Date fechaFinalizacion, int productoId) {
        this.promociones = promociones;
        this.precioPromocion = precioPromocion;
        this.descripcionPromocion = descripcionPromocion;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.productoId = productoId;
    }

    public int getPromociones() {
        return promociones;
    }

    public void setPromociones(int promociones) {
        this.promociones = promociones;
    }

    public double getPrecioPromocion() {
        return precioPromocion;
    }

    public void setPrecioPromocion(double precioPromocion) {
        this.precioPromocion = precioPromocion;
    }

    public String getDescripcionPromocion() {
        return descripcionPromocion;
    }

    public void setDescripcionPromocion(String descripcionPromocion) {
        this.descripcionPromocion = descripcionPromocion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    @Override
    public String toString() {
        return "Promociones{" + "promociones=" + promociones + ", precioPromocion=" + precioPromocion + ", descripcionPromocion=" + descripcionPromocion + ", fechaInicio=" + fechaInicio + ", fechaFinalizacion=" + fechaFinalizacion + ", productoId=" + productoId + '}';
    }
    
    
}
