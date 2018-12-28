/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dto.driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MauricioGabriel
 * @param <T> Clase de una entidad de la base de datos
 */
public abstract class DaoDriver<T> {

    protected static Connection conn;
    protected String tableName;
    protected int attNum;//Sin contar el id

    public DaoDriver() {
        conn = DaoFactory.getConnection();
    }
    
    protected String listSQL() {
        String sql = "SELECT * FROM " + tableName;
        return sql;
    }
    
    protected String selectById() {
        String sql = listSQL() + " WHERE " + tableName + "_id = ?";
        return sql;
    }
    
    protected String insertSQL() {
        String values = " values(null";
        for (int i = 0; i < attNum; i++) {
            values += ", ?";
        }
        values += ")";
        String sql = "insert into " + tableName + values;
        return sql;
    }
    
    protected String deleteSQL() {
        String sql = "delete from " + tableName + " where " + tableName + "_id=?";
        return sql;
    }

    public T buscarId(int id) {
        T res = null;
        try {
            PreparedStatement pstm = conn.prepareStatement(selectById());
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                res = map(rs);
            }
        } catch (SQLException ex) {
            printErr(ex);
        }
        return res;
    }
    
    public abstract T buscar(String cond);

//    public TecProducto buscar(String proN, String proDescripcion, int proPrecio,
//            String proUltimaActualizacion);

    public abstract ArrayList<T> listar();

    public abstract boolean guardar(T nuevo);

    public abstract boolean editar(T mod);

    public abstract boolean borrar(int id);
    
    protected abstract T map(ResultSet rs) throws SQLException;
    
    protected abstract PreparedStatement unMap(PreparedStatement pstm, T tabla) throws SQLException;
    
    protected void printErr(Exception ex) {
        System.out.println(ex);
            for (StackTraceElement stackEl : ex.getStackTrace()) {
                System.out.println(stackEl);
            }
    }
}
