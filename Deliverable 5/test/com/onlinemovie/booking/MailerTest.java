/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlinemovie.booking;

import java.util.List;
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
public class MailerTest {
    private static final String from = "kumar.lohithreddy@gmail.com";
    private static final String pass = "ajju6036";
    
    public MailerTest() {
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
     * Test of sendMail method, of class Mailer.
     */
    @Test
    public void testSendMail_3args_1() {
        System.out.println("sendMail");
        String mailRecipient = "kirankumarraju.g@gmail.com";
        String body = "abcdefg";
        String subject = "ablkjsfi";
        boolean expResult = true;
        boolean result = Mailer.sendMail(mailRecipient, body, subject);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendMail method, of class Mailer.
     */
    @Test
    public void testSendMail_3args_2() {
        System.out.println("sendMail");
        List<String> mailRecipients = null;
        String body = "";
        String subject = "";
        boolean expResult = false;
        boolean result = Mailer.sendMail(mailRecipients, body, subject);
        assertEquals(expResult, result);
    }
    
}
