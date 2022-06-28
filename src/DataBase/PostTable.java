package DataBase;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
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

    public void insertPostData(String jsonString, String path) throws SQLException
    {
        JSONObject jsonObject = new JSONObject(jsonString);
        String query = "insert into posts (title, category, price, description, owner, location, sold, photo, date) values" +
                " (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, jsonObject.getString("title"));
        preparedStatement.setString(2, jsonObject.getString("category"));
        preparedStatement.setString(3, jsonObject.getString("price"));
        preparedStatement.setString(4, jsonObject.getString("description"));
        preparedStatement.setString(5, jsonObject.getString("owner"));
        preparedStatement.setString(6, jsonObject.getString("location"));
        preparedStatement.setString(7, jsonObject.getString("sold"));
        preparedStatement.setString(8, path);
        preparedStatement.setString(9, jsonObject.getString("date"));
        preparedStatement.executeUpdate();
    }

    public void updatePostData(String jsonString, String path) throws SQLException
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
        preparedStatement.setString(7, path);
        preparedStatement.setString(8, jsonObject.getString("owner"));
        preparedStatement.setString(9, jsonObject.getString("postId"));
        preparedStatement.executeUpdate();
    }


    public void deletePost(String postId) throws SQLException
    {
        String query = "delete from posts where postId = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, postId);
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

    public ArrayList<String> getPostByCategoryAndLocation(String category, String location) throws SQLException
    {
        String query = "select * from posts where category = ? and location = ? order by date";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, category);
        preparedStatement.setString(2, location);

        return convertDataToArrayJsonString(preparedStatement);
    }

    public ArrayList<String> getPostByLocation(String location) throws SQLException
    {
        String query = "select * from posts where location = ? order by date";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, location);

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
                    "\"" + phoneNumber + "\"" + ",\n" + "\"date\": " + "\"" + resultSet.getDate("date") + "\"" + "\n}";
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
        String query = "select * from posts inner join bookmarks on posts.postId = bookmarks.postId where bookmarks.userName = ? order by date";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);

        return convertDataToArrayJsonString(preparedStatement);
    }

    //this method returns posts with specified filter
    public ArrayList<String> getFilteredPriceAndLocationPost(int min, int max, String location) throws SQLException
    {
        String query = "select * from posts where price between ? and ? and location = ? order by date";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, min);
        preparedStatement.setInt(2, max);
        preparedStatement.setString(3, location);

        return convertDataToArrayJsonString(preparedStatement);
    }

    public ArrayList<String> getFilteredPricePost(int min, int max) throws SQLException
    {
        String query = "select * from posts where price between ? and ? order by date";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, min);
        preparedStatement.setInt(2, max);

        return convertDataToArrayJsonString(preparedStatement);
    }

    public ArrayList<String> getPostByCategoryAndPrice(String category, int min, int max) throws SQLException
    {
        String query = "select * from posts where price between ? and ? and category = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, min);
        preparedStatement.setInt(2, max);
        preparedStatement.setString(3, category);

        return convertDataToArrayJsonString(preparedStatement);
    }

    public ArrayList<String> getFilteredPriceAndLocationAndCategory(String location
            , String category, int min, int max) throws SQLException
    {
        String query = "select * from posts where price between ? and ? and location = ? and category = ? order by date";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, min);
        preparedStatement.setInt(2, max);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, category);

        return convertDataToArrayJsonString(preparedStatement);
    }

    public void saveMessage(String jsonString) throws SQLException
    {
        String query = "insert into conversations (chatId, sender, receiver, message, dateTime) " +
                "values (?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        JSONObject jsonObject = new JSONObject(jsonString);
        preparedStatement.setInt(1, jsonObject.getInt("chatId"));
        preparedStatement.setString(2, jsonObject.getString("sender"));
        preparedStatement.setString(3, jsonObject.getString("receiver"));
        preparedStatement.setString(4, jsonObject.getString("text"));
        preparedStatement.setString(5, jsonObject.getString("dateTime"));
        preparedStatement.executeUpdate();
    }

    //for getting the conversation of the post between specified users
    public ArrayList<String> getConversation(String postId, String userName1, String userName2) throws SQLException
    {
        ArrayList<String> messages = new ArrayList<>();

        String query = "select * from conversations inner join posts on conversations.sender = ? and conversations.receiver = ? or " +
                "conversations.sender = ? and conversations.receiver = ? where posts.postId = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName1);
        preparedStatement.setString(2, userName2);
        preparedStatement.setString(3, userName2);
        preparedStatement.setString(4, userName1);
        preparedStatement.setString(5, postId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            String jsonString = "{\n \"text\": " + "\"" + resultSet.getString("message") + "\"" + ",\n" + "\"sender\": " +
                    "\"" + resultSet.getString("sender") + "\"" + ",\n" + "\"receiver\": " + "\"" +
                    resultSet.getString("receiver") + "\"" +
                    ",\n" + "\"dateTime\": " + "\"" + resultSet.getString("dateTime") + "\"" + "\n}";
            messages.add(jsonString);
        }

        return messages;
    }




    public void close() throws SQLException
    {
        preparedStatement.close();
        connection.close();
    }

}
