package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ChatServer {
    private ServerSocket serverSocket;
    static HashMap<String, ChatHandler> onlineUsers = new HashMap<>();

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
                String userName = dataInputStream.readUTF();;
                ChatHandler chatHandler = new ChatHandler(socket, dataInputStream, dataOutputStream);
                onlineUsers.put(userName, chatHandler);
                new Thread(chatHandler).start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}
