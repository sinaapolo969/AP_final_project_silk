package Model.Chat;

import Model.Post.Post;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Chat
{
    private Socket socket;
    private static DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public Chat(Socket socket)
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

    public static String sendingMessageAndReceivingNotify(MessageStruct message, Post post) //put postID in constructor as chatId
    {
        try
        {
            //dataOutputStream.writeInt();ask for the code later
            dataOutputStream.writeUTF(post.getPostId());
            dataOutputStream.writeUTF(makingJsonString(message, post.getPostId()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return sendingNotification(message.getReceiverID(), post.getOwner() ,message.getText());
    }

    private static String sendingNotification(String receiverID, String Owner,String text)
    {
        String jsonString = "{\n \"text\": " + receiverID + "\"receiverId;\": " + text + "\n}";

        return jsonString;
    }

    private static String makingJsonString(MessageStruct message, String postID)
    {
        String jsonString = "{\n \"postId\": " + postID + ",\n" + "\"text\": " + message.getText() +
                ",\n" + "\"senderId\": " + message.getSenderID() + ",\n" +
                "\"receiverId;\": " + message.getReceiverID() +",\n" + "\"Date\" : " + message.getDate().toString() + "\n}";
        return jsonString;
    }

    public void getChat(Post post, String userId1, String userId2)
    {;
        try
        {
            //dataOutputStream.writeInt();ask about the code
            dataOutputStream.writeUTF(post.getPostId());
            dataOutputStream.writeUTF(userId1);
            dataOutputStream.writeUTF(userId2);
            post.setMessages(gettingChatFromDataBase());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private ArrayList<MessageStruct> gettingChatFromDataBase() throws IOException {
        ArrayList<MessageStruct> messages = new ArrayList<>();
        String jsonString;

        jsonString = dataInputStream.readUTF();
        if (!jsonString.equals("EXIT")) {
            JSONObject jsonObject = new JSONObject(jsonString);
            MessageStruct message = new MessageStruct(jsonObject.getString("text"),
                    jsonObject.getString("senderId"), jsonObject.getString("receiverId"),
                    convertStringToDate(jsonObject.getString("date")));
            messages.add(message);
        }
        else
        {
            //try {
            //dataOutputStream.writeInt(012010);//ask about te code
            //} catch (IOException e) {
            //e.printStackTrace();
            //}

        }
        return messages;
    }

    private LocalDateTime convertStringToDate(String date)
    {
        String [] stringDate = date.split("\\.|\\:|\\-|T");

        return LocalDateTime.of(Integer.parseInt(stringDate[0]), Integer.parseInt(stringDate[1]), Integer.parseInt(stringDate[2]),
                Integer.parseInt(stringDate[3]), Integer.parseInt(stringDate[4]), Integer.parseInt(stringDate[5]));
    }
}
