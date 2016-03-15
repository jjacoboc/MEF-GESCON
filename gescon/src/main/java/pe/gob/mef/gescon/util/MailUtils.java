/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.util;

import java.util.Properties;
import java.util.ResourceBundle;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import pe.gob.mef.gescon.common.Parameters;

/**
 *
 * @author JJacobo
 */
public class MailUtils {

    public static void sendMail(String to, String subject, String body) {
        ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getMail());
        
        final String username = bundle.getString("from");
        final String password = bundle.getString("pass");

        Properties props = new Properties();
        props.put("mail.smtp.auth", bundle.getString("auth"));
        props.put("mail.smtp.starttls.enable", bundle.getString("starttls"));
        props.put("mail.smtp.host", bundle.getString("host"));
        props.put("mail.smtp.port", bundle.getString("port"));

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html");
            multipart.addBodyPart(messageBodyPart);
            
            DataSource fds = new FileDataSource("\\\\192.168.1.11\\gescon\\files\\bannerLogo.png");
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<banner>");            
            multipart.addBodyPart(messageBodyPart);
            
            DataSource ds = new FileDataSource("\\\\192.168.1.11\\gescon\\files\\boton.png");
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(ds));
            messageBodyPart.setHeader("Content-ID", "<boton>");
            multipart.addBodyPart(messageBodyPart);
         
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
