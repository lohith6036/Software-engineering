package com.onlinemovie.booking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Username
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailer {

    // Sender's email ID and password needs to be mentioned
    private static final String from = "onlineticketbookingsystemg10@gmail.com";
    private static final String pass = "ABCDabcd1234";

    public static boolean sendMail(String mailRecipient, String body, String subject) {
        //Creating a result for getting status that messsage is delivered or not!
        String result;
        boolean response = false;

        // Defining the gmail host
        String host = "smtp.gmail.com";

        // Creating Properties object
        Properties props = new Properties();

        // Defining properties
        props.put("mail.smtp.host", host);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.user", from);
        props.put("mail.password", pass);
        props.put("mail.port", "465");
        // Authorized the Session object.
        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });

        
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(mailSession);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(mailRecipient));
            // Set Subject: header field
            message.setSubject(subject);
            // Now set the actual message
            message.setText(body);
            // Send message
            
            Transport.send(message);

            response = true;

        } catch (Exception mex) {

            mex.printStackTrace();
            result = "Error: unable to send mail....";

        }
        return response;
    }

    public static boolean sendMail(List<String> mailRecipients, String body, String subject) {
        //Creating a result for getting status that messsage is delivered or not!
        String result;
        boolean response = false;

        // Defining the gmail host
        String host = "smtp.gmail.com";

        // Creating Properties object
        Properties props = new Properties();

        // Defining properties
        props.put("mail.smtp.host", host);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.user", from);
        props.put("mail.password", pass);
        props.put("mail.port", "465");

        // Authorized the Session object.
        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(mailSession);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            for (String toMail : mailRecipients) {
                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(toMail));
                //message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("abc@abc.com,abc@def.com,ghi@abc.com"));
                // Set Subject: header field
                message.setSubject(subject);
                // Now set the actual message
                message.setText(body);
                // Send message
                Transport.send(message);

                response = true;
            }
        } catch (Exception mex) {

            mex.printStackTrace();
            result = "Error: unable to send mail....";

        }
        return response;
    }
}
