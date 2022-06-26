package Model.Post;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PostRequests
{
    private final Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public PostRequests(Socket socket)
    {
        this.socket = socket;
        try
        {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}

//import static DataBase.test.receiveProfilePhoto;
