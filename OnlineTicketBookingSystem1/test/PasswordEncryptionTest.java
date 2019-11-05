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
public class PasswordEncryptionTest {
    
    public PasswordEncryptionTest() {
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
     * Test of hashPassword method, of class PasswordEncryption.
     */
    @Test
    public void testHashPassword() {
        System.out.println("hashPassword");
        String in = "vynu121";
        String expResult = "38E9FCE1D33A469D8FA9B6D485AEC6AAF315FE60F12E67D21350991702750C34";
        String result = PasswordEncryption.hashPassword(in);
        assertEquals(expResult, result);
      
    }
    
}
