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
                System.out.println("client accepted");
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                new ClientHandler(socket, dataInputStream, dataOutputStream, objectInputStream, objectOutputStream).start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}
