/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dto;

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
        super.attNum = 1;
        super.table = Materia.class;
    }
    
    

    @Override
    protected Materia map(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String nombre = rs.getString(2);
        return new Materia(id, nombre);
    }

    @Override
    protected PreparedStatement unMap(PreparedStatement pstm, Materia tabla, boolean id) throws SQLException {
        pstm.setString(1, tabla.getNombre());
        if (id) {
            pstm.setInt(2, tabla.getMateria_id());
        }
        return pstm;
    }
    
}
