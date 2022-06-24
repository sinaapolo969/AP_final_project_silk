package Model.Person.Admin;


//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import Model.PageControl;
import Model.Person.User.User;

import java.io.IOException;
import java.util.*;

import static java.lang.System.out;


public class Admin {
    private static AdminStruct admin = new AdminStruct("Admin", "1234", "silkroadhelpu@gmail.com");

    public static String[] states = new String[]{"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",
                                                "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada",
                                                "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
                                                "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsinite", "Wyoming"};


    public void adminLogin(String inputUserName, String inputPassWord)
    {
        if (inputUserName == admin.getUserName() && inputPassWord.equals(admin.getPassword()))
        {
            //login
        }
        else
        {
            out.println("miss information");
        }
    }


    public static void sendEmail(String recipient, String subject, String sentMassage) {
        String sender = "omidsltni@gmail.com";
        //String password = "Asap1234";
        //String host = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user", "omidsltni@gmail.com");
        //properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.elasticemail.com");
        properties.put("mail.smtp.port", "2525");


//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(sender, "A33191238B52D232020B2F375A40386FE4B6");
//            }
//        });
//
//        Message message = prepareMessage(session, sender, recipient, subject, sentMassage);
//
//        try {
//            assert message != null;
//            Transport.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        System.out.println("massage send successfully");
//    }
//
//
//    private static Message prepareMessage(Session session, String sender, String recipient, String subject, String sentMassage) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(sender));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//            message.setSubject(subject);
//            message.setText(sentMassage);
//            return message;
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    }
}
