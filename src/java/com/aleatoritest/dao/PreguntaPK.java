/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author MauricioGabriel
 */
@Embeddable
public class PreguntaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pregunta_id")
    private int preguntaId;
    @Basic(optional = false)
    @Column(name = "usuario_id")
    private int usuarioId;

    public PreguntaPK() {
    }

    public PreguntaPK(int preguntaId, int usuarioId) {
        this.preguntaId = preguntaId;
        this.usuarioId = usuarioId;
    }

    public int getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(int preguntaId) {
        this.preguntaId = preguntaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) preguntaId;
        hash += (int) usuarioId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaPK)) {
            return false;
        }
        PreguntaPK other = (PreguntaPK) object;
        if (this.preguntaId != other.preguntaId) {
            return false;
        }
        if (this.usuarioId != other.usuarioId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.PreguntaPK[ preguntaId=" + preguntaId + ", usuarioId=" + usuarioId + " ]";
    }
    
}
