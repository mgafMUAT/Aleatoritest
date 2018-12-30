/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author MauricioGabriel
 */
//@NamedQueries({
//    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")
//    , @NamedQuery(name = "Materia.findByMateriaId", query = "SELECT m FROM Materia m WHERE m.materia_id.materiaId = :materiaId")
//    , @NamedQuery(name = "Materia.findByAsignaturaId", query = "SELECT m FROM Materia m WHERE m.materia_id.asignaturaId = :asignaturaId")
//    , @NamedQuery(name = "Materia.findByNombre", query = "SELECT m FROM Materia m WHERE m.nombre = :nombre")})
public class Materia implements Serializable {

    private Integer materia_id;
    private String nombre;
    private List<Pregunta> preguntaList;
    private Asignatura asignatura;
    private List<Prueba> pruebaList;

    public Materia() {
    }

    public Materia(Integer materia_id) {
        this.materia_id = materia_id;
    }

    public Materia(Integer materia_id, String nombre) {
        this.materia_id = materia_id;
        this.nombre = nombre;
    }

    public Integer getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(Integer materia_id) {
        this.materia_id = materia_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public List<Prueba> getPruebaList() {
        return pruebaList;
    }

    public void setPruebaList(List<Prueba> pruebaList) {
        this.pruebaList = pruebaList;
    }

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (materia_id != null ? materia_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        return !((this.materia_id == null && other.materia_id != null) || (this.materia_id != null && !this.materia_id.equals(other.materia_id)));
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.Materia[ materia_id=" + materia_id + " ]";
    }
    
}
