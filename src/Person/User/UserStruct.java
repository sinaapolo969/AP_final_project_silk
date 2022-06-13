package Person.User;

import Person.Person;
import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class UserStruct extends Person
{
    private String firstName;
    private String phoneNumber;
    private String location;
    private String lastName;

    public UserStruct(String userName, String password, String firstName, String email, String phoneNumber, String lastName, String location)
    {
        super(userName ,password ,email);
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.location = location;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
