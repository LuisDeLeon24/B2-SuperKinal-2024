/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luisDeleon.model;

/**
 *
 * @author Luis De León
 */
public class NivelAcceso {
    private int nivelccesoId;
    private String nivelAcceso;

    public NivelAcceso() {
    }

    public NivelAcceso(int nivelccesoId, String nivelAcceso) {
        this.nivelccesoId = nivelccesoId;
        this.nivelAcceso = nivelAcceso;
    }

    public int getNivelccesoId() {
        return nivelccesoId;
    }

    public void setNivelccesoId(int nivelccesoId) {
        this.nivelccesoId = nivelccesoId;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    @Override
    public String toString() {
        return "ID " + nivelccesoId + " | " + nivelAcceso;
    }
    
    
}
