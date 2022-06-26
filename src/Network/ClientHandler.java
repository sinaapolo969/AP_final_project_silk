package Network;

import DataBase.PostTable;
import DataBase.UserTable;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientHandler extends Thread
{
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public ClientHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream)
    {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream =dataOutputStream;
    }


    @Override
    public void run()
    {
        int command;
        while (true)
        {
            try
            {
                command = dataInputStream.readInt();
                switch (command)
                {
                    //signUp
                    case 1:
                        String userInfo = dataInputStream.readUTF();
                        JSONObject jsonObject = new JSONObject(userInfo);
                        String fileType = dataInputStream.readUTF();
                        String path = "D:/final project/userProfiles/" + jsonObject.getString("userName") + fileType;
                        makeNewAccount(userInfo, path);
                        break;
                    //log in
                    case 2:
                        String userName = dataInputStream.readUTF();
                        String password = dataInputStream.readUTF();
                        try
                        {
                            if (password.equals(checkPassword(userName)))
                            {
                                dataOutputStream.writeUTF(getUserAccount(userName));
                                sendProfilePhoto(getUserProfilePhoto(userName));
                            }
                        }
                        catch (NullPointerException e)
                        {
                            System.out.println("userName isnt exists");
                        }
                        break;
                    //get post by owner
                    case 3:
                        userName = dataInputStream.readUTF();
                        getPostByOwner(userName);
                        break;
                    //get post by category
                    case 4:
                        //String category = dataInputStream.readUTF();
                        //objectOutputStream.writeObject(getPostByCategory(category));
                        break;
                    //get post by location
                    case 5:
                        String location = dataInputStream.readUTF();
                        getPostByLocation(location);
                        break;
                    case 6:
                        String postData = dataInputStream.readUTF();
                        jsonObject = new JSONObject(postData);
                        fileType = dataInputStream.readUTF();
                        path = "D:/final project/postPhotos/" + jsonObject.getString("postId") + fileType;
                        makeNewPost(postData, path);
                    case 0:
                        return;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    //-----------------user part

    //for make a new row in database table for user
    private void makeNewAccount(String jsonString, String path)
    {
        UserTable userTable = new UserTable();
        try
        {
            receiveProfilePhoto(path);
            userTable.insertUserData(jsonString, path);
            userTable.close();
        }
        catch (SQLException | ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }
    }

    private void sendProfilePhoto(File file) {
        try
        {
            int bytes = 0;
            FileInputStream fileInputStream = new FileInputStream(file);
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

    private synchronized void receiveProfilePhoto(String path)
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

    //this method is for saving profiles to server side
    private String savePhotoToDirectory(File file)
    {
        String path = null;
        try
        {
            BufferedImage bufferedImage = ImageIO.read(file);
            path = "D://final project/userProfiles/" + file.getName();
            ImageIO.write(bufferedImage, "jpg", new File(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return path;
    }

    //for get the user data when it logged in
    //this method should return an object of User class that has not made yet!
    private String getUserAccount(String userName)
    {
        UserTable userTable = new UserTable();
        String userInfo = null;
        try
        {
            userInfo = userTable.getUserData(userName);
            userTable.close();
        }
        catch (SQLException | ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }

        return userInfo;
    }

    private String checkPassword(String userName)
    {
        UserTable userTable = new UserTable();
        try
        {
            String password = userTable.checkPassword(userName);
            userTable.close();
            return password;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private File getUserProfilePhoto(String userName)
    {
        UserTable userTable = new UserTable();
        try
        {
            File file = userTable.getUserProfilePhoto(userName);
            userTable.close();

            return file;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private void editUserInfo(String jsonString, File file)
    {
        UserTable userTable = new UserTable();
        try {
            userTable.updateUserData(jsonString, file.getAbsolutePath());
            userTable.close();
        }
        catch (SQLException | IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private void deleteUserAccount(String userName)
    {
        UserTable userTable = new UserTable();
        try
        {
            userTable.deleteUserData(userName);
            userTable.close();
        }
        catch (SQLException | IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private void makeNewPost(String jsonString, String path)
    {
        PostTable postTable = new PostTable();
        try
        {
            receiveProfilePhoto(path);
            postTable.insertPostData(jsonString, path);
            postTable.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private ArrayList<String> getPostByCategory(String category)
    {
        PostTable postTable = new PostTable();
        ArrayList<String> posts = null;
        try
        {
            posts = postTable.getPostByCategory(category);
            postTable.close();
            sendMultiPosts(posts);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    private void getPostByOwner(String userName)
    {
        PostTable postTable = new PostTable();
        ArrayList<String> posts = null;
        try
        {
            posts = postTable.getPostDataByOwner(userName);
            postTable.close();
            sendMultiPosts(posts);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    private void getPostByLocation(String location)
    {
        PostTable postTable = new PostTable();
        ArrayList<String> posts = null;
        try
        {
            posts = postTable.getPostByLocation(location);
            postTable.close();
            sendMultiPosts(posts);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private ArrayList<String> getUserBookMarks(String userName)
    {
        PostTable postTable = new PostTable();
        ArrayList<String> posts = null;
        try
        {
            posts = postTable.getBookMarks(userName);
            postTable.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    private void sendMultiPosts(ArrayList<String> posts)
    {
        try
        {
            for (String post : posts)
            {
                JSONObject jsonObject = new JSONObject(post);
                dataOutputStream.writeUTF(post);
                sendProfilePhoto(new File(jsonObject.getString("photo")));
            }
            //this is temprary
            dataOutputStream.writeUTF("EXIT");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    //for terminate the thread and logout
    public void logOut()
    {
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    //----------------end of user part
}
