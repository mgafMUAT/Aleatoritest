/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest;

import com.aleatoritest.dao.Asignatura;
import com.aleatoritest.dto.AsignaturaDriver;

/**
 *
 * @author MauricioGabriel
 */
public class TestRun {
    public static void main(String[] args) {
        AsignaturaDriver ad = new AsignaturaDriver();
        Asignatura get = ad.listar().get(0);
        System.out.println(get.getAsignaturaId());
        System.out.println(get.getNombre());
        get.setNombre("Ciencias");
        System.out.println(ad.editar(get));
        get = ad.listar().get(0);
        System.out.println(get.getNombre());
    }
}
