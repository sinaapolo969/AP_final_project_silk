package DataBase;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class test
{
    static String json = "{\n" +
            "  \"firstName\": \"mmadreza\",\n" +
            "  \"lastName\": \"tayebi\",\n" +
            "  \"userName\": \"sinaApolo\",\n" +
            "  \"password\": \"sina1381\",\n" +
            "  \"phoneNumber\": \"11111\",\n" +
            "  \"emailAddress\": \"sdadfdfsd\",\n" +
            "  \"location\": \"abodan\"\n" +
            "}";
    static String json2 = "{\n" +
            "  \"firstName\": \"mmadreza\",\n" +
            "  \"lastName\": \"tayebi\",\n" +
            "  \"phoneNumber\": \"11111\",\n" +
            "  \"emailAddress\": \"sdadfdfsd\",\n" +
            "  \"location\": \"abodan\"\n" +
            "}";
    static String json3 = "{\n" +
            "  \"title\": \"bib\",\n" +
            "  \"postId\": \"2\", \n " +
            "  \"category\": \"home\",\n" +
            "  \"description\": \"something\",\n" +
            "  \"price\": \"10000\",\n" +
            "  \"owner\": \"sina\",\n" +
            "  \"location\": \"ahvaz\", \n" +
            "  \"sold\": \"0\",\n" +
            "  \"photo\": \"something\"\n" +
            "}";
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException
    {
        //ArrayList<Post> posts = new DataRequestSender().postRequestByCategory("home");
//        PostTable postTable = new PostTable();
//        ArrayList<String> posts = postTable.getPostDataByOwner("sina");
//        JSONObject jsonObject = new JSONObject(posts.get(0));
//        System.out.println(jsonObject.getString("title"));
//        String json = userTable.getUserData("sina");
//        JSONObject jsonObject = new JSONObject(json);
//        System.out.println(jsonObject.getString("firstName"));
//        for (Post post : posts)
//        {
//            System.out.println(post.getCategory() + "   " + post.getTitle() +
//                     post.getPostId() + "  " + post.getPhoneNumber() + "   " + post.getLocation());
//        }

        //PostTable postTable = new PostTable();
//        ArrayList<String> posts = postTable.getPostDataByOwner("omidslt");
//        for (String s : posts) {
//            System.out.println(s);
//        }
        //postTable.insertBookMark("sina", 3);

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.getDayOfMonth());


    }
}
