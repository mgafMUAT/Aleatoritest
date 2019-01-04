/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dto;

import com.aleatoritest.dao.Materia;
import com.aleatoritest.dao.Prueba;
import com.aleatoritest.dao.Usuario;
import com.aleatoritest.dto.driver.DaoDriver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MauricioGabriel
 */
public class PruebaDriver extends DaoDriver<Prueba> {

    public PruebaDriver() {
        super();
        super.attNum = 5;
        super.table = Prueba.class;
    }

    @Override
    protected String SQLfields(boolean and) {
        String sql = super.SQLfields(and);
        sql = sql.replace("usuario", "usuario_id");
        sql = sql.replace("materia", "materia_id");
        return sql;
    }

    @Override
    protected Prueba map(ResultSet rs) throws SQLException {
        int prueba_id = rs.getInt(1);
        Usuario usuario = new UsuarioDriver().buscarId(rs.getInt(2));
        Materia materia = new MateriaDriver().buscarId(rs.getInt(3));
        String nombre = rs.getString(4);
        int cantidadPreguntas = rs.getInt(5);
        java.util.Date fecha = rs.getDate(6);
        Prueba nuevo = new Prueba(prueba_id, nombre, cantidadPreguntas, fecha);
        nuevo.setUsuario(usuario);
        nuevo.setMateria(materia);
        nuevo.setPreguntaIds(new JoinTableDriver(JoinTable.PRUEBAHASPREGUNTA).listar(true, prueba_id));
        return nuevo;
    }

    @Override
    protected PreparedStatement unMap(PreparedStatement pstm, Prueba tabla, boolean id) throws SQLException {
        pstm.setInt(1, tabla.getUsuario().getUsuarioId());
        pstm.setInt(2, tabla.getMateria().getMateriaId());
        pstm.setString(3, tabla.getNombre());
        pstm.setInt(4, tabla.getCantidadPreguntas());
        pstm.setDate(5, new java.sql.Date(tabla.getFecha().getTime()));
        if (id) {
            pstm.setInt(6, tabla.getPruebaId());
        }
        return pstm;
    }
    
}
