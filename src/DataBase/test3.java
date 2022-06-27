package DataBase;

import Network.Client;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class test3
{
    static String json = "{\n" +
            "  \"firstName\": \"ariana\",\n" +
            "  \"lastName\": \"mehhh\",\n" +
            "  \"userName\": \"meow\",\n" +
            "  \"password\": \"sina1381\",\n" +
            "  \"phoneNumber\": \"11111\",\n" +
            "  \"emailAddress\": \"sdadfdfsd\",\n" +
            "  \"location\": \"abodan\"\n" +
            "}";
    static String json2 = "{\n" +
            "  \"firstName\": \"mmadreza\",\n" +
            "  \"lastName\": \"tayebi\",\n" +
            "  \"phoneNumber\": \"11111\",\n" +
            "  \"emailAddress\": \"sdadfdfsd\",\n" +
            "  \"location\": \"abodan\"\n" +
            "}";
    static String json3 = "{\n" +
            "  \"title\": \"bib\",\n" +
            "  \"postId\": \"2\", \n " +
            "  \"category\": \"home\",\n" +
            "  \"description\": \"something\",\n" +
            "  \"price\": \"10000\",\n" +
            "  \"owner\": \"sina\",\n" +
            "  \"location\": \"ahvaz\", \n" +
            "  \"sold\": \"0\",\n" +
            "  \"photo\": \"something\"\n" +
            "}";
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException
    {
        Scanner in = new Scanner(System.in);
        Socket socket = Client.chatSetUp();
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("mahdiA");
        dataOutputStream.flush();
        Thread sendMessage = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String message = in.next();
                while (true)
                {
                    try {
                        dataOutputStream.writeUTF(message);
                        dataOutputStream.flush();
                        Thread.currentThread().join();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread receiveMessage = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String received;
                while (true)
                {
                    try {
                        received = dataInputStream.readUTF();
                        System.out.println(received);
                        Thread.currentThread().join();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        sendMessage.start();
        receiveMessage.start();
    }

    private static void sendProfile (String path, DataOutputStream dataOutputStream) {
        try
        {
            int bytes = 0;
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            dataOutputStream.writeUTF(file.getName().substring(file.getName().indexOf(".")));
            dataOutputStream.writeLong(file.length());
            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1)
            {
                dataOutputStream.write(buffer, 0, bytes);
                dataOutputStream.flush();
            }
            fileInputStream.close();
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
