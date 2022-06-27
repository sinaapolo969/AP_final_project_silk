package Network;

import DataBase.PostTable;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ChatHandler extends Thread
{
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public ChatHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream)
    {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream =dataOutputStream;
    }

    @Override
    public void run()
    {
        int command;
        while (true)
        {
            try
            {
                command = dataInputStream.readInt();
                switch (command)
                {
                    //send and save message
                    case 1:
                        String message = dataInputStream.readUTF();
                        sendMessage(message);
                        break;
                    //receive message
                    case 2:
                        break;
                    case 0:
                        Thread.currentThread().join();
                        break;
                }
            }
            catch (IOException | InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(String message) throws IOException
    {
        JSONObject jsonObject = new JSONObject();
        Socket socket = ChatServer.onlineUsers.get(jsonObject.getString("receiver"));
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
        try {
            new PostTable().saveMessage(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
