package Network;

import java.io.*;
import java.net.Socket;

public class Client
{
    private Socket socket;

    public Socket setUp()
    {
        try
        {
            socket = new Socket("127.0.0.1", 6666);
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
