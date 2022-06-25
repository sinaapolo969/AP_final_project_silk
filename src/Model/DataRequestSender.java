package Model;

public class DataRequestSender
{
//    public ArrayList<Model.Post> postRequestByCategory(String category) throws SQLException
//    {
//        PostTable postTable = new PostTable();
//        ArrayList<String> jsonPosts = postTable.getPostByCategory(category);
//        ArrayList<Model.Post> posts = new ArrayList<>();
//        for (String json : jsonPosts)
//        {
//            posts.add(jsonParser(json, new Model.Post()));
//        }
//
//        return posts;
//    }
//
//    public void editPostInfo(String jsonString) throws SQLException
//    {
//        PostTable postTable = new PostTable();
//        postTable.updatePostData(jsonString);
//    }
//
//    public Model.Post jsonParser(String jsonString, Model.Post post)
//    {
//        JSONObject jsonObject = new JSONObject(jsonString);
//        post.setTitle(jsonObject.getString("title"));
//        post.setPostId(jsonObject.getString("postId"));
//        post.setCategory(jsonObject.getString("category"));
//        post.setPrice(jsonObject.getDouble("price"));
//        post.setDescription(jsonObject.getString("description"));
//        post.setOwner(jsonObject.getString("owner"));
//        post.setLocation(jsonObject.getString("location"));
//        //post.setPhoto(jsonObject.get("photo"));
//        post.setSaleStatus(jsonObject.getInt("sold") != 0);
//        post.setPhoneNumber(jsonObject.getString("phoneNumber"));
//
//        return post;
//    }
}
