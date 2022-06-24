package Model.Person;

abstract public class Person
{
    private String password;
    private String userName;
    private String email;

    public Person(String userName,String password , String email)
    {
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
}
