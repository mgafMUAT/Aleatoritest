/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dto;

import com.aleatoritest.dao.Asignatura;
import com.aleatoritest.dao.Materia;
import com.aleatoritest.dto.driver.DaoDriver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MauricioGabriel
 */
public class MateriaDriver extends DaoDriver<Materia> {

    public MateriaDriver() {
        super();
        super.attNum = 2;
        super.table = Materia.class;
    }
    
    

    @Override
    protected Materia map(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        Asignatura asignatura = new AsignaturaDriver().buscarId(rs.getInt(2));
        String nombre = rs.getString(3);
        Materia nuevo = new Materia(id, nombre, asignatura);
        
        return nuevo;
    }

    @Override
    protected PreparedStatement unMap(PreparedStatement pstm, Materia tabla, boolean id) throws SQLException {
        pstm.setInt(1, tabla.getAsignatura().getAsignaturaId());
        pstm.setString(2, tabla.getNombre());
        if (id) {
            pstm.setInt(3, tabla.getMateriaId());
        }
        return pstm;
    }
    
}
