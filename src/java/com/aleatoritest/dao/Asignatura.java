/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dao;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MauricioGabriel
 */
//@NamedQueries({
//    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a")
//    , @NamedQuery(name = "Asignatura.findByAsignaturaId", query = "SELECT a FROM Asignatura a WHERE a.asignaturaId = :asignaturaId")
//    , @NamedQuery(name = "Asignatura.findByNombre", query = "SELECT a FROM Asignatura a WHERE a.nombre = :nombre")})
public class Asignatura implements Serializable {

    private Integer asignaturaId;
    private String nombre;
    private ArrayList<Materia> materiaList;

    public Asignatura() {
    }

    public Asignatura(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public Asignatura(Integer asignaturaId, String nombre) {
        this.asignaturaId = asignaturaId;
        this.nombre = nombre;
    }

    public Integer getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public ArrayList<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(ArrayList<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignaturaId != null ? asignaturaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        return !((this.asignaturaId == null && other.asignaturaId != null) || (this.asignaturaId != null && !this.asignaturaId.equals(other.asignaturaId)));
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.Asignatura[ asignaturaId=" + asignaturaId + " ]";
    }

}
