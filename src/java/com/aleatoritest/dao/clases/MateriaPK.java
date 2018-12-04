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
public class MateriaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "materia_id")
    private int materiaId;
    @Basic(optional = false)
    @Column(name = "asignatura_id")
    private int asignaturaId;

    public MateriaPK() {
    }

    public MateriaPK(int materiaId, int asignaturaId) {
        this.materiaId = materiaId;
        this.asignaturaId = asignaturaId;
    }

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) materiaId;
        hash += (int) asignaturaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPK)) {
            return false;
        }
        MateriaPK other = (MateriaPK) object;
        if (this.materiaId != other.materiaId) {
            return false;
        }
        if (this.asignaturaId != other.asignaturaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.MateriaPK[ materiaId=" + materiaId + ", asignaturaId=" + asignaturaId + " ]";
    }
    
}
