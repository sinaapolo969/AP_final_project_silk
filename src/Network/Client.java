package Network;

import java.io.*;
import java.net.Socket;

public class Client
{
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    public void setUp()
    {
        try
        {
            socket = new Socket("127.0.0.1", 6666);
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            //we should call the launch method here and send the requests for server
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
