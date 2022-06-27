package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private ServerSocket serverSocket;

    public void setUp()
    {
        try
        {
            serverSocket = new ServerSocket(5056);
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
                new ChatHandler(socket, dataInputStream, dataOutputStream).start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}
