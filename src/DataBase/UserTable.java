package DataBase;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTable extends DbHandler
{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserTable()
    {
        try
        {
            connection = getDbConnection();
        }
        catch (IOException | SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    //insert data to table
    public void insertUserData(String jsonString) throws SQLException, IOException, ClassNotFoundException
    {
        //this json contains the user data
        JSONObject jsonObject = new JSONObject(jsonString);
        //query for save the data in table
        String query = "insert into users (firstName, lastName, userName, password, phoneNumber, emailAddress, location) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        userExecutor(jsonObject, query);
    }

    //update user data
    public void updateUserData(String jsonString) throws SQLException, IOException, ClassNotFoundException
    {
        JSONObject jsonObject = new JSONObject(jsonString);
        String query = "update users set firstName = ?, lastName = ?," +
                " phoneNumber = ?, emailAddress = ?, location = ? where userName = ?";
        userExecutor(jsonObject, query);
    }

    //to avoid duplicate code
    private void userExecutor( JSONObject jsonObject, String query) throws SQLException
    {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, jsonObject.getString("firstName"));
        preparedStatement.setString(2, jsonObject.getString("lastName"));
        preparedStatement.setString(3, jsonObject.getString("userName"));
        preparedStatement.setString(4, jsonObject.getString("password"));
        preparedStatement.setString(5, jsonObject.getString("phoneNumber"));
        preparedStatement.setString(6, jsonObject.getString("emailAddress"));
        preparedStatement.setString(7, jsonObject.getString("location"));
        preparedStatement.executeUpdate();
        close();
    }

    //delete user data
    public void deleteUserData(String userName) throws SQLException, IOException, ClassNotFoundException
    {
        String query = "delete from users where userName = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.executeUpdate();
        close();
    }

    //getting user data from table
    public String getUserData(String userName) throws SQLException, IOException, ClassNotFoundException
    {
        String query = "select * from users where userName = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        //this is for make a json string for passing to clients
        String jsonString = null;
        while (resultSet.next())
        {
            jsonString = "{\n \"firstName\": " + resultSet.getString("firstName") + ",\n" + "\"lastName\": " +
                    resultSet.getString("lastName") + ",\n" + "\"userName\": " + resultSet.getNString("userName") + ",\n" +
                    "\"password\": " + resultSet.getString("password") + ",\n" + "\"phoneNumber\": " +
                    resultSet.getString("phoneNumber") + ",\n" + "\"emailAddress\" : " + resultSet.getString("emailAddress") +
                    ",\n" + "\"location\": " + resultSet.getString("location") + "\n}";
        }
        close();

        return jsonString;
    }

    private void close() throws SQLException
    {
        preparedStatement.close();
        connection.close();
    }
}
