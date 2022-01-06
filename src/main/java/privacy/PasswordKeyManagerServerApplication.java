package privacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.mail.*;
import javax.swing.text.html.HTML;
import java.io.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

@SpringBootApplication
public class PasswordKeyManagerServerApplication {


    public static void main(String[] args) throws MessagingException, IOException {

        SpringApplication.run(PasswordKeyManagerServerApplication.class, args);

        int messageIdx = 1;
        Scanner sc = new Scanner(System.in);
        boolean continueRead = true;
        while(continueRead){
            readLastMessage(messageIdx);
            messageIdx+=1;
            System.out.println("\nGet next message?  y/n");
            String ans = sc.nextLine().toString();
            if(Objects.equals(ans, "y")){
                System.out.println("Message index: "+messageIdx+"\n");
            }else{
                continueRead=false;
                System.out.println("Ok, see you next time.\n");
            }
        }
    }
//    public static void reading(int messageIdx) throws MessagingException, IOException {
//
//    }
    public static void readLastMessage(int idx) throws IOException, MessagingException {
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);


        String username = properties.getProperty("mail.user");
        String password = properties.getProperty("mail.password");
        String host = properties.getProperty("mail.host");

        Properties prop = new Properties();
        prop.put("mail.store.protocol", "imaps");  //SSL
        Store store = Session.getInstance(prop).getStore();
        store.connect(host, username, password);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        System.out.println("Number of emails: "+inbox.getMessageCount());

        Message message = inbox.getMessage(inbox.getMessageCount());
//        File emailFile = new File();

        if(message.isMimeType("text/plain")){
            FileWriter emailWriter = new FileWriter("N:\\MINE\\PR\\Lab2\\email_file.txt");
            emailWriter.write(message.getContent().toString());
            emailWriter.close();
            System.out.println("New email: "+ message.getContent().toString());

        }else{
            Multipart multipart = (Multipart) message.getContent();
            BodyPart body = multipart.getBodyPart(idx);
            FileWriter emailWriter = new FileWriter("N:\\MINE\\PR\\Lab2\\email_file.html");
            emailWriter.write(body.getContent().toString());
            emailWriter.close();
            System.out.println("Check for html file\n");
        }


//        System.out.println(body.getContent());

        message.setFlag(Flags.Flag.SEEN, true);
        inbox.close();
    }
}
