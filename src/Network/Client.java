package Network;

import java.io.*;
import java.net.Socket;

public class Client
{
    public static Socket socket;

    public Socket setUp()
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
