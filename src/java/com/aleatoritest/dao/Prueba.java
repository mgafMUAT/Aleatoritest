/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MauricioGabriel
 */
//@NamedQueries({
//    @NamedQuery(name = "Prueba.findAll", query = "SELECT p FROM Prueba p")
//    , @NamedQuery(name = "Prueba.findByPruebaId", query = "SELECT p FROM Prueba p WHERE p.pruebaId.pruebaId = :pruebaId")
//    , @NamedQuery(name = "Prueba.findByUsuarioId", query = "SELECT p FROM Prueba p WHERE p.pruebaId.usuarioId = :usuarioId")
//    , @NamedQuery(name = "Prueba.findByMateriaId", query = "SELECT p FROM Prueba p WHERE p.pruebaId.materiaId = :materiaId")
//    , @NamedQuery(name = "Prueba.findByNombre", query = "SELECT p FROM Prueba p WHERE p.nombre = :nombre")
//    , @NamedQuery(name = "Prueba.findByCantidadPreguntas", query = "SELECT p FROM Prueba p WHERE p.cantidadPreguntas = :cantidadPreguntas")
//    , @NamedQuery(name = "Prueba.findByFecha", query = "SELECT p FROM Prueba p WHERE p.fecha = :fecha")})
public class Prueba implements Serializable {

    protected Integer pruebaId;
    private Usuario usuario;
    private Materia materia;
    private String nombre;
    private int cantidadPreguntas;
    private Date fecha;
    private List<Pregunta> preguntaList;

    public Prueba() {
    }

    public Prueba(Integer pruebaId) {
        this.pruebaId = pruebaId;
    }

    public Prueba(Integer pruebaId, String nombre, int cantidadPreguntas, Date fecha) {
        this.pruebaId = pruebaId;
        this.nombre = nombre;
        this.cantidadPreguntas = cantidadPreguntas;
        this.fecha = fecha;
    }

    public Integer getPruebaId() {
        return pruebaId;
    }

    public void setPruebaId(Integer pruebaId) {
        this.pruebaId = pruebaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadPreguntas() {
        return cantidadPreguntas;
    }

    public void setCantidadPreguntas(int cantidadPreguntas) {
        this.cantidadPreguntas = cantidadPreguntas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaId != null ? pruebaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prueba)) {
            return false;
        }
        Prueba other = (Prueba) object;
        return !((this.pruebaId == null && other.pruebaId != null) || (this.pruebaId != null && !this.pruebaId.equals(other.pruebaId)));
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.Prueba[ pruebaId=" + pruebaId + " ]";
    }
    
}
