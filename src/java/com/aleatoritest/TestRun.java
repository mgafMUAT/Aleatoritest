/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest;

import com.aleatoritest.dao.Usuario;
import com.aleatoritest.dto.UsuarioDriver;

/**
 *
 * @author MauricioGabriel
 */
public class TestRun {
    public static void main(String[] args) {
        UsuarioDriver ud = new UsuarioDriver();
        Usuario user = ud.buscarId(8);
        System.out.println(user.getUsuarioId());
        System.out.println(user.getCorreo());
        System.out.println(user.getClave());
        System.out.println(user.getNombre());
        System.out.println(user.getApellidos());
        System.out.println(user.getEsProfesor());
    }
}
