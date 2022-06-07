package DataBase;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String query = "delete from posts where owner = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.executeUpdate();
        close();
    }

    public ArrayList<String> getPostDataByOwner(String userName) throws SQLException
    {
        String query = "select * from posts where owner = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> posts = new ArrayList<>();
        while (resultSet.next())
        {
            String jsonString = "{\n \"title\": " + resultSet.getString("title") + ",\n" + "\"category\": " +
                    resultSet.getString("category") + ",\n" + "\"price\": "
                    + resultSet.getNString("price") + ",\n" +
                    "\"description\": " + resultSet.getString("description") + ",\n" + "\"owner\": " +
                    resultSet.getString("owner") + ",\n" + "\"sold\" : " + resultSet.getString("sold") +
                    ",\n" + "\"photo\": " + resultSet.getString("photo") + "\n}";
            posts.add(jsonString);
        }
        close();

        return posts;
    }

    public ArrayList<String> getPostByCategory(String category) throws SQLException
    {
        String query = "select * from posts where category = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, category);
        ResultSet resultSet = preparedStatement.executeQuery();
        int counter = 1;
        ArrayList<String> posts = new ArrayList<>();
        while (resultSet.next())
        {
            if (counter > 4)
            {
                break;
            }
            String query2 = "select users.phoneNumber from users inner join posts on posts.owner = users.userName where owner = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setString(1, resultSet.getString("owner"));
            ResultSet resultSet1 = preparedStatement2.executeQuery();
            String phoneNumber = null;
            while (resultSet1.next())
            {
                phoneNumber = resultSet1.getString("phoneNumber");
            }
            String jsonString = "{\n \"title\": " + resultSet.getString("title") + ",\n" + "\"category\": " +
                    resultSet.getString("category") + ",\n" + "\"price\": "
                    + resultSet.getString("price") + ",\n" +
                    "\"description\": " + resultSet.getString("description") + ",\n" + "\"owner\": " +
                    resultSet.getString("owner") + ",\n" + "\"sold\" : " + resultSet.getString("sold") +
                    ",\n" + "\"photo\": " + resultSet.getString("photo") + ",\n" + "\"phoneNumber\":" + phoneNumber + "\n}";
            posts.add(jsonString);
            counter++;
        }

        return posts;
    }

    private void close() throws SQLException
    {
        preparedStatement.close();
        connection.close();
    }

}
