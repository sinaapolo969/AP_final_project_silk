package DataBase;

import Network.Client;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class test
{
    static String json = "{\n" +
            "  \"firstName\": \"sssdsds\",\n" +
            "  \"lastName\": \"mehhh\",\n" +
            "  \"userName\": \"ssss\",\n" +
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

        Client client = new Client();
        Socket socket = client.setUp();
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeInt(2);
        dataOutputStream.writeUTF("sina");
        String data;
        data = dataInputStream.readUTF();
        System.out.println(data);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeUTF(json);
        dataOutputStream.writeInt(0);
//        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//        Object o = objectInputStream.readObject();
//        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//        String data = null;
//        while (dataInputStream.available() > 0)
//        {
//            data = dataInputStream.readUTF();
//        }
//        System.out.println(data);
//        dataInputStream.close();


//        for (String string : posts)
//        {
//            System.out.println(string);
//        }

    }
}
