/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dto;

import com.aleatoritest.dao.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MauricioGabriel
 */
public class UsuarioDriverTest {
    
    public UsuarioDriverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of map method, of class UsuarioDriver.
     */
    @Test
    public void testMap() throws Exception {
        System.out.println("map");
        ResultSet rs = null;
        UsuarioDriver instance = new UsuarioDriver();
        Usuario expResult = null;
        Usuario result = instance.map(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unMap method, of class UsuarioDriver.
     */
    @Test
    public void testUnMap() throws Exception {
        System.out.println("unMap");
        PreparedStatement pstm = null;
        Usuario tabla = null;
        UsuarioDriver instance = new UsuarioDriver();
        PreparedStatement expResult = null;
        PreparedStatement result = instance.unMap(pstm, tabla);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
