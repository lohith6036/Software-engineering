/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class bookTest {
    
    public bookTest() {
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
     * Test of CalculateCost method, of class book.
     */
    @Test
    public void testCalculateCost() {
        System.out.println("CalculateCost");
        String TicketClass = "platinum";
        book instance = new book();
        //int expResult = 0;
        int result = instance.CalculateCost(TicketClass);
        assertEquals(20, result);
    }
    
}
