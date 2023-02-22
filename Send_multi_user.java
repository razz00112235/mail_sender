import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;


public class Send_multi_user{

    static String reciever="meghrajyadav.yadav20@gmail.com,meghrajyadav.yadav20@gmail.com", sender="meghrajyadav.yadav20@gmail.com", 
    subject="only testing", body="This is message";
 
    static boolean send(String to, String from, String sub, String text)
    {
        boolean flag=false;
        Properties prop=new Properties();
        prop.put("mail.smtp.port",587);
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        
        String uname="meghrajyadav.yadav20";
        String pass="yoyxjhbabzhddzqb";

        Session session=Session.getInstance(prop,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(uname, pass);
            }
        });
        try {
           
            Message msg=new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setSubject(sub);

            String[] recipientList = reciever.split(",");
            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient : recipientList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }
            msg.setRecipients(Message.RecipientType.TO, recipientAddress);


            // msg.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //msg.setText(text);
             // This mail has 2 part, the BODY and the embedded image
         MimeMultipart multipart = new MimeMultipart("related");

         // first part (the html)
         BodyPart messageBodyPart = new MimeBodyPart();
         String htmlText = "<H1>Hello</H1>";
         messageBodyPart.setContent(htmlText, "text/html");
         // add it
         multipart.addBodyPart(messageBodyPart);
         String filename = "User_Mail.java";
         // second part (the image)
         messageBodyPart = new MimeBodyPart();        
         DataSource fds = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(fds));
         messageBodyPart.setFileName(filename);

         // add image to the multipart
         multipart.addBodyPart(messageBodyPart);

         // put everything together
         msg.setContent(multipart);

            Transport.send(msg);
            flag=true;

            
        } catch (Exception e) {
           System.out.println(e);
        }
        return flag;
    }

    public static void main(String[] args) 
    {
      boolean status= send(reciever,sender,subject,body);
      if(status){
        System.out.println("Email send successfully");
      }
      else{System.out.println("There is some issue");}
    }
}