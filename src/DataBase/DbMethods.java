package DataBase;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//this class contains the databases methods that we need for this project
public abstract class DbMethods
{
    //user methods
    //insert data to table
    public void insertUserData(String jsonString) throws SQLException, IOException, ClassNotFoundException
    {
        DbHandler dbHandler = new DbHandler();
        Connection connection = dbHandler.getDbConnection();
        //this json contains the user data
        JSONObject jsonObject = new JSONObject(jsonString);
        //query for save the data in table
        String query = "insert into users (firstName, lastName, userName, password, phoneNumber, emailAddress, location) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, jsonObject.getString("firstName"));
        preparedStatement.setString(2, jsonObject.getString("lastName"));
        preparedStatement.setString(3, jsonObject.getString("userName"));
        preparedStatement.setString(4, jsonObject.getString("password"));
        preparedStatement.setString(5, jsonObject.getString("phoneNumber"));
        preparedStatement.setString(6, jsonObject.getString("emailAddress"));
        preparedStatement.setString(7, jsonObject.getString("location"));
        preparedStatement.executeUpdate();
        connection.close();
        preparedStatement.close();
    }

    //update user data
    public void updateUserData(String jsonString) throws SQLException, IOException, ClassNotFoundException
    {
        DbHandler dbHandler = new DbHandler();
        Connection connection = dbHandler.getDbConnection();
        JSONObject jsonObject = new JSONObject(jsonString);
        String query = "update users set firstName = ?, lastName = ?," +
                " phoneNumber = ?, emailAddress = ?, location = ? where userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, jsonObject.getString("firstName"));
        preparedStatement.setString(2, jsonObject.getString("lastName"));
        preparedStatement.setString(3, jsonObject.getString("phoneNumber"));
        preparedStatement.setString(4, jsonObject.getString("emailAddress"));
        preparedStatement.setString(5, jsonObject.getString("location"));
        preparedStatement.setString(6, jsonObject.getString("userName"));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    //delete user data
    public void deleteUserData(String userName) throws SQLException, IOException, ClassNotFoundException
    {
        DbHandler dbHandler = new DbHandler();
        Connection connection = dbHandler.getDbConnection();
        String query = "delete from users where userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    //getting user data from table
    public String getUserData(String userName) throws SQLException, IOException, ClassNotFoundException
    {
        DbHandler dbHandler = new DbHandler();
        Connection connection = dbHandler.getDbConnection();
        String query = "select * from users where userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
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
        return jsonString;
    }
    //end of user methods


}
