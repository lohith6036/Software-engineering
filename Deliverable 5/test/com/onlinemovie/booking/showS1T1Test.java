/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlinemovie.booking;

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
public class showS1T1Test {
    
    public showS1T1Test() {
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
     * Test of bookNew method, of class showS1T1.
     */
    @Test
    public void testBookNew() {
        System.out.println("bookNew");
        int num = 10;
        showS1T1 test = new showS1T1(null, null, 0, null);
        //boolean expResult = true;
        boolean result = test.bookNew(num);
        assertEquals(true, result);

    }
    
}
