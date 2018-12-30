/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dto;

import com.aleatoritest.dto.driver.DaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MauricioGabriel
 */
public class JoinTableDriver {

    private static Connection conn;
    private final JoinTable jt;
    private final String jtName;

    public JoinTableDriver(JoinTable jt) {
        try {
            conn = DaoFactory.getConnection();
        } catch (SQLException ex) {
            printErr(ex);
        }
        this.jt = jt;
        this.jtName = jt.toString().toLowerCase();
    }

    private PreparedStatement executeSQL(boolean list, boolean first, int id) {
        String sql = list ? "select *" : "delete";
        sql += " from " + jtName + " where ";
        String val = idColumns()[first ? 0 : 1] + " = ?";
        sql += val;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            return pstm;
        } catch (SQLException ex) {
            printErr(ex);
            return null;
        }
    }

    private String[] idColumns() {
        switch (jt) {
            case PROFESORHASESTUDIANTE:
                return new String[]{"profesor_id", "ayudante_id"};
            case PREGUNTACOMPARTIDA:
                return new String[]{"pregunta_id", "usuario_id"};
            case PREGUNTAHASMATERIA:
                return new String[]{"pregunta_id", "materia_id"};
            default: //PRUEBAHASPREGUNTA:
                return new String[]{"prueba_id", "pregunta_id"};
        }
    }

    public ArrayList<Integer> listar(boolean first, int id) {
        PreparedStatement pstm = executeSQL(true, first, id);
        ArrayList<Integer> list = new ArrayList<>(2);
        try {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(first ? 1 : 2));
            }
        } catch (SQLException ex) {
            printErr(ex);
        }
        return list;
    }

    public boolean guardar(int id1, int id2) {
        String sql = "insert into " + jtName;
        String values = " values(?, ?)";
        sql = sql.concat(values);
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id1);
            pstm.setInt(1, id2);
            int update = pstm.executeUpdate();
            return update != 0;

        } catch (SQLException ex) {
            printErr(ex);
            return false;
        }
    }
    
    public boolean borrar(boolean first, int id) {
        PreparedStatement pstm = executeSQL(false, first, id);
        try {
            int update = pstm.executeUpdate();
            return update != 0;
        } catch (SQLException ex) {
            printErr(ex);
            return false;
        }
    }
    
    public boolean borrar(int id1, int id2) {
        String[] ids = idColumns();
        String sql = "delete from " + jtName + " where ";
        String cond = ids[0] + " = ? and " + ids[1] + " = ?";
        sql += cond;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id1);
            pstm.setInt(2, id2);
            int update = pstm.executeUpdate();
            return update != 0;

        } catch (SQLException ex) {
            printErr(ex);
            return false;
        }
    }

    private void printErr(SQLException ex) {
        System.out.println(ex);
        for (StackTraceElement stackEl : ex.getStackTrace()) {
            System.out.println("        " + stackEl);
        }
    }

}
