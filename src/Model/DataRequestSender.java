package Model;

import DataBase.PostTable;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataRequestSender
{
    public ArrayList<Post> postRequestByCategory(String category) throws SQLException
    {
        PostTable postTable = new PostTable();
        ArrayList<String> jsonPosts = postTable.getPostByCategory(category);
        ArrayList<Post> posts = new ArrayList<>();
        for (String json : jsonPosts)
        {
            posts.add(jsonParser(json, new Post()));
        }

        return posts;
    }

    public void editPostInfo(String jsonString) throws SQLException
    {
        PostTable postTable = new PostTable();
        postTable.updatePostData(jsonString);
    }

    public Post jsonParser(String jsonString, Post post)
    {
        JSONObject jsonObject = new JSONObject(jsonString);
        post.setTitle(jsonObject.getString("title"));
        post.setPostId(jsonObject.getString("postId"));
        post.setCategory(jsonObject.getString("category"));
        post.setPrice(jsonObject.getDouble("price"));
        post.setDescription(jsonObject.getString("description"));
        post.setOwner(jsonObject.getString("owner"));
        post.setLocation(jsonObject.getString("location"));
        post.setPhoto(jsonObject.getString("photo"));
        post.setSaleStatus(jsonObject.getInt("sold") != 0);
        post.setPhoneNumber(jsonObject.getString("phoneNumber"));

        return post;
    }
}
