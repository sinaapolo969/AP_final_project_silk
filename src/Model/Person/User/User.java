package Model.Person.User;

import Model.Person.Person;
import javafx.scene.image.Image;

public class User extends Person
{

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
