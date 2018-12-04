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
@Table(name = "pregunta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p")
    , @NamedQuery(name = "Pregunta.findByPreguntaId", query = "SELECT p FROM Pregunta p WHERE p.preguntaPK.preguntaId = :preguntaId")
    , @NamedQuery(name = "Pregunta.findByUsuarioId", query = "SELECT p FROM Pregunta p WHERE p.preguntaPK.usuarioId = :usuarioId")
    , @NamedQuery(name = "Pregunta.findByPregunta", query = "SELECT p FROM Pregunta p WHERE p.pregunta = :pregunta")
    , @NamedQuery(name = "Pregunta.findByRespuestaCorrecta", query = "SELECT p FROM Pregunta p WHERE p.respuestaCorrecta = :respuestaCorrecta")
    , @NamedQuery(name = "Pregunta.findByAlternativa1", query = "SELECT p FROM Pregunta p WHERE p.alternativa1 = :alternativa1")
    , @NamedQuery(name = "Pregunta.findByAlternativa2", query = "SELECT p FROM Pregunta p WHERE p.alternativa2 = :alternativa2")
    , @NamedQuery(name = "Pregunta.findByAlternativa3", query = "SELECT p FROM Pregunta p WHERE p.alternativa3 = :alternativa3")
    , @NamedQuery(name = "Pregunta.findByEsVisible", query = "SELECT p FROM Pregunta p WHERE p.esVisible = :esVisible")
    , @NamedQuery(name = "Pregunta.findByFecha", query = "SELECT p FROM Pregunta p WHERE p.fecha = :fecha")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreguntaPK preguntaPK;
    @Basic(optional = false)
    @Column(name = "pregunta")
    private String pregunta;
    @Basic(optional = false)
    @Column(name = "respuestaCorrecta")
    private String respuestaCorrecta;
    @Basic(optional = false)
    @Column(name = "alternativa1")
    private String alternativa1;
    @Basic(optional = false)
    @Column(name = "alternativa2")
    private String alternativa2;
    @Basic(optional = false)
    @Column(name = "alternativa3")
    private String alternativa3;
    @Basic(optional = false)
    @Column(name = "esVisible")
    private boolean esVisible;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinTable(name = "preguntacompartida", joinColumns = {
        @JoinColumn(name = "pregunta_id", referencedColumnName = "pregunta_id")}, inverseJoinColumns = {
        @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @JoinTable(name = "preguntahasmateria", joinColumns = {
        @JoinColumn(name = "pregunta_id", referencedColumnName = "pregunta_id")}, inverseJoinColumns = {
        @JoinColumn(name = "materia_id", referencedColumnName = "materia_id")})
    @ManyToMany
    private List<Materia> materiaList;
    @ManyToMany(mappedBy = "preguntaList")
    private List<Prueba> pruebaList;
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Pregunta() {
    }

    public Pregunta(PreguntaPK preguntaPK) {
        this.preguntaPK = preguntaPK;
    }

    public Pregunta(PreguntaPK preguntaPK, String pregunta, String respuestaCorrecta, String alternativa1, String alternativa2, String alternativa3, boolean esVisible, Date fecha) {
        this.preguntaPK = preguntaPK;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.alternativa1 = alternativa1;
        this.alternativa2 = alternativa2;
        this.alternativa3 = alternativa3;
        this.esVisible = esVisible;
        this.fecha = fecha;
    }

    public Pregunta(int preguntaId, int usuarioId) {
        this.preguntaPK = new PreguntaPK(preguntaId, usuarioId);
    }

    public PreguntaPK getPreguntaPK() {
        return preguntaPK;
    }

    public void setPreguntaPK(PreguntaPK preguntaPK) {
        this.preguntaPK = preguntaPK;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getAlternativa1() {
        return alternativa1;
    }

    public void setAlternativa1(String alternativa1) {
        this.alternativa1 = alternativa1;
    }

    public String getAlternativa2() {
        return alternativa2;
    }

    public void setAlternativa2(String alternativa2) {
        this.alternativa2 = alternativa2;
    }

    public String getAlternativa3() {
        return alternativa3;
    }

    public void setAlternativa3(String alternativa3) {
        this.alternativa3 = alternativa3;
    }

    public boolean getEsVisible() {
        return esVisible;
    }

    public void setEsVisible(boolean esVisible) {
        this.esVisible = esVisible;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @XmlTransient
    public List<Prueba> getPruebaList() {
        return pruebaList;
    }

    public void setPruebaList(List<Prueba> pruebaList) {
        this.pruebaList = pruebaList;
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
        hash += (preguntaPK != null ? preguntaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.preguntaPK == null && other.preguntaPK != null) || (this.preguntaPK != null && !this.preguntaPK.equals(other.preguntaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.Pregunta[ preguntaPK=" + preguntaPK + " ]";
    }
    
}
