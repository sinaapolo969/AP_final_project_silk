package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private ServerSocket serverSocket;

    public void setUp()
    {
        try
        {
            serverSocket = new ServerSocket(6666);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        while (true)
        {
            try
            {

                Socket socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                new ClientHandler(socket, dataInputStream, dataOutputStream).start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}
