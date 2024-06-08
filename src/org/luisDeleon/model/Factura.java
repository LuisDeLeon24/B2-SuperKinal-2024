/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.model;

import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author informatica
 */
public class Factura {
    private int facturaId;
    private Date fecha;
    private Time hora;
    private int clienteId;
    private int empleadoId;
<<<<<<< HEAD
    private String cliente;
    private String empleado;
=======
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
    private double total;
    

    public Factura() {
    }

    public Factura(int facturaId, Date fecha, Time hora, int clienteId, int empleadoId, double total) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.hora = hora;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.total = total;
    }

    public Factura(int facturaId, Date fecha, Time hora, int clienteId, int empleadoId) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.hora = hora;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
    }

<<<<<<< HEAD

    public Factura(int facturaId, Date fecha, Time hora, String cliente, String empleado, double total) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.hora = hora;
        this.cliente = cliente;
        this.empleado = empleado;
        this.total = total;
    }
    
    
    
    

=======
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

<<<<<<< HEAD
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

=======
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
    
    
    
}
