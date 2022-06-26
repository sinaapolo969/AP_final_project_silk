package DataBase;

import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class test
{
    static String json = "{\n" +
            "  \"firstName\": \"ariana\",\n" +
            "  \"lastName\": \"mehhh\",\n" +
            "  \"userName\": \"meow\",\n" +
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

//        PostTable postTable = new PostTable();
//        ArrayList<String> posts = postTable.getPostDataByOwner("sina");
//        System.out.println(posts.get(0));

//        Client client = new Client();
//        Socket socket = client.setUp();
        PostTable postTable = new PostTable();
        ArrayList<String> posts = postTable.getPostDataByOwner("mahdi");
        for (String post : posts) {
            JSONObject jsonObject = new JSONObject(post);
            //Date date = new Date(jsonObject.getString("date"));
            //System.out.println(date);
            //System.out.println(post);
           // System.out.println(jsonObject.get("date"));
        }

//        PostRequests postRequests = new PostRequests(socket);
//        ArrayList<Post> posts = postRequests.getPostByOwner("sina");
//        for (Post post : posts) {
//            System.out.println(post.getPrice());
//        }
//        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//        dataOutputStream.writeInt(3);
//        dataOutputStream.writeUTF("sina");
//        ArrayList<Post> posts = new ArrayList<>();
//        String jsonString;
//        while (true)
//        {
//            jsonString = dataInputStream.readUTF();
//            if (!jsonString.equals("EXIT"))
//            {
//                JSONObject jsonObject = new JSONObject(jsonString);
//                File file = new File("D:/" + jsonObject.getString("postId") +
//                        jsonObject.getString("photo").substring(jsonObject.getString("photo").indexOf(".")));
//                Post post = new Post(jsonObject.getString("title"), jsonObject.getString("postId"),
//                        jsonObject.getString("category"), jsonObject.getString("description"),
//                        Double.parseDouble(jsonObject.getString("price")), jsonObject.getString("sold"),
//                        jsonObject.getString("owner"), file, jsonObject.getString("phoneNumber"),
//                        jsonObject.getString("location"));
//                posts.add(post);
//                receiveProfilePhoto(file.getAbsolutePath(), dataInputStream);
//            }
//            else
//            {
//                break;
//            }
//        }
//
//        System.out.println(posts.get(0).getPrice());
//        dataOutputStream.writeInt(0);


    }

    private static void sendProfile (String path, DataOutputStream dataOutputStream) {
        try
        {
            int bytes = 0;
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            dataOutputStream.writeUTF(file.getName().substring(file.getName().indexOf(".")));
            dataOutputStream.writeLong(file.length());
            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1)
            {
                dataOutputStream.write(buffer, 0, bytes);
                dataOutputStream.flush();
            }
            fileInputStream.close();
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public static void receiveProfilePhoto(String path, DataInputStream dataInputStream)
    {
        int bytes = 0;
        File file = new File(path);
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            long size = dataInputStream.readLong();
            byte[] buffer = new byte[4 * 1024];
            while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1)
            {
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;
            }
            fileOutputStream.close();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
