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
//    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p")
//    , @NamedQuery(name = "Pregunta.findByPreguntaId", query = "SELECT p FROM Pregunta p WHERE p.preguntaId.preguntaId = :preguntaId")
//    , @NamedQuery(name = "Pregunta.findByUsuarioId", query = "SELECT p FROM Pregunta p WHERE p.preguntaId.usuarioId = :usuarioId")
//    , @NamedQuery(name = "Pregunta.findByPregunta", query = "SELECT p FROM Pregunta p WHERE p.pregunta = :pregunta")
//    , @NamedQuery(name = "Pregunta.findByRespuestaCorrecta", query = "SELECT p FROM Pregunta p WHERE p.respuestaCorrecta = :respuestaCorrecta")
//    , @NamedQuery(name = "Pregunta.findByAlternativa1", query = "SELECT p FROM Pregunta p WHERE p.alternativa1 = :alternativa1")
//    , @NamedQuery(name = "Pregunta.findByAlternativa2", query = "SELECT p FROM Pregunta p WHERE p.alternativa2 = :alternativa2")
//    , @NamedQuery(name = "Pregunta.findByAlternativa3", query = "SELECT p FROM Pregunta p WHERE p.alternativa3 = :alternativa3")
//    , @NamedQuery(name = "Pregunta.findByEsVisible", query = "SELECT p FROM Pregunta p WHERE p.esVisible = :esVisible")
//    , @NamedQuery(name = "Pregunta.findByFecha", query = "SELECT p FROM Pregunta p WHERE p.fecha = :fecha")})
public class Pregunta implements Serializable {

    private Integer preguntaId;
    private String pregunta;
    private String respuestaCorrecta;
    private String alternativa1;
    private String alternativa2;
    private String alternativa3;
    private boolean esVisible;
    private Date fecha;
    private Usuario usuario;
    private List<Usuario> usuarioList;
    private List<Materia> materiaList;
    private List<Prueba> pruebaList;

    public Pregunta() {
    }

    public Pregunta(Integer preguntaId) {
        this.preguntaId = preguntaId;
    }

    public Pregunta(Integer preguntaId, String pregunta, String respuestaCorrecta, String alternativa1, String alternativa2, String alternativa3, boolean esVisible, Date fecha) {
        this.preguntaId = preguntaId;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.alternativa1 = alternativa1;
        this.alternativa2 = alternativa2;
        this.alternativa3 = alternativa3;
        this.esVisible = esVisible;
        this.fecha = fecha;
    }

    public Integer getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Integer preguntaId) {
        this.preguntaId = preguntaId;
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

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

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
        hash += (preguntaId != null ? preguntaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        return !((this.preguntaId == null && other.preguntaId != null) || (this.preguntaId != null && !this.preguntaId.equals(other.preguntaId)));
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.Pregunta[ preguntaId=" + preguntaId + " ]";
    }
    
}
