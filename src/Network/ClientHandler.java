package Network;

import DataBase.UserTable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable
{
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public ClientHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream)
    {
        this.socket = socket;
        this.dataOutputStream = dataOutputStream;
        this.dataInputStream = dataInputStream;
        run();
    }


    @Override
    public void run()
    {
        //we should handle the requests form clients

    }

    //-----------------user part

    //for make a new row in database table for user
    public void makeNewAccount(String jsonString)
    {
        UserTable userTable = new UserTable();
        try
        {
            userTable.insertUserData(jsonString);
        }
        catch (SQLException | ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }
    }

    //for get the user data when it logged in
    //this method should return an object of User class that has not made yet!
    public void getUserAccount(String userName)
    {
        UserTable userTable = new UserTable();
        try
        {
            userTable.getUserData(userName);
        }
        catch (SQLException | ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }

        //return userAccount
    }

    public void editUserInfo(String jsonString)
    {
        UserTable userTable = new UserTable();
        try {
            userTable.updateUserData(jsonString);
        }
        catch (SQLException | IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteUserAccount(String userName)
    {
        UserTable userTable = new UserTable();
        try
        {
            userTable.deleteUserData(userName);
        }
        catch (SQLException | IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    //for terminate the thread and logout
    public void logOut()
    {
        try
        {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    //----------------end of user part
}
