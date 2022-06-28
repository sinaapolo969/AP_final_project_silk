package DataBase;

import java.io.*;
import java.sql.SQLException;

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
            "  \"location\": \"Chicago\", \n" +
            "  \"sold\": \"0\",\n" +
            "  \"photo\": \"something\",\n" +
            "  \"date\": 2022-04-8" +
            "}";

    static String message = "{\n " +
            "  \"chatId\": \"34\", \n " +
            "  \"sender\": \"sina\", \n" +
            "  \"receiver\": \"mahdiA\", \n" +
            "  \"text\": \"salam\", \n " +
            "  \"dateTime\": \"2022-06-28 12:54:51\" \n } ";
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException
    {

//        PostTable postTable = new PostTable();
//        ArrayList<String> posts = postTable.getPostDataByOwner("meow", 1);
//        for (String s: posts)
//        {
//            System.out.println(s);
//        }

//        Request request = new Request(new Client().setUp());
//        Request.editInfo(new User("sina", "1234", "sina", "tayebi",
//                "09167287588", "sinaapolo969@gmail.com", "Chicago", new File("D:/new.png")));
//        PostTable postTable = new PostTable();
//        ArrayList<String> message = postTable.getConversation("30", "sina", "mahdiA");
//        for (String sss : message) {
//            System.out.println(sss);
//        }
       // PostTable postTable = new PostTable();
//        for (int i = 0; i < 10; i++)
//        {
//            userTable.insertPostData(json3, "C:\\Users\\kara\\Downloads\\ish.jpg");
//        }


//
//        ArrayList<Post> posts = Request.getPostByOwner("meow", 1);
//        System.out.println(posts.get(0).getPrice());


//        ArrayList<String> posts = postTable.getPostByLocation("Chicago");
//        System.out.println(posts.get(0));

//        Client client = new Client();
//        Socket socket = client.setUp();
//        PostTable postTable = new PostTable();
//        ArrayList<String> posts = postTable.getPostDataByOwner("mahdi");
//        for (String post : posts) {
//            JSONObject jsonObject = new JSONObject(post);
//        Client client = new Client();
//        Socket socket = client.setUp();
//        PostRequests postRequests = new PostRequests(socket);
//        ArrayList<Post> posts = postRequests.getPostByOwner("ariana");
//        for (Post post : posts) {
//            System.out.println(post.getPrice());
//        }

            //Date date = new Date(jsonObject.getString("date"));
            //System.out.println(date);
            //System.out.println(post);
           // System.out.println(jsonObject.get("date"));


//        PostRequests postRequests = new PostRequests(socket);
//        ArrayList<Post> posts = postRequests.getPostByLocation("Chicago");
//        for (Post post : posts) {
//            System.out.println(post.getPrice());
//        }
//        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//        dataOutputStream.writeInt(5);
//        dataOutputStream.writeUTF("Chicago");
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

//        Socket socket = Client.chatSetUp();
//        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//        dataOutputStream.writeUTF("sina");
//        dataOutputStream.flush();
//        Thread sendMessage = new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                String message = test.message;
//                while (true)
//                {
//                    try {
//                        dataOutputStream.writeUTF(message);
//                        dataOutputStream.flush();
//                        Thread.currentThread().join();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        Thread receiveMessage = new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                String received;
//                while (true)
//                {
//                    try {
//                        received = dataInputStream.readUTF();
//                        System.out.println(received);
//                        Thread.currentThread().join();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        });
//        receiveMessage.start();
//        sendMessage.start();
//        PostTable postTable = new PostTable();
//        ArrayList<String> posts = postTable.getFilteredPricePost("1000", "100000");
//        for (String s : posts) {
//            System.out.println(s);
//        }


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
