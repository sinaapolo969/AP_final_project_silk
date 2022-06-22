package Model.Person.User;

import Model.PageControl;
import Model.Person.Admin.Admin;
import Model.Person.EmailValidationException;
import Model.Person.PhoneNumberValidationException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

import static java.lang.System.out;

public class Request
{
    User user;
    private Socket socket;
    private DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

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
        if(!checkingEmail(receivedUser.getEmail()))
        {
            try
            {
                throw new EmailValidationException("Wrong Email");
            }
            catch (EmailValidationException e)
            {
                e.printStackTrace();
            }
        }
        else if(!checkingPhoneNumber(receivedUser.getPhoneNumber()))
        {
            try
            {
                throw new PhoneNumberValidationException("Wrong Phone-number");
            }
            catch (PhoneNumberValidationException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            boolean flag = repetitionOfEmail(receivedUser.getEmail());

            if (flag)
            {
                //sendingDataToServer(userNumber);
                Admin.sendEmail(receivedUser.getEmail());
            }
            else
            {
                out.println("u are already signed up");
            }
        }
    }

    private boolean repetitionOfEmail(String email)
    {

    }


    public void login(String inputUserName, String inputPassWord, String inputEmail) throws IOException
    {
        if(!checkingEmail(inputEmail))
        {
            try
            {
                throw new EmailValidationException("Wrong Email");
            }
            catch (EmailValidationException e)
            {
                e.printStackTrace();
            }
        }

        int userNumber = 0;
        String inputIdentity = inputUserName + " " + inputPassWord + " " + inputEmail;

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


    private static String sendingDataToServer(int userNumber)
    {
//        JSONObject jsonObject = new JSONObject((Map) users.get(userNumber));
//        out.println(jsonObject.toString());
//        return new JSONObject((Map) users.get(userNumber)).toJSONString();
        return null;
    }


    public void logOut()
    {
        try {
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
