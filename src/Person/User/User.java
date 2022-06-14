package Person.User;

import Person.Admin.Admin;
import Person.EmailValidationException;
import Person.PhoneNumberValidationException;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.System.out;

public class User
{
    public static ArrayList<UserStruct> users = new ArrayList<>();

    public static void signUp(String userName, String passWord, String firstName, String lastName, String email, String phoneNumber, String location)
    {
        if(!CheckingEmail(email))
        {
            signUp(userName, passWord, firstName, lastName, email, phoneNumber, location);// we should get input here instead of sign up
            try
            {
                throw new EmailValidationException("Wrong Email");
            }
            catch (EmailValidationException e)
            {
                e.printStackTrace();
            }
        }

        if(!CheckingPhoneNumber(phoneNumber))
        {
            signUp(userName, passWord, firstName, lastName, email, phoneNumber, location);// we should get input here instead of sign up
            try
            {
                throw new PhoneNumberValidationException("Wrong Phone-number");
            }
            catch (PhoneNumberValidationException e)
            {
                e.printStackTrace();
            }
        }


        boolean flag = true;
        for (UserStruct user : users)
        {
            if (Objects.equals(user.getEmail(), email))
            {
                flag = false;
                break;
            }
        }

        if (flag)
        {
            users.add(new UserStruct(userName, passWord, firstName, email, phoneNumber, lastName, location));
            int userNumber = users.size() - 1;
            SendingDataToServer(userNumber);
            Admin.sendEmail(users.get(userNumber).getEmail());
        }
        else
        {
            out.println("u are already signed up");//we should call a method to get input again
            signUp(userName,passWord,firstName,lastName,email,phoneNumber,location);
            login(userName, passWord, email);
        }
    }


    public static void login(String inputUserName, String inputPassWord, String inputEmail)
    {
        if(!CheckingEmail(inputEmail))
        {
            login (inputUserName, inputPassWord, inputEmail);// we should get input here instead of sign up
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
        for (int j = 0; j < users.size(); j++)
        {
            if (Objects.equals(users.get(j).getIdentity(), inputIdentity))
            {
                flag = true;
                userNumber = j;
                break;
            }
        }

        if (flag)
        {
            Admin.sendEmail(users.get(userNumber).getEmail());
        }
        else
        {
            out.println("You have not Signed up yet.\n");
            login(inputUserName, inputPassWord, inputEmail);
            signUp(inputUserName, inputPassWord, "", "", inputEmail, "", "");
        }
    }


    private static boolean CheckingEmail(String email)
    {
        String regex = "[0-9a-zA-Z_.]*@[0-9a-zA-Z]*\\.[a-zA-Z]{3}";

        return email.matches(regex);
    }


    private static boolean CheckingPhoneNumber(String phoneNumber)
    {
        char[] charNum = phoneNumber.toCharArray();

        if (charNum[0] == '0' && charNum[1] == '9')
        {
            String regexNum = "[0-9]{11}";

            return phoneNumber.matches(regexNum);
        }
        else if (charNum[0] == '+' && charNum[1] == '9' && charNum[2] == '8' && charNum[3] == '9')
        {
            String regexNum = "\\+[0-9]{12}";

            return phoneNumber.matches(regexNum);
        }
        else if (charNum[0] == '0' && charNum[1] == '0' && charNum[2] == '9' && charNum[3] == '8' && charNum[4] == '9')
        {
            String regexNum = "[0-9]{14}";

            return phoneNumber.matches(regexNum);
        }
        else
        {
            return false;
        }
    }


    private static String SendingDataToServer(int userNumber)
    {
//        JSONObject jsonObject = new JSONObject((Map) users.get(userNumber));
//        out.println(jsonObject.toString());
//        return new JSONObject((Map) users.get(userNumber)).toJSONString();
        return null;
    }
}
