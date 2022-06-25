package Model.Post;

import Model.Person.EmailValidationException;
import Model.Person.PhoneNumberValidationException;
import Model.Person.User.User;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;

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


    public void makingPost(Post post)
    {
        String jsonFormOfThePost = creatingJsonString(post);
        sendingPostDataToServer(jsonFormOfThePost, post.getPhoto());
    }

    private void sendingPostDataToServer(String jsonFormOfThePost, Image photo)
    {
        try
        {
            dataOutputStream.writeInt(6);
            dataOutputStream.writeUTF(jsonFormOfThePost);
            //dataOutputStream.writeInt(0);
            //objectOutputStream.writeObject(photo);send photo here
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void editPost(Post editedPost)
    {
        sendingPostDataToServer(creatingJsonString(editedPost), editedPost.getPhoto());
    }

    public void deletePost(String postID)//if they push delete post button
    {
        sendingPostForDeleting(postID);
    }

    private String creatingJsonString(Post post)
    {
        String jsonString = "{\n \"title\": " + post.getTitle() + ",\n" + "\"postID\": " +
                post.getPostId() + ",\n" + "\"category\": " + post.getCategory() + ",\n" +
                "\"description\": " + post.getDescription() + ",\n" + "\"price\": " +
                post.getPrice() + ",\n" + "\"saleStatus\" : " + post.isSaleStatus() +
                ",\n" + "\"owner\": " + post.getOwner() + "\"phoneNumber\" : " + post.getPhoneNumber() +
                ",\n" + "\"location\": " + post.getLocation() + "\"Date\" : " + post.getDate() +
                ",\n" + "\"EXP\": " + post.getEXP() + "\n}";
        return jsonString;
    }

    public void getPostByOwner(String userName)// it is not completed yet
    {
        try {
            dataOutputStream.write(3);
            dataOutputStream.writeUTF(userName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void getPostByCategory(String category)// it is not completed yet
    {
        try {
            dataOutputStream.write(4);
            dataOutputStream.writeUTF(category);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void expiration(Post post)
    {
        if(post.getEXP().compareTo(LocalDate.now()) >= 0)
        {
            sendingPostForDeleting(post.getPostId());
        }
    }

    private void sendingPostForDeleting(String postID)
    {
        try {
            dataOutputStream.write(8);
            dataOutputStream.writeUTF(postID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
