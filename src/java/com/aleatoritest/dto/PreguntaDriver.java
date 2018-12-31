/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dto;

import com.aleatoritest.dao.Pregunta;
import com.aleatoritest.dao.Usuario;
import com.aleatoritest.dto.driver.DaoDriver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MauricioGabriel
 */
public class PreguntaDriver extends DaoDriver<Pregunta> {

    public PreguntaDriver() {
        super();
        super.attNum = 8;
        super.table = Pregunta.class;
    }

    @Override
    protected Pregunta map(ResultSet rs) throws SQLException {
        int pregunta_id = rs.getInt(1);
        Usuario usuario = new UsuarioDriver().buscarId(rs.getInt(2));
        String pregunta = rs.getString(3);
        String respuestaCorrecta = rs.getString(4);
        String alternativa1 = rs.getString(5);
        String alternativa2 = rs.getString(6);
        String alternativa3 = rs.getString(7);
        boolean esVisible = rs.getBoolean(8);
        java.util.Date fecha = rs.getDate(9);
        Pregunta nuevo = new Pregunta(pregunta_id, pregunta, respuestaCorrecta, alternativa1, alternativa2, alternativa3, esVisible, fecha);
        nuevo.setUsuario(usuario);
        nuevo.setUsuarioIds(new JoinTableDriver(JoinTable.PREGUNTACOMPARTIDA).listar(true, pregunta_id));
        nuevo.setMateriaIds(new MateriaDriver().listarIds("pregunta_id", pregunta_id));
        nuevo.setPruebaIds(new JoinTableDriver(JoinTable.PRUEBAHASPREGUNTA).listar(false, pregunta_id));
        return nuevo;
    }

    @Override
    protected PreparedStatement unMap(PreparedStatement pstm, Pregunta tabla, boolean id) throws SQLException {
        pstm.setInt(1, tabla.getUsuario().getUsuarioId());
        pstm.setString(2, tabla.getPregunta());
        pstm.setString(3, tabla.getRespuestaCorrecta());
        pstm.setString(4, tabla.getAlternativa1());
        pstm.setString(5, tabla.getAlternativa1());
        pstm.setString(6, tabla.getAlternativa1());
        pstm.setBoolean(7, tabla.getEsVisible());
        pstm.setDate(8, new java.sql.Date(tabla.getFecha().getTime()));
        if (id) {
            pstm.setInt(9, tabla.getPreguntaId());
        }
        return pstm;
    }
    
}
