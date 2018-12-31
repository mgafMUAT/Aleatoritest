/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dto;

import com.aleatoritest.dao.Usuario;
import com.aleatoritest.dto.driver.DaoDriver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MauricioGabriel
 */
public class UsuarioDriver extends DaoDriver<Usuario> {

    public UsuarioDriver() {
        super();
        super.table = Usuario.class;
        super.attNum = 5;
    }
    
    public boolean validar(String correo, String clave) {
        String sql = listSQL() + " where correo = ? and clave = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, correo);
            pstm.setString(2, clave);
            ResultSet rs = pstm.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            printErr(ex);
            return false;
        }
    }

    @Override
    protected Usuario map(ResultSet rs) throws SQLException {
        int usuario_id = rs.getInt(1);
        String correo = rs.getString(2);
        String clave = rs.getString(3);
        String nombre = rs.getString(4);
        String apellidos = rs.getString(5);
        boolean esProfesor = rs.getBoolean(6);
        Usuario nuevo = new Usuario(usuario_id, correo, clave, nombre, apellidos, esProfesor);
        nuevo.setUsuarioIds(new JoinTableDriver(JoinTable.PROFESORHASAYUDANTE).listar(esProfesor, usuario_id));
        return nuevo;
    }

    @Override
    protected PreparedStatement unMap(PreparedStatement pstm, Usuario tabla, boolean id) throws SQLException {
        pstm.setString(1, tabla.getCorreo());
        pstm.setString(2, tabla.getClave());
        pstm.setString(3, tabla.getNombre());
        pstm.setString(4, tabla.getApellidos());
        pstm.setBoolean(5, tabla.getEsProfesor());
        if (id) {
            pstm.setInt(6, tabla.getUsuarioId());
        }
        return pstm;
    }

}
