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
public class Cargo {
    private int cargoId;
    private String nombreCargo;
    private String descripcioCargo;

    public Cargo() {
    }

    public Cargo(int cargoId, String nombreCargo, String descripcioCargo) {
        this.cargoId = cargoId;
        this.nombreCargo = nombreCargo;
        this.descripcioCargo = descripcioCargo;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public String getDescripcioCargo() {
        return descripcioCargo;
    }

    public void setDescripcioCargo(String descripcioCargo) {
        this.descripcioCargo = descripcioCargo;
    }

    @Override
    public String toString() {
        return cargoId +" | "+ nombreCargo + " | "+ descripcioCargo;
    }
    
    
}
