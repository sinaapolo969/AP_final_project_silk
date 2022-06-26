package Model.Post;

import org.json.JSONObject;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

//import static DataBase.test.receiveProfilePhoto;

public class PostRequests
{
    private final Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public PostRequests(Socket socket)
    {
        this.socket = socket;
        try
        {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void bookmarking (String postID, String username)
    {
        //dataOutputStream.writeInt(); ask for the code
        try
        {
            dataOutputStream.writeUTF(username);
            dataOutputStream.writeUTF(postID);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Post> gettingBookmarks (String username)
    {
        ArrayList<Post> bookMarkedPosts = new ArrayList<>();
        try
        {
            dataOutputStream.writeInt(7);
            dataOutputStream.writeUTF(username);
            bookMarkedPosts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return bookMarkedPosts;
    }

    public void makingPost(Post post)
    {
//        String jsonFormOfThePost = creatingJsonString(post);
//        sendingPostDataToServer(jsonFormOfThePost, post.getPhoto());
    }

    private void sendingPostDataToServer(String jsonFormOfThePost, Image photo)
    {
        try
        {
            dataOutputStream.writeInt(6);
            dataOutputStream.writeUTF(jsonFormOfThePost);
            //dataOutputStream.writeInt(0);
            //objectOutputStream.writeObject(photo);send photo here
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void editPost(Post editedPost)
    {
        //sendingPostDataToServer(creatingJsonString(editedPost), editedPost.getPhoto());
    }

    public void deletePost(String postID)//if they push delete post button
    {
        sendingPostForDeleting(postID);
    }

    private String creatingJsonString(Post post)
    {
        String jsonString = "{\n \"title\": " + post.getTitle() + ",\n" + "\"postID\": " +
                post.getPostId() + ",\n" + "\"category\": " + post.getCategory() + ",\n" +
                "\"description\": " + post.getDescription() + ",\n" + "\"price\": " +
                post.getPrice() + ",\n" + "\"saleStatus\" : " + post.isSaleStatus() +
                ",\n" + "\"owner\": " + post.getOwner() + "\"phoneNumber\" : " + post.getPhoneNumber() +
                ",\n" + "\"location\": " + post.getLocation() + "\"Date\" : " + post.getDate() +
                ",\n" + "\"EXP\": " + post.getEXP() + "\n}";
        return jsonString;
    }

    public ArrayList<Post> getPostByOwner(String userName)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            dataOutputStream.write(3);
            dataOutputStream.writeUTF(userName);
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    public ArrayList<Post> gettingPostsFromDataBase()
    {
        ArrayList<Post> posts = new ArrayList<>();
        String jsonString = null;
        while (true)
        {
            try {
                jsonString = dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!jsonString.equals("EXIT"))
            {
                JSONObject jsonObject = new JSONObject(jsonString);
                File file = new File("D:/" + jsonObject.getString("postId") +
                        jsonObject.getString("photo").substring(jsonObject.getString("photo").indexOf(".")));
                receiveProfilePhoto(file.getAbsolutePath(), dataInputStream);
                Post post = new Post(jsonObject.getString("title"), jsonObject.getString("postId"),
                        jsonObject.getString("category"), jsonObject.getString("description"),
                        Double.parseDouble(jsonObject.getString("price")), jsonObject.getString("sold"),
                        jsonObject.getString("owner"), file, jsonObject.getString("phoneNumber"),
                        jsonObject.getString("location"));
                posts.add(post);
            }
            else
            {
                try {
                    dataOutputStream.writeInt(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return posts;
    }

    public ArrayList<Post> getPostByCategory(String category)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            dataOutputStream.write(4);
            dataOutputStream.writeUTF(category);
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    public ArrayList<Post> getPostByLocation (String location)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            dataOutputStream.write(5);
            dataOutputStream.writeUTF(location);
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    private void expiration(Post post)
    {
        if(post.getEXP().compareTo(LocalDate.now()) >= 0)
        {
            sendingPostForDeleting(post.getPostId());
        }
    }

    private void sendingPostForDeleting(String postID)
    {
        try {
            dataOutputStream.write(8);
            dataOutputStream.writeUTF(postID);
        } catch (IOException e) {
            e.printStackTrace();
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
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public LocalDate convertingStringToDate(String dateString)
    {
        String[] splitedDate = dateString.split("-");
        try
        {
            return LocalDate.of(Integer.parseInt(splitedDate[0]), Integer.parseInt(splitedDate[1]), Integer.parseInt(splitedDate[2]));
        }
        catch (DateTimeException | NumberFormatException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }
}
