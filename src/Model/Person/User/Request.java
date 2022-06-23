package Model.Person.User;

import Model.Person.EmailValidationException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Request
{
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public Request(Socket socket)
    {
        this.socket = socket;
        try
        {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void signUp(User receivedUser)
    {
//        if(!checkingEmail(receivedUser.getEmail()))
//        {
//            try
//            {
//                throw new EmailValidationException("Wrong Email");
//            }
//            catch (EmailValidationException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        else if(!checkingPhoneNumber(receivedUser.getPhoneNumber()))
//        {
//            try
//            {
//                throw new PhoneNumberValidationException("Wrong Phone-number");
//            }
//            catch (PhoneNumberValidationException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//            boolean flag = true;//repetitionOfEmail(receivedUser.getEmail());
//
//            if (flag)
//            {
//                //sendingDataToServer(userNumber);
//                sendingDataToServer(receivedUser);
//                Admin.sendEmail(receivedUser.getEmail());
//            }
//            else
//            {
//                out.println("u are already signed up");
//            }
//        }
        sendingDataToServer(receivedUser);
    }

//    private boolean repetitionOfEmail(String email)
//    {
//
//    }


    public void login(String inputUserName, String inputPassWord) throws IOException
    {

        boolean flag = false;
//        for (int j = 0; j < users.size(); j++)
//        {
//            if (Objects.equals(users.get(j).getIdentity(), inputIdentity))
//            {
//                flag = true;
//                userNumber = j;
//                break;
//            }
//        }
//
//        if (flag)
//        {
//            Admin.sendEmail(users.get(userNumber).getEmail());
//            PageControl.open("LoggedHome");
//        }
//        else
//        {
//            out.println("You have not signed up yet.\n");
//        }
    }


    private void sendingDataToServer(User receivedUser)
    {
        String jsonString = "{\n \"firstName\": " + receivedUser.getFirstName() + ",\n" + "\"lastName\": " +
                receivedUser.getLastName() + ",\n" + "\"userName\": " + receivedUser.getUserName() + ",\n" +
                "\"password\": " + receivedUser.getPassword() + ",\n" + "\"phoneNumber\": " +
                receivedUser.getPhoneNumber() + ",\n" + "\"emailAddress\" : " + receivedUser.getEmail() +
                ",\n" + "\"location\": " + receivedUser.getLocation() + "\n}";
        System.out.println(jsonString);
        //JSONObject jsonObject = new JSONObject(receivedUser);
        try
        {
            dataOutputStream.writeInt(1);
            dataOutputStream.writeUTF(jsonString);
            dataOutputStream.writeInt(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void logOut()
    {
        try
        {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean checkingEmail(String email)
    {
        String regex = "[0-9a-zA-Z_.]*@[0-9a-zA-Z]*\\.[a-zA-Z]{3}";
        return email.matches(regex);
    }


    private boolean checkingPhoneNumber(String phoneNumber)
    {
        String regex = "09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}";
        return phoneNumber.matches(regex);
    }
}
