package Network;

import DataBase.PostTable;
import DataBase.UserTable;

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
    private final ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ClientHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream,
                         ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream)
    {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
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
                        File file = (File) objectInputStream.readObject();
                        makeNewAccount(userInfo, file);
                        break;
                    //log in
                    case 2:
                        String userName = dataInputStream.readUTF();
                        String data = getUserAccount(userName);
                        dataOutputStream.writeUTF(data);
                        objectOutputStream.writeObject(getUserProfilePhoto(userName));
                        break;
                    //get post by owner
                    case 3:
                        userName = dataInputStream.readUTF();
                        //objectOutputStream.writeObject(getPostByOwner(userName));
                        break;
                    //get post by category
                    case 4:
                        String category = dataInputStream.readUTF();
                        //objectOutputStream.writeObject(getPostByCategory(category));
                        break;
                    //get user bookmarks
                    case 5:
                        userName = dataInputStream.readUTF();
                        //objectOutputStream.writeObject(getUserBookMarks(userName));
                        break;
                    case 0:
                        return;
                }
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }

    }

    //-----------------user part

    //for make a new row in database table for user
    private void makeNewAccount(String jsonString, File file)
    {
        UserTable userTable = new UserTable();
        try
        {
            userTable.insertUserData(jsonString, file.getAbsolutePath());
            userTable.close();
        }
        catch (SQLException | ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }
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

    private File getUserProfilePhoto(String userName)
    {
        UserTable userTable = new UserTable();
        try
        {
            return userTable.getUserProfilePhoto(userName);
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

    private void makeNewPost(String jsonString)
    {
        PostTable postTable = new PostTable();
        try
        {
            postTable.insertPostData(jsonString);
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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    private ArrayList<String> getPostByOwner(String userName)
    {
        PostTable postTable = new PostTable();
        ArrayList<String> posts = null;
        try
        {
            posts = postTable.getPostDataByOwner(userName);
            postTable.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return posts;
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

    //this method is for saving profiles to server side
    private void savePhotoToDirectory(File file)
    {
        try
        {
            BufferedImage bufferedImage = ImageIO.read(file);
            ImageIO.write(bufferedImage, "jpg", new File("D://final project/userProfiles" + file.getName()));
        }
        catch (IOException e)
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
