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
        String query = "insert into posts (title, category, price, description, owner, location, sold, photo) values" +
                " (?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, jsonObject.getString("title"));
        preparedStatement.setString(2, jsonObject.getString("category"));
        preparedStatement.setString(3, jsonObject.getString("price"));
        preparedStatement.setString(4, jsonObject.getString("description"));
        preparedStatement.setString(5, jsonObject.getString("location"));
        preparedStatement.setString(6, jsonObject.getString("sold"));
        preparedStatement.setString(7, jsonObject.getString("photo"));
        preparedStatement.setString(8, jsonObject.getString("owner"));
        preparedStatement.executeUpdate();
    }

    public void updatePostData(String jsonString) throws SQLException
    {
        JSONObject jsonObject = new JSONObject(jsonString);
        String query = "update posts set title= ?, category = ?," +
                " price = ?, description = ?, location = ?,  sold = ?, photo = ? where owner = ? and postId = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, jsonObject.getString("title"));
        preparedStatement.setString(2, jsonObject.getString("category"));
        preparedStatement.setString(3, jsonObject.getString("price"));
        preparedStatement.setString(4, jsonObject.getString("description"));
        preparedStatement.setString(5, jsonObject.getString("location"));
        preparedStatement.setString(6, jsonObject.getString("sold"));
        preparedStatement.setString(7, jsonObject.getString("photo"));
        preparedStatement.setString(8, jsonObject.getString("owner"));
        preparedStatement.setString(9, jsonObject.getString("postId"));
        preparedStatement.executeUpdate();
    }


    public void deletePost(String userName) throws SQLException
    {
        String query = "delete from posts where owner = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.executeUpdate();
    }

    public ArrayList<String> getPostDataByOwner(String userName) throws SQLException
    {
        String query = "select * from posts where owner = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);

        return convertDataToArrayJsonString(preparedStatement);
    }

    public ArrayList<String> getPostByCategory(String category) throws SQLException
    {
        String query = "select * from posts where category = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, category);
        return convertDataToArrayJsonString(preparedStatement);
    }

    private String getOwnerPhoneNumber(String owner) throws SQLException
    {
        String query2 = "select users.phoneNumber from users inner join posts on posts.owner = users.userName where owner = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
        preparedStatement2.setString(1, owner);
        ResultSet resultSet1 = preparedStatement2.executeQuery();
        String phoneNumber = null;
        while (resultSet1.next())
        {
            phoneNumber = resultSet1.getString("phoneNumber");
        }

        return phoneNumber;
    }



    private ArrayList<String> convertDataToArrayJsonString(PreparedStatement preparedStatement1) throws SQLException
    {
        ResultSet resultSet = preparedStatement1.executeQuery();
        ArrayList<String> posts = new ArrayList<>();
        while (resultSet.next())
        {
            String phoneNumber = getOwnerPhoneNumber(resultSet.getString("owner"));
            String jsonString = "{\n \"title\": " + "\"" + resultSet.getString("title") +
                    "\"" + ",\n" + "\"postId\": " + "\"" + resultSet.getInt("postId") + "\"" + ",\n" + "\"category\": " +
                    "\"" + resultSet.getString("category") + "\"" + ",\n" + "\"price\": "
                    + "\"" + resultSet.getNString("price") + "\"" + ",\n" +
                    "\"description\": " + "\"" + resultSet.getString("description") + "\"" + ",\n" + "\"owner\": " + "\"" +
                    resultSet.getString("owner") + "\"" + ",\n" + "\"location\": "
                    + "\"" + resultSet.getString("location") + "\"" + ",\n" + "\"sold\" : "
                    + "\"" + resultSet.getString("sold") + "\"" +
                    ",\n" + "\"photo\": " + "\"" + resultSet.getString("photo") + "\"" +",\n" + "\"phoneNumber\": " +
                    "\"" + phoneNumber + "\"" + "\n}";
            posts.add(jsonString);
        }

        return posts;
    }

    public void insertBookMark(String userName, int postId) throws SQLException
    {
        String query = "insert into bookmarks (userName, postId) values (?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.setInt(2, postId);
        preparedStatement.executeUpdate();
    }

    //this method is for return posts that bookmarked from a user
    public ArrayList<String> getBookMarks(String userName) throws SQLException
    {
        String query = "select * from posts inner join bookmarks on posts.postId = bookmarks.postId where bookmarks.userName = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);

        return convertDataToArrayJsonString(preparedStatement);
    }




    public void close() throws SQLException
    {
        preparedStatement.close();
        connection.close();
    }

}
