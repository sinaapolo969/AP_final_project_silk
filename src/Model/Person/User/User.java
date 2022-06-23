package Model.Person.User;

import Model.PageControl;
import Model.Person.Admin.Admin;
import Model.Person.EmailValidationException;
import Model.Person.Person;
import Model.Person.PhoneNumberValidationException;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
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
    private File profile;


    public User(String userName, String password, String firstName, String lastName,
                String phoneNumber, String email, String location, File profile)
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

    public File getProfile() {
        return profile;
    }

    public void setProfile(File profile) {
        this.profile = profile;
    }
}
