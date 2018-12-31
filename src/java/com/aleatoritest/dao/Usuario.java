/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dao;

import com.aleatoritest.dto.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author MauricioGabriel
 */
//@NamedQueries({
//    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
//    , @NamedQuery(name = "Usuario.findByUsuarioId", query = "SELECT u FROM Usuario u WHERE u.usuarioId = :usuarioId")
//    , @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")
//    , @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave")
//    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
//    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
//    , @NamedQuery(name = "Usuario.findByEsProfesor", query = "SELECT u FROM Usuario u WHERE u.esProfesor = :esProfesor")})
public class Usuario implements Serializable {

    private Integer usuarioId;
    private String correo;
    private String clave;
    private String nombre;
    private String apellidos;
    private boolean esProfesor;
    private ArrayList<Pregunta> preguntaList;
    private ArrayList<Integer> preguntaIds;
    private ArrayList<Usuario> usuarioList;
    private ArrayList<Integer> usuarioIds;
    private ArrayList<Prueba> pruebaList;
    private ArrayList<Integer> pruebaIds;

    public Usuario() {
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario(Integer usuarioId, String correo, String clave, String nombre, String apellidos, boolean esProfesor) {
        this.usuarioId = usuarioId;
        this.correo = correo;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.esProfesor = esProfesor;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean getEsProfesor() {
        return esProfesor;
    }

    public void setEsProfesor(boolean esProfesor) {
        this.esProfesor = esProfesor;
    }

    public ArrayList<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(ArrayList<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }
    
    public ArrayList<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(ArrayList<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
    public ArrayList<Prueba> getPruebaList() {
        return pruebaList;
    }

    public void setPruebaList(ArrayList<Prueba> pruebaList) {
        this.pruebaList = pruebaList;
    }

    public ArrayList<Integer> getPreguntaIds() {
        return preguntaIds;
    }

    public void setPreguntaIds(ArrayList<Integer> preguntaIds) {
        this.preguntaIds = preguntaIds;
    }

    public ArrayList<Integer> getUsuarioIds() {
        return usuarioIds;
    }

    public void setUsuarioIds(ArrayList<Integer> usuarioIds) {
        this.usuarioIds = usuarioIds;
    }

    public ArrayList<Integer> getPruebaIds() {
        return pruebaIds;
    }

    public void setPruebaIds(ArrayList<Integer> pruebaIds) {
        this.pruebaIds = pruebaIds;
    }
    
    public void makePreguntaList() {
        preguntaList = new ArrayList<>(2);
        PreguntaDriver pd = new PreguntaDriver();
        preguntaIds.forEach((preguntaId) -> {
            preguntaList.add(pd.buscarId(preguntaId));
        });
    }
    
    public void makeUsuarioList() {
        usuarioList = new ArrayList<>(2);
        UsuarioDriver pd = new UsuarioDriver();
        usuarioIds.forEach((usuario) -> {
            usuarioList.add(pd.buscarId(usuario));
        });
    }
    
    public void makePruebaList() {
        pruebaList = new ArrayList<>(2);
        PruebaDriver pd = new PruebaDriver();
        pruebaIds.forEach((pruebaId) -> {
            pruebaList.add(pd.buscarId(pruebaId));
        });
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return !((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId)));
    }

    @Override
    public String toString() {
        return "com.aleatoritest.dao.Usuario[ usuarioId=" + usuarioId + " ]";
    }
    
}
