/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlinemovie.booking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kiran
 */
public class ResetPasswordTest {
    
    public ResetPasswordTest() {
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
     * Test of doPost method, of class ResetPassword.
     */
    @Test
    public void generateRandomCode()throws Exception {
    System.out.println("Generate Random Code");
    String list = "0123456789";
    boolean expresult = true;
    String outputString = ResetPassword.generateRandomCode(list);
    boolean result=outputString!=null;
    assertEquals(expresult, result);
    }
    
}
