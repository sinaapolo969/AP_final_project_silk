package Network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private ServerSocket serverSocket;
    private Socket socket;

    public void setUp()
    {
        while (true)
        {
            try
            {
                serverSocket = new ServerSocket(6666);
                socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
