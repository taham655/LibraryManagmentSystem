package com.example.librarymanagment.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class EMAIL {
    public static void email(String email, String subject, String text) {

                String from = ""; //enter your email
                String password = ""; //enter your password

                // Recipient's email ID needs to be mentioned.
                //String to = email;

                // Assuming you are sending email through Gmail's SMTP server
                String host = "smtp.gmail.com";

                // Get system properties
                Properties properties = System.getProperties();

                // Setup mail server
                properties.setProperty("mail.smtp.host", host);
                properties.setProperty("mail.smtp.auth", "true");
                properties.setProperty("mail.smtp.port", "587");
                properties.setProperty("mail.smtp.starttls.enable", "true");

                // Get the default Session object.
                Session session = Session.getInstance(properties,

                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(from, password);
                            }
                        });


                try {
                    // Create a default MimeMessage object.
                    MimeMessage message = new MimeMessage(session);

                    // Set From: header field of the header.
                    message.setFrom(new InternetAddress(from));

                    // Set To: header field of the header.
                    message.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(email));
                    // Set Subject: header field
                    message.setSubject(subject);

                    // Now set the actual message
                    message.setText(text);

                    // Send message
                    Transport.send(message);
                    System.out.println("Email sent successfully...");
                } catch (MessagingException mex) {
                    mex.printStackTrace();

                }
            }
        }




