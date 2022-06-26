package Model.Person.User;


import Model.Person.Admin.Admin;
import Model.Person.EmailValidationException;
import Model.Person.PhoneNumberValidationException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;


public class Request
{
    private final Socket socket;
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
        //if(!checkingEmailValidation(receivedUser.getEmail()))
        //{
        //    try
        //    {
        //        throw new EmailValidationException("Wrong Email");
        //    }
        //    catch (EmailValidationException e)
        //    {
        //        e.printStackTrace();
        //    }
        //}
        //else if(!checkingPhoneNumber(receivedUser.getPhoneNumber()))
        //{
        //    try
        //    {
        //        throw new PhoneNumberValidationException("Wrong Phone-number");
        //    }
        //    catch (PhoneNumberValidationException e)
        //    {
        //        e.printStackTrace();
        //    }
        //}
        //else if(!checkingStrengthOfPass(receivedUser.getPassword()))
        //{
        //    //the password isn't strong enough
        //}
        //else
        //{
        //    boolean flag = true;//repetitionOfEmail(receivedUser.getEmail());
        //
        //    if (flag)
        //    {
        //        //sendingDataToServer(userNumber);
        //        sendingDataToServer(receivedUser);
        //        Admin.sendEmail(receivedUser.getEmail(), "WellCome", "welcome\n to silk road app we are so glad to have you here");
        //    }
        //    else
        //    {
        //        System.out.println("u are already signed up");
        //    }
        //}
        sendingDataToServer(receivedUser);
    }


    public User login(String inputUserName, String inputPassWord)
    {
        User receivedUser = existenceOfUser(inputUserName, inputPassWord);
        return receivedUser;
    }


    public synchronized void sendingEmailForResettingPassword(User user)// ariana should call this when user push reset password button
    {
        int code = 1000 + (int) (Math.random() * 10000);
        Admin.sendEmail(user.getEmail(), "Resetting Email", "here's your code for resetting your password" + code);
        // I will send this code and user for ariana
    }

    public synchronized void gettingCode(int inputCode, int originalCode, User user) // ariana will send the input code for me and als resend the original code and the user
    {
        if (inputCode == originalCode)
        {
            //calling the page that get new pass then there we call editInfo(user)
        }
        else {
            //out.println("wrong code");
            //if (they push new code button)
            //{
            //  sout("enter the new code)
            //  sendingEmailForResettingPassword(user);
            //
            //}
            //elseif(they push exit)
            //{
            //      go back
            //}
        }
    }


    public void editInfo(User receivedUser) {
        if (!checkingEmailValidation(receivedUser.getEmail())) {
            try {
                throw new EmailValidationException("Wrong Email");
            } catch (EmailValidationException e) {
                e.printStackTrace();
            }
        } else if (!checkingPhoneNumber(receivedUser.getPhoneNumber())) {
            try {
                throw new PhoneNumberValidationException("Wrong Phone-number");
            } catch (PhoneNumberValidationException e) {
                e.printStackTrace();
            }
        } else {
            sendingDataToServer(receivedUser);
        }
    }


    private User existenceOfUser(String inputUserName, String inputPassWord)
    {
        try {
            dataOutputStream.writeInt(2);
            dataOutputStream.writeUTF(inputUserName);
            dataOutputStream.writeUTF(inputPassWord);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String receivedUser = null;
        try {
            receivedUser = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File receivedImage = receiveProfilePhoto("D:/new.png");
        if(receivedUser == null)
        {
            return null;
        }
        JSONObject receivedUserJason = new JSONObject(receivedUser);

        User user = new User(receivedUserJason.getString("userName"), receivedUserJason.getString("password"),
                receivedUserJason.getString("firstName"), receivedUserJason.getString("lastName"),
                receivedUserJason.getString("phoneNumber"), receivedUserJason.getString("emailAddress"),
                receivedUserJason.getString("location"), receivedImage);

        return user;
    }

    private void sendingDataToServer(User receivedUser)
    {
        String jsonString = "{\n \"firstName\": " + "\"" + receivedUser.getFirstName() + "\"" + ",\n" + "\"lastName\": " +
                "\"" + receivedUser.getLastName() + "\"" + ",\n" + "\"userName\": " + "\"" + receivedUser.getUserName() + "\"" + ",\n" +
                "\"password\": " + "\"" + receivedUser.getPassword() + "\"" + ",\n" + "\"phoneNumber\": " +
                "\"" + receivedUser.getPhoneNumber() + "\"" + ",\n" + "\"emailAddress\" : " + "\"" + receivedUser.getEmail() + "\"" +
                ",\n" + "\"location\": " + "\"" + receivedUser.getLocation() + "\"" + "\n}";
        System.out.println(jsonString);
        //JSONObject jsonObject = new JSONObject(receivedUser);

        try
        {
            dataOutputStream.writeInt(1);
            dataOutputStream.writeUTF(jsonString);
            sendProfilePhoto(receivedUser.getProfile(), dataOutputStream);
            dataOutputStream.writeInt(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void sendProfilePhoto(File file, DataOutputStream dataOutputStream)
    {
        try
        {
            int bytes = 0;
            FileInputStream fileInputStream = new FileInputStream(file);
            dataOutputStream.writeUTF(file.getName().substring(file.getName().indexOf(".")));
            dataOutputStream.writeLong(file.length());
            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1)
            {
                dataOutputStream.write(buffer, 0, bytes);
                dataOutputStream.flush();
            }
            fileInputStream.close();
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    private File receiveProfilePhoto(String path)
    {
        int bytes = 0;
        File file = new File(path);
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            long size = dataInputStream.readLong();
            byte[] buffer = new byte[4 * 1024];
            while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1)
            {
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;
            }
            fileOutputStream.close();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

        return file;
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

    private boolean checkingEmailValidation(String email)
    {
        String regex = "[0-9a-zA-Z_.]*@[0-9a-zA-Z]*\\.[a-zA-Z]{3}";
        return email.matches(regex);
    }


    private boolean checkingPhoneNumber(String phoneNumber)
    {
        String regex = "09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}";
        return phoneNumber.matches(regex);
    }

    private boolean existenceOfEmail(String email) {
        try {
            dataOutputStream.writeUTF(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return dataInputStream.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkingStrengthOfPass(String password)
    {
        String strongPassRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        return password.matches(strongPassRegex);
    }
}
