import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mail_app{
static JFrame main_frame;
static JLabel Sender_mail,Sender_msg,Sender_subject,output_msg;
static JTextField send_mail_id,send_subject;
static JButton send;
static JTextArea send_msg;
static String reciever,sender="meghrajyadav.yadav20@gmail.com",subject,body;
static JFileChooser mail_file;
// static String reciever="mkyadav124@gmail.com", sender="meghrajyadav.yadav20@gmail.com", 
//     subject="only testing", body="This is message";
    public void mail_home()
    {
        main_frame=new JFrame("Mail Sender App");
        main_frame.setBounds(10, 100, 500, 550);

        Sender_mail=new JLabel("Sender Mail Id");
        Sender_mail.setBounds(20, 20, 150, 30);
        send_mail_id=new JTextField();
        send_mail_id.setBounds(180, 20, 200, 30);
        Sender_subject=new JLabel("Subject:- ");
        Sender_subject.setBounds(20, 80, 150, 30);
        send_subject=new JTextField();
        send_subject.setBounds(180, 80, 200, 30);
        Sender_msg=new JLabel("Message:- ");
        Sender_msg.setBounds(20, 150, 150, 30);
        send_msg=new JTextArea(4, 10);
        send_msg.setBounds(180, 140, 200, 150);
        send=new JButton("Send Mail");
        send.setBounds(180, 320, 150, 40);
        output_msg=new JLabel();
        output_msg.setBounds(30, 400, 150, 30);
        main_frame.add(Sender_mail);main_frame.add(Sender_msg);main_frame.add(Sender_subject);
        main_frame.add(send_mail_id);main_frame.add(send_msg);main_frame.add(send_subject);
        main_frame.add(send);main_frame.add(output_msg);
        main_frame.setLayout(null);
        main_frame.setVisible(true);

        ActionListener send_mail=new ActionListener() {
            public void actionPerformed(ActionEvent e){
                reciever=send_mail_id.getText();
                subject=send_subject.getText();
                body=send_msg.getText();
                if(!reciever.contains("@gmail.com"))
                {
                    output_msg.setText("please fill email correctly");
                }
                else
                {
                    send(reciever,sender,subject,body);
                }
                
            }
        };
        send.addActionListener(send_mail);
    }

    

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
            msg.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            msg.setText(text);

            Transport.send(msg);
            flag=true;
            output_msg.setText("Mail send Successfully!...");
            send_mail_id.setText("");
            send_subject.setText("");
            send_msg.setText("");

            
        } catch (Exception e) {
           System.out.println(e);
        }
        return flag;
    }

    public static void main(String[] args) 
    {
        Mail_app um=new Mail_app();
        um.mail_home();
    
    }
}