/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dao.clases;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Entity
@Table(name = "materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")
    , @NamedQuery(name = "Materia.findByMateriaId", query = "SELECT m FROM Materia m WHERE m.materiaPK.materiaId = :materiaId")
    , @NamedQuery(name = "Materia.findByAsignaturaId", query = "SELECT m FROM Materia m WHERE m.materiaPK.asignaturaId = :asignaturaId")
    , @NamedQuery(name = "Materia.findByNombre", query = "SELECT m FROM Materia m WHERE m.nombre = :nombre")})
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MateriaPK materiaPK;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "materiaList")
    private List<Pregunta> preguntaList;
    @JoinColumn(name = "asignatura_id", referencedColumnName = "asignatura_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materia")
    private List<Prueba> pruebaList;

    public Materia() {
    }

    public Materia(MateriaPK materiaPK) {
        this.materiaPK = materiaPK;
    }

    public Materia(MateriaPK materiaPK, String nombre) {
        this.materiaPK = materiaPK;
        this.nombre = nombre;
    }

    public Materia(int materiaId, int asignaturaId) {
        this.materiaPK = new MateriaPK(materiaId, asignaturaId);
    }

    public MateriaPK getMateriaPK() {
        return materiaPK;
    }

    public void setMateriaPK(MateriaPK materiaPK) {
        this.materiaPK = materiaPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
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

    @XmlTransient
    public List<Prueba> getPruebaList() {
        return pruebaList;
    }

    public void setPruebaList(List<Prueba> pruebaList) {
        this.pruebaList = pruebaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materiaPK != null ? materiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.materiaPK == null && other.materiaPK != null) || (this.materiaPK != null && !this.materiaPK.equals(other.materiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.Materia[ materiaPK=" + materiaPK + " ]";
    }
    
}
