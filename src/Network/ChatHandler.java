package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
                        break;
                    //receive message
                    case 2:
                        break;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
