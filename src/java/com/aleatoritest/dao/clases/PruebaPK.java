/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dao.clases;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author MauricioGabriel
 */
@Embeddable
public class PruebaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "prueba_id")
    private int pruebaId;
    @Basic(optional = false)
    @Column(name = "usuario_id")
    private int usuarioId;
    @Basic(optional = false)
    @Column(name = "materia_id")
    private int materiaId;

    public PruebaPK() {
    }

    public PruebaPK(int pruebaId, int usuarioId, int materiaId) {
        this.pruebaId = pruebaId;
        this.usuarioId = usuarioId;
        this.materiaId = materiaId;
    }

    public int getPruebaId() {
        return pruebaId;
    }

    public void setPruebaId(int pruebaId) {
        this.pruebaId = pruebaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pruebaId;
        hash += (int) usuarioId;
        hash += (int) materiaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaPK)) {
            return false;
        }
        PruebaPK other = (PruebaPK) object;
        if (this.pruebaId != other.pruebaId) {
            return false;
        }
        if (this.usuarioId != other.usuarioId) {
            return false;
        }
        if (this.materiaId != other.materiaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.PruebaPK[ pruebaId=" + pruebaId + ", usuarioId=" + usuarioId + ", materiaId=" + materiaId + " ]";
    }
    
}
