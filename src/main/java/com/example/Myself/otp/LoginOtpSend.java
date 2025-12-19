package com.example.Myself.otp;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class LoginOtpSend {

    public static String emailOtpsendToCollege() {

        final String to = "karanwaghachoure95@gmail.com";
        final String from = "karanwaghachoure95@gmail.com";
        final String password = "jldarwszchovamzf"; // 🔐 Gmail App Password

        String otp = generateOTP(6);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject("Your OTP Code");
            message.setText(
                    "Hello,\n\nYour OTP is: " + otp +
                    "\n\nOTP is valid for 5 minutes.\n\nRegards,\nJijamata College"
            );

            Transport.send(message);
            System.out.println("✅ OTP SENT SUCCESSFULLY: " + otp);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return otp;
    }

    private static String generateOTP(int length) {
        Random rand = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(rand.nextInt(10));
        }
        return otp.toString();
    }
}
