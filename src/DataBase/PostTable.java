package DataBase;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostTable extends DbHandler
{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PostTable()
    {
        try
        {
            this.connection = getDbConnection();
        }
        catch (IOException | SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void insertPostData(String jsonString) throws SQLException
    {
        JSONObject jsonObject = new JSONObject(jsonString);
        String query = "insert into posts (title, category, price, description, owner, sold, photo) values" +
                " (?, ?, ?, ?, ?, ?, ?)";
        postExecutor(jsonObject, query);
    }

    public void updatePostData(String jsonString) throws SQLException
    {
        JSONObject jsonObject = new JSONObject(jsonString);
        String query = "update posts set title= ?, category = ?," +
                " price = ?, description = ?, owner = ?, sold = ?, photo = ? where owner = ?";
        postExecutor(jsonObject, query);
    }

    //for avoiding the code Repetitious
    private void postExecutor(JSONObject jsonObject, String query) throws SQLException
    {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, jsonObject.getString("title"));
        preparedStatement.setString(2, jsonObject.getString("category"));
        preparedStatement.setString(3, jsonObject.getString("price"));
        preparedStatement.setString(4, jsonObject.getString("description"));
        preparedStatement.setString(5, jsonObject.getString("owner"));
        preparedStatement.setString(6, jsonObject.getString("sold"));
        preparedStatement.setString(7, jsonObject.getString("photo"));
        preparedStatement.executeUpdate();
        close();
    }

    public void deletePost(String userName) throws SQLException
    {
        String query = "delete * from posts where owner = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.executeUpdate();
        close();
    }

    public String getPostData(String userName) throws SQLException
    {
        String query = "select * from posts where owner = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        String jsonString = null;
        while (resultSet.next())
        {
            jsonString = "{\n \"title\": " + resultSet.getString("title") + ",\n" + "\"category\": " +
                    resultSet.getString("category") + ",\n" + "\"price\": "
                    + resultSet.getNString("price") + ",\n" +
                    "\"description\": " + resultSet.getString("description") + ",\n" + "\"owner\": " +
                    resultSet.getString("owner") + ",\n" + "\"sold\" : " + resultSet.getString("sold") +
                    ",\n" + "\"photo\": " + resultSet.getString("photo") + "\n}";
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
