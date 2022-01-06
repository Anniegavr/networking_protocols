//package privacy.service.security.services.imap;
//
//import org.springframework.stereotype.Service;
//
//import javax.mail.*;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
////@Service
//public class EmailReceiver {
//
//    public void receiver() throws IOException, MessagingException {
//
//        FileInputStream fileInputStream = new FileInputStream("application.properties");
//        Properties properties = new Properties();
//        properties.load(fileInputStream);
//
//
//        String username = properties.getProperty("spring.mail.username");
//        String password = properties.getProperty("spring.mail.password");
//        String host = properties.getProperty("spring.mail.host");
//
//        Properties prop = new Properties();
//        prop.put("mail.store.protocol", "imaps");  //SSL
//        Store store = Session.getInstance(prop).getStore();
//        store.connect(host, username, password);
//        Folder inbox = store.getFolder("INBOX");
//        inbox.open(Folder.READ_WRITE);
//        System.out.println("Number of emails: "+inbox.getMessageCount());
//
//        WSMessage message = inbox.getMessage(inbox.getMessageCount());
//        Multipart multipart = (Multipart) message.getContent();
//        System.out.println(multipart.getContentType());
//
//        BodyPart body = multipart.getBodyPart(1);
//        System.out.println(body.getContent());
//
//        message.setFlag(Flags.Flag.SEEN, true);
//        inbox.close();
//    }
//}
