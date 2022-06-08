package Person.User;

import Person.Person;
import org.json.simple.JSONObject;

public class UserStruct extends Person
{
    JSONObject person = new JSONObject();
    private final String firstName;
    private String phoneNumber;
    String lastName;

    public UserStruct(String userName, String password, String firstName, String email, String phoneNumber, String lastName) {
        super(userName ,password ,email);
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        super.setIdentity(userName + " " + password + " " + email);
        person.put("lastName", lastName);
        person.put("firstName", firstName);
        person.put("userName", userName);
        person.put("emailAddress", email);
        person.put("password", password);
        person.put("phoneNumber", phoneNumber);
    }

    public String getIDNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }
}
