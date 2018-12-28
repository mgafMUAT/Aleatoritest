/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest;

import java.sql.*;

/**
 *
 * @author MauricioGabriel
 */
public class Validator {

    private static final String CONNECT = "jdbc:mysql://localhost:3306/aleatoritest";
    private static final String USER = "aleatoriuser";
    private static final String PASS = "aleatoritest";
    private static Connection con;
    private static Validator instance = null;

    public static Validator getInstance(){
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }
    
    private Validator() {
        try {
            //loading drivers for mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            //creating connection with the database
            con = DriverManager.getConnection(CONNECT, USER, PASS);
        } catch (ClassNotFoundException|SQLException e) {
            System.out.println(e);
            for (StackTraceElement stack : e.getStackTrace()) {
                System.out.println("-> " + stack);
            }
        }
    }

    public boolean checkUser(String email, String pass) {
        boolean st = false;
        try {
            PreparedStatement ps = con.prepareStatement("select * from usuario where correo=? and clave=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

        } catch (SQLException e) {
            System.out.println(e);
            for (StackTraceElement stack : e.getStackTrace()) {
                System.out.println("-> " + stack);
            }
        }
        return st;
    }
}
