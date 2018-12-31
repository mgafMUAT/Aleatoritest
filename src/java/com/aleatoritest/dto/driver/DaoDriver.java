/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dto.driver;

import java.lang.reflect.Field;
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
    protected int attNum;//Sin contar el id
    protected Class table;

    public DaoDriver() {
        try {
            conn = DaoFactory.getConnection();
        } catch (SQLException ex) {
            printErr(ex);
        }
    }

    protected String listSQL() {
        return "SELECT * FROM " + table.getSimpleName().toLowerCase();
    }
    
    protected String listId() {
        String tName = table.getSimpleName().toLowerCase();
        return "select " + tName + "_id from " + tName;
    }

    private String byId() {
        return " WHERE " + table.getSimpleName().toLowerCase() + "_id = ?";
    }

    protected String selectById() {
        String sql = listSQL() + byId();
        return sql;
    }

    protected String insertSQL() {
        String values = " values(null";
        for (int i = 0; i < attNum; i++) {
            values += ", ?";
        }
        values += ")";
        String sql = "insert into " + table.getSimpleName().toLowerCase() + values;
        return sql;
    }

    protected String editSQL() {
        String update = "update " + table.getSimpleName().toLowerCase();
        String set = " set ";
        Field[] fields = table.getDeclaredFields();
        for (int i = 1; i < attNum; i++) {
            set += fields[i].getName() + " = ?, ";
        }
        set += fields[attNum].getName() + " = ?";
        String sql = update + set + byId();
        return sql;
    }

    protected String deleteSQL() {
        String sql = "delete from " + table.getSimpleName().toLowerCase() + byId();
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

    public ArrayList<T> listar() {
        ArrayList<T> list = new ArrayList<>(1);
        try {
            PreparedStatement pstm = conn.prepareStatement(listSQL());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException ex) {
            printErr(ex);
        }
        return list;
    }
    
    public ArrayList<Integer> listarIds(String fcol, int f_id) {
        ArrayList<Integer> list = new ArrayList<>(1);
        String sql = listId() + " where " + fcol + " = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, f_id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            printErr(ex);
        }
        return list;
    }

    public boolean guardar(T nuevo) {
        try {
            PreparedStatement pstm = conn.prepareStatement(insertSQL());
            pstm = unMap(pstm, nuevo, false);
            int update = pstm.executeUpdate();
            return update != 0;

        } catch (SQLException ex) {
            printErr(ex);
            return false;
        }
    }

    public boolean editar(T mod) {
        try {
            PreparedStatement pstm = conn.prepareStatement(editSQL());
            pstm = unMap(pstm, mod, true);
            int update = pstm.executeUpdate();
            return update != 0;

        } catch (SQLException ex) {
            printErr(ex);
            return false;
        }
    }

    public boolean borrar(int id) {
        try {
            PreparedStatement pstm = conn.prepareStatement(deleteSQL());
            pstm.setInt(1, id);
            int update = pstm.executeUpdate();
            return update != 0;

        } catch (SQLException ex) {
            printErr(ex);
            return false;
        }
    }

    protected abstract T map(ResultSet rs) throws SQLException;

    protected abstract PreparedStatement unMap(PreparedStatement pstm, T tabla, boolean id) throws SQLException;

    protected void printErr(Exception ex) {
        System.out.println(ex);
        for (StackTraceElement stackEl : ex.getStackTrace()) {
            System.out.println("        " + stackEl);
        }
    }
}
