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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MauricioGabriel
 */
public class UsuarioDriver extends DaoDriver<Usuario> {

    public UsuarioDriver() {
        super();
        super.tableName = "usuario";
        super.attNum = 5;
    }

    @Override
    public Usuario buscar(String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean guardar(Usuario nuevo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(Usuario mod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrar(int id) {
        try {
            PreparedStatement pstm = conn.prepareStatement(super.deleteSQL());
            pstm.setInt(1, id);
        } catch (SQLException ex) {
            super.printErr(ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Usuario map(ResultSet rs) throws SQLException {
        int usuario_id = rs.getInt(1);
        String correo = rs.getString(2);
        String clave = rs.getString(3);
        String nombre = rs.getString(4);
        String apellidos = rs.getString(5);
        boolean esProfesor = rs.getBoolean(6);
        return new Usuario(usuario_id, correo, clave, nombre, apellidos, esProfesor);
    }

    @Override
    protected PreparedStatement unMap(PreparedStatement pstm, Usuario tabla) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
