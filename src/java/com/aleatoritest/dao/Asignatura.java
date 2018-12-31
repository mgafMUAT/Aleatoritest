/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dao;

import com.aleatoritest.dto.MateriaDriver;
import java.io.Serializable;
import java.util.ArrayList;

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
    private ArrayList<Integer> materiaIds;
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

    public ArrayList<Integer> getMateriaIds() {
        return materiaIds;
    }

    public void setMateriaIds(ArrayList<Integer> materiaIds) {
        this.materiaIds = materiaIds;
    }

    public ArrayList<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(ArrayList<Materia> materiaList) {
        this.materiaList = materiaList;
    }
    
    public void makeMateriaList() {
        materiaList = new ArrayList<>(2);
        MateriaDriver pd = new MateriaDriver();
        materiaIds.forEach((materia) -> {
            materiaList.add(pd.buscarId(materia));
        });
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
