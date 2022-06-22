package Person.Admin;


import java.util.*;



public class Admin {
    private static AdminStruct admin = new AdminStruct("Admin", "1234", "silkroadhelpu@gmail.com");

    public static void sendEmail(String costumerEmail) {
        String recipient = "omidsltni@gmail.com";
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
//        Message message = prepareMessage(session, sender, recipient);
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
//    private static Message prepareMessage(Session session, String sender, String recipient) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(sender));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//            message.setSubject("Welcoming message");
//            message.setText("Hi There,\n Welcome to silk road app. \n We are so glad to see you here. ");
//            return message;
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    }
}
