package Model.Person.User;

import Model.PageControl;
import Model.Person.Admin.Admin;
import Model.Person.EmailValidationException;
import Model.Person.Person;
import Model.Person.PhoneNumberValidationException;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.System.out;

public class User extends Person
{
    public static ArrayList<User> users = new ArrayList<User>();

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String location;
    private Image profile;

    public User(String userName, String password, String firstName, String lastName,
                String phoneNumber, String email, String location, Image profile)
    {
        super(userName ,password ,email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.profile = profile;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
