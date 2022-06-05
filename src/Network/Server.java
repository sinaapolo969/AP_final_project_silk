package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private ServerSocket serverSocket;
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public void setUp()
    {
        while (true)
        {
            try
            {
                serverSocket = new ServerSocket(6666);
                socket = serverSocket.accept();
                dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                ClientHandler clientHandler = new ClientHandler(socket, dataInputStream, dataOutputStream);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
