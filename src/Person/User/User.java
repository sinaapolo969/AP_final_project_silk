package Person.User;

import Person.Admin.Admin;
import Person.EmailValidationException;
import Person.PhoneNumberValidationException;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.*;

public class User {
    public static ArrayList<UserStruct> users = new ArrayList<>();
    static Scanner scanner = new Scanner(in);

    private static void signUp() {
        String userName;
        String passWord;
        String firstName;
        String lastName;
        String email = null;
        String phoneNumber = null;

        userName = GettingUserName();
        try {
            email = GettingEmail();
        } catch (EmailValidationException e) {
            e.printStackTrace();
        }
        firstName = GettingName("firstname");
        lastName = GettingName("lastname");
        passWord = GettingPassWord();
        try {
            phoneNumber = GettingPhoneNumber();
        } catch (PhoneNumberValidationException e) {
            e.printStackTrace();
        }


        boolean flag = true;
        for (UserStruct user : users) {
            if (Objects.equals(user.getEmail(), email)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            users.add(new UserStruct(userName, passWord, firstName, email, phoneNumber, lastName));
            int userNumber = users.size() - 1;
            Admin.sendEmail(users.get(userNumber).getEmail());
        } else {
            out.println("You have already signed up.\n please choose;\n1. Try again\n2. Login");

            int selected = scanner.nextInt();

            try {
                if (selected > 2) {
                    throw new Exception();
                } else {
                    switch (selected) {
                        case 1:
                            out.flush();
                            signUp();
                            break;
                        case 2:
                            out.flush();
                            login();
                            break;
                    }
                }
            } catch (Exception e) {
                out.flush();
                out.println("Wrong input");
                signUp();
            }
        }
    }

    public static void login() {
        String inputUserName;
        String inputPassWord;
        String inputEmail = null;
        int userNumber = 0;

        inputUserName = GettingUserName();
        try {
            inputEmail = GettingEmail();
        } catch (EmailValidationException e) {
            e.printStackTrace();
        }
        inputPassWord = GettingPassWord();

        String inputIdentity = inputUserName + " " + inputPassWord + " " + inputEmail;

        boolean flag = false;
        for (int j = 0; j < users.size(); j++) {
            if (Objects.equals(users.get(j).getIdentity(), inputIdentity)) {
                flag = true;
                userNumber = j;
                break;
            }
        }

        if (flag) {
            out.flush();
            Admin.sendEmail(users.get(userNumber).getEmail());
        } else {
            out.println("You have not Signed up yet.\n 1. Signing up \n 2. Try again");

            int selected = scanner.nextInt();

            try {
                if (selected > 2) {
                    throw new Exception();
                } else {
                    switch (selected) {
                        case 1:
                            signUp();
                            out.flush();
                            break;
                        case 2:
                            login();
                            out.flush();
                            break;
                    }
                }
            } catch (Exception e) {
                out.flush();
                out.println("Wrong input");
                signUp();
            }
        }
    }

    private static String GettingEmail() throws EmailValidationException {
        out.println("please enter your Email:");
        String email = scanner.next();

        String regex = "[0-9a-zA-Z_.]*@[0-9a-zA-Z]*\\.[a-zA-Z]{3}";

        boolean flag = email.matches(regex);

        if (flag) {
            return email;
        } else {
            GettingEmail();
            throw new EmailValidationException("Wrong email!!!");
        }
    }

    private static String GettingPassWord() {
        out.println("please enter your password:");
        return scanner.next();
    }

    private static String GettingName(String s) {
        out.println("please enter your "+ s + ":");
        return scanner.next();
    }

    private static String GettingPhoneNumber() throws PhoneNumberValidationException {
        String phoneNumber;
        out.println("please enter your phoneNumber Number:");
        phoneNumber = scanner.next();

        char[] charNum = phoneNumber.toCharArray();

        if (charNum[0] == '0' && charNum[1] == '9')
        {
            String regexNum = "[0-9]{11}";

            boolean flagNum = phoneNumber.matches(regexNum);

            if (flagNum)
            {
                return phoneNumber;
            }
            else
            {
                GettingPhoneNumber();
                throw new PhoneNumberValidationException ("Wrong phone number!!!");
            }
        }
        else if (charNum[0] == '+' && charNum[1] == '9' && charNum[2] == '8' && charNum[3] == '9')
        {
            String regexNum = "\\+[0-9]{12}";

            boolean flagNum = phoneNumber.matches(regexNum);

            if (flagNum)
            {
                return phoneNumber;
            }
            else
            {
                GettingPhoneNumber();
                throw new PhoneNumberValidationException ("Wrong phone number!!!");
            }
        }
        else if (charNum[0] == '0' && charNum[1] == '0' && charNum[2] == '9' && charNum[3] == '8' && charNum[4] == '9')
        {
            String regexNum = "[0-9]{14}";

            boolean flagNum = phoneNumber.matches(regexNum);

            if (flagNum)
            {
                return phoneNumber;
            }
            else
            {
                GettingPhoneNumber();
                throw new PhoneNumberValidationException ("Wrong phone number!!!");
            }
        }
        else
        {
            GettingPhoneNumber();
            throw new PhoneNumberValidationException ("Wrong phone number!!!");
        }
    }

    private static String GettingUserName() {
        out.println("please enter your username:");
        return scanner.next();
    }

    private static JSONObject SendingDataToServer()
    {
        return users.get(users.size() - 1).person;
    }
}
