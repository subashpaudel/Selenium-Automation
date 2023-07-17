package org.F1Soft.Helpers;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class EmailHelper {
    public static void  sendMail() throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true");
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("qaautomation0232@gmail.com", "P@ssword@123");
                    }
                });
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("qaautomation0232@gmail.com"));
            msg.setRecipients(RecipientType.TO, "qaautomation0232@gmail.com");
            msg.setSubject(" automation report");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();



            ///Html
            MimeBodyPart htmlPart = new MimeBodyPart();
            String target = System.getProperty("user.dir")+"/target";
            FileReader fr=new FileReader(target+"/surefire-reports/emailable-report.html");
            BufferedReader br= new BufferedReader(fr);
            StringBuilder content=new StringBuilder(1024);
            String s;
            while(( s=br.readLine())!=null)
            {
                content.append(s);
            }
            htmlPart.setContent(content.toString(), "text/html");
            multipart.addBodyPart(htmlPart);

            //Attachment
            MimeBodyPart attachementPart = new MimeBodyPart();
            attachementPart.attachFile(new File( target+"/logs/application.html"));
            multipart.addBodyPart(attachementPart);

            msg.setContent(multipart);

            Transport.send(msg);
            System.out.println("---Done---");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
