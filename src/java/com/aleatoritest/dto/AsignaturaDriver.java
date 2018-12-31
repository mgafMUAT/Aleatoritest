/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dto;

import com.aleatoritest.dao.Asignatura;
import com.aleatoritest.dto.driver.DaoDriver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MauricioGabriel
 */
public class AsignaturaDriver extends DaoDriver<Asignatura> {

    public AsignaturaDriver() {
        super();
        super.attNum = 1;
        super.table = Asignatura.class;
    }
    
    

    @Override
    protected Asignatura map(ResultSet rs) throws SQLException {
        int asignatura_id = rs.getInt(1);
        String nombre = rs.getString(2);
        Asignatura nuevo = new Asignatura(asignatura_id, nombre);
        nuevo.setMateriaIds(new MateriaDriver().listarIds("asignatura_id", asignatura_id));
        return nuevo;
    }

    @Override
    protected PreparedStatement unMap(PreparedStatement pstm, Asignatura tabla, boolean id) throws SQLException {
        pstm.setString(1, tabla.getNombre());
        if (id) {
            pstm.setInt(2, tabla.getAsignaturaId());
        }
        return pstm;
    }
    
}
