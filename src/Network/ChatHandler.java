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
    private boolean isLoggedIn;

    public ChatHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream)
    {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream =dataOutputStream;
        this.isLoggedIn = true;
    }

    @Override
    public void run()
    {
        String received;
        while (true)
        {
            try
            {
                received = dataInputStream.readUTF();
                JSONObject jsonObject = new JSONObject(received);

                if (received.equals("endChat"))
                {
                    isLoggedIn = false;
                    this.socket.close();
                }

                if (ChatServer.onlineUsers.get(jsonObject.getString("receiver")).isLoggedIn)
                {
                    ChatServer.onlineUsers.get(jsonObject.getString("receiver")).dataOutputStream.writeUTF(received);
                    dataOutputStream.flush();
                    saveMessage(received);
                }
                else
                {
                    saveMessage(received);
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void saveMessage(String message)
    {
        try {
            new PostTable().saveMessage(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
