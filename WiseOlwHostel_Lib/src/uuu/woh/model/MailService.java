package uuu.woh.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.security.auth.login.Configuration;

/**
 *
 * @author Administrator
 */
public class MailService {

    public static void sendHelloMailWithLogo(String to, String htmlText) {
        if (to == null) {
            to = "musheng1986@gmail.com";
        }

        //以下為寄件所需的SMTP伺服器與帳號設定，這裡使用gmail的SMTP Server
        final String host = "smtp.gmail.com";
        final int port = 587;
        final String username = "seanwangfamily@gmail.com";
        final String password = "qjzntdhcsjkkgetf";//your password        
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            // 以下建立message物件作為mail的內容
            Message message = new MimeMessage(session);

            // Set 收件email: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("[Wise Owl Hostel] Thank you for booking Wise Owl Hostel ! ");

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)            
            BodyPart messageBodyPart = new MimeBodyPart();

            Map<String, Object> map = new HashMap<>();
            map.put("title", "This is your Reservation");
            String ipAddress = java.net.InetAddress.getLocalHost().getHostAddress();
            map.put("host", ipAddress);

//            String htmlText = "信件內容";
            messageBodyPart.setContent(htmlText, "text/html;charset=utf-8");
            // add it
            multipart.addBodyPart(messageBodyPart);

            // second part (the image)
//            String filename = "ftl/shoppingbag.png";
//            messageBodyPart = new MimeBodyPart();
            //取得網站上的圖檔
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            URL url = classLoader.getResource("/" + filename);
//            System.out.println("url = " + url);
//            String imagePath = filename;
//            if (url != null) {
//                imagePath = url.getPath();
//            }
//            System.out.println("imagePath = " + imagePath);
//
//            DataSource fds = new FileDataSource(imagePath);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setFileName(filename);
//            messageBodyPart.setHeader("Content-ID", "<image>");
            // add image to the multipart
            //multipart.addBodyPart(messageBodyPart);
            // put everything together
            message.setContent(multipart);
            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
        } catch (Exception ex) {
            System.out.println("ex = " + ex);
            if (ex.getCause() != null) {
                System.out.println("ex.getCause():" + ex.getCause());
            }
        }
    }

//    public static String processTemplateWithMap(String fileName, Map map) {
//        final String ftlDir = "ftl";
//        try {
//
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            URL url = classLoader.getResource("/" + ftlDir);
//            System.out.println("url = " + url);
//            String ftlPath = ftlDir;
//            if (url != null) {
//                ftlPath = url.getPath();
//            }
//            System.out.println("ftlPath = " + ftlPath);
//            File dir = new File(ftlPath);
//
//            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
//            cfg.setDirectoryForTemplateLoading(dir);
//            cfg.setDefaultEncoding("UTF-8");
//            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//            Template template = cfg.getTemplate(fileName);
//            try (StringWriter stringWriter = new StringWriter();) {
//                template.process(map, stringWriter);
//                System.out.println(stringWriter);
//                return stringWriter.toString();
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, "取得Email樣本檔案(" + fileName + ")失敗", ex);
//            throw new RuntimeException("取得Email樣本檔案(" + fileName + ")失敗", ex);
//        }
//    }
}
