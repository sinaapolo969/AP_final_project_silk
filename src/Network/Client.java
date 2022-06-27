package Network;

import java.io.*;
import java.net.Socket;

public class Client
{
    public static Socket socket;
    public static Socket chatSocket;
    public static Socket setUp()
    {
        try
        {
            socket = new Socket("localhost", 6666);
            //we should call the launch method here and send the requests for server
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return socket;
    }

    public static Socket chatSetUp()
    {
        try {
            chatSocket = new Socket("localhost", 5056);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chatSocket;
    }

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
}
