/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dao.clases;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MauricioGabriel
 */
@Entity
@Table(name = "prueba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prueba.findAll", query = "SELECT p FROM Prueba p")
    , @NamedQuery(name = "Prueba.findByPruebaId", query = "SELECT p FROM Prueba p WHERE p.pruebaPK.pruebaId = :pruebaId")
    , @NamedQuery(name = "Prueba.findByUsuarioId", query = "SELECT p FROM Prueba p WHERE p.pruebaPK.usuarioId = :usuarioId")
    , @NamedQuery(name = "Prueba.findByMateriaId", query = "SELECT p FROM Prueba p WHERE p.pruebaPK.materiaId = :materiaId")
    , @NamedQuery(name = "Prueba.findByNombre", query = "SELECT p FROM Prueba p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Prueba.findByCantidadPreguntas", query = "SELECT p FROM Prueba p WHERE p.cantidadPreguntas = :cantidadPreguntas")
    , @NamedQuery(name = "Prueba.findByFecha", query = "SELECT p FROM Prueba p WHERE p.fecha = :fecha")})
public class Prueba implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruebaPK pruebaPK;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "cantidadPreguntas")
    private int cantidadPreguntas;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinTable(name = "pruebahaspregunta", joinColumns = {
        @JoinColumn(name = "prueba_id", referencedColumnName = "prueba_id")}, inverseJoinColumns = {
        @JoinColumn(name = "pregunta_id", referencedColumnName = "pregunta_id")})
    @ManyToMany
    private List<Pregunta> preguntaList;
    @JoinColumn(name = "materia_id", referencedColumnName = "materia_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Materia materia;
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Prueba() {
    }

    public Prueba(PruebaPK pruebaPK) {
        this.pruebaPK = pruebaPK;
    }

    public Prueba(PruebaPK pruebaPK, String nombre, int cantidadPreguntas, Date fecha) {
        this.pruebaPK = pruebaPK;
        this.nombre = nombre;
        this.cantidadPreguntas = cantidadPreguntas;
        this.fecha = fecha;
    }

    public Prueba(int pruebaId, int usuarioId, int materiaId) {
        this.pruebaPK = new PruebaPK(pruebaId, usuarioId, materiaId);
    }

    public PruebaPK getPruebaPK() {
        return pruebaPK;
    }

    public void setPruebaPK(PruebaPK pruebaPK) {
        this.pruebaPK = pruebaPK;
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

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaPK != null ? pruebaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prueba)) {
            return false;
        }
        Prueba other = (Prueba) object;
        if ((this.pruebaPK == null && other.pruebaPK != null) || (this.pruebaPK != null && !this.pruebaPK.equals(other.pruebaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.Prueba[ pruebaPK=" + pruebaPK + " ]";
    }
    
}
