package Model.Person.User;


//import Model.Person.Admin.Admin;
import Model.Person.EmailValidationException;
import Model.Person.PhoneNumberValidationException;
import Model.Post.Post;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;


public class Request
{
    private static Socket socket;
    private static DataOutputStream dataOutputStream;
    private static DataInputStream dataInputStream;


    public Request(Socket socket)
    {

        try
        {
            Request.socket = socket;
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //the emails method should uncomment
    public static void signUp(User receivedUser)
    {
//        if(!checkingEmailValidation(receivedUser.getEmail()))
//        {
//            try
//            {
//                throw new EmailValidationException("Wrong Email");
//            }
//            catch (EmailValidationException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        else if(!checkingPhoneNumber(receivedUser.getPhoneNumber()))
//        {
//            try
//            {
//                throw new PhoneNumberValidationException("Wrong Phone-number");
//            }
//            catch (PhoneNumberValidationException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//            try
//            {
//                dataOutputStream.writeInt(1);
//                dataOutputStream.flush();
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//            sendingUserDataToServer(receivedUser);
//            Admin.sendEmail(receivedUser.getEmail(), "WellCome", "welcome\n to silk road app we are so glad to have you here");
//        }
        try
        {
            dataOutputStream.writeInt(1);
            dataOutputStream.flush();
            sendingUserDataToServer(receivedUser);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static User login(String JsonStringUser)
    {
        File receivedImage = receiveProfilePhoto("D:/new.png");

        JSONObject receivedUserJason = new JSONObject(JsonStringUser);

        User user = new User(receivedUserJason.getString("userName"), receivedUserJason.getString("password"),
                receivedUserJason.getString("firstName"), receivedUserJason.getString("lastName"),
                receivedUserJason.getString("phoneNumber"), receivedUserJason.getString("emailAddress"),
                receivedUserJason.getString("location"), receivedImage);

        return user;
    }


    public synchronized void sendingEmailForResettingPassword(User user)// ariana should call this when user push reset password button
    {
        int code = 1000 + (int) (Math.random() * 10000);
        //Admin.sendEmail(user.getEmail(), "Resetting Email", "here's your code for resetting your password" + code);
        // I will send this code and user for ariana
    }

    //this method doesnt synced with database and fx
    public synchronized void gettingCode(int inputCode, int originalCode, User user) // ariana will send the input code for me and als resend the original code and the user
    {
        if (inputCode == originalCode)
        {
            //calling the page that get new pass then there we call editInfo(user)
        }
        else {
            //out.println("wrong code");
            //if (they push new code button)
            //{
            //  sout("enter the new code)
            //  sendingEmailForResettingPassword(user);
            //
            //}
            //elseif(they push exit)
            //{
            //      go back
            //}
        }
    }


    public static void editInfo(User receivedUser) {
        if (!checkingEmailValidation(receivedUser.getEmail())) {
            try {
                throw new EmailValidationException("Wrong Email");
            } catch (EmailValidationException e) {
                e.printStackTrace();
            }
        } else if (!checkingPhoneNumber(receivedUser.getPhoneNumber())) {
            try {
                throw new PhoneNumberValidationException("Wrong Phone-number");
            } catch (PhoneNumberValidationException e) {
                e.printStackTrace();
            }
        } else {
            try
            {
                dataOutputStream.writeInt(8);
                dataOutputStream.flush();
                sendingUserDataToServer(receivedUser);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String existenceOfUser(String inputUserName, String inputPassWord)
    {
        try {
            dataOutputStream.writeInt(2);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(inputUserName);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(inputPassWord);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String receivedUser = null;
        try
        {
            receivedUser = dataInputStream.readUTF();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if(receivedUser == null)
        {
            return null;
        }

        return receivedUser;
    }

    private static void sendingUserDataToServer(User receivedUser)
    {
        String jsonString = "{\n \"firstName\": " + "\"" + receivedUser.getFirstName() + "\"" + ",\n" + "\"lastName\": " +
                "\"" + receivedUser.getLastName() + "\"" + ",\n" + "\"userName\": " + "\"" + receivedUser.getUserName() + "\"" + ",\n" +
                "\"password\": " + "\"" + receivedUser.getPassword() + "\"" + ",\n" + "\"phoneNumber\": " +
                "\"" + receivedUser.getPhoneNumber() + "\"" + ",\n" + "\"emailAddress\" : " + "\"" + receivedUser.getEmail() + "\"" +
                ",\n" + "\"location\": " + "\"" + receivedUser.getLocation() + "\"" + "\n}";

        try
        {
            dataOutputStream.writeUTF(jsonString);
            dataOutputStream.flush();
            sendProfilePhoto(receivedUser.getProfile());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void sendProfilePhoto(File file)
    {
        try
        {
            int bytes = 0;
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

    private static File receiveProfilePhoto(String path)
    {
        int bytes = 0;
        File file = new File(path);
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            long size = dataInputStream.readLong();
            byte[] buffer = new byte[4 * 1024];
            while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1)
            {
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;
            }
            fileOutputStream.close();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

        return file;
    }



    private static boolean checkingEmailValidation(String email)
    {
        String regex = "[0-9a-zA-Z_.]*@[0-9a-zA-Z]*\\.[a-zA-Z]{3}";
        return email.matches(regex);
    }


    private static boolean checkingPhoneNumber(String phoneNumber)
    {
        String regex = "09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}";
        return phoneNumber.matches(regex);
    }

    //this method doesnt work
    private boolean existenceOfEmail(String email) {
//        try {
//            dataOutputStream.writeUTF(email);
//            dataOutputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            return dataInputStream.readBoolean();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    public static void bookmarking (String postID, String username)
    {
        try
        {
            dataOutputStream.writeInt(15);
            dataOutputStream.writeUTF(postID);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(username);
            dataOutputStream.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void unbookmarking (String postID, String username)
    {
        try
        {
            //dataOutputStream.writeInt(); ask for the code
            dataOutputStream.writeUTF(username);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(postID);
            dataOutputStream.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Post> gettingBookmarks(String username)
    {
        ArrayList<Post> bookMarkedPosts = new ArrayList<>();
        try
        {
            dataOutputStream.writeInt(7);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(username);
            dataOutputStream.flush();
            bookMarkedPosts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



        return bookMarkedPosts;
    }

    public static void makingPost(Post post)
    {
        String jsonFormOfThePost = creatingJsonString(post);
        try
        {
            dataOutputStream.writeInt(6);
            dataOutputStream.flush();
            sendingPostDataToServer(jsonFormOfThePost, post.getPhoto());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void editPost(Post editedPost)
    {
        try
        {
            dataOutputStream.writeInt(10);
            dataOutputStream.flush();
            sendingPostDataToServer(creatingJsonString(editedPost), editedPost.getPhoto());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void sendingPostDataToServer(String jsonFormOfThePost, File photo)
    {
        try
        {
            dataOutputStream.writeUTF(jsonFormOfThePost);
            dataOutputStream.flush();
            sendPostPhoto(photo.getAbsolutePath());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void deletePost(String postID)//if they push delete post button
    {
        sendingPostForDeleting(postID);
    }

    private static String creatingJsonString(Post post)
    {
        String jsonString = "{\n \"title\": " + "\"" + post.getTitle() + "\"" + ",\n" + "\"postId\": " +
                "\"" + post.getPostId() + "\"" + ",\n" + "\"category\": " + "\"" + post.getCategory() + "\"" + ",\n" +
                "\"description\": " + "\"" + post.getDescription() + "\"" + ",\n" + "\"price\": " +
                "\"" + post.getPrice() + "\"" + ",\n" + "\"sold\" : " + "\"" + post.isSaleStatus() + "\"" +
                ",\n" + "\"owner\": " + "\"" + post.getOwner() + "\"" + ",\n" + "\"phoneNumber\" : " + "\"" + post.getPhoneNumber() +
                "\"" + ",\n" + "\"location\": " + "\"" + post.getLocation() + "\""
                +  ",\n" + "\"date\" : " + "\"" + post.getDate() + "\"" + "\n}";

        return jsonString;
    }

    public static ArrayList<Post> getPostByOwner(String userName)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try
        {
            dataOutputStream.writeInt(3);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(userName);
            dataOutputStream.flush();
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    public static ArrayList<Post> gettingPostsFromDataBase() throws IOException
    {
        ArrayList<Post> posts = new ArrayList<>();
        String jsonString;
        int arraySize = dataInputStream.readInt();
        for (int i = 0; i < arraySize; i++)
        {
            jsonString = dataInputStream.readUTF();
            JSONObject jsonObject = new JSONObject(jsonString);
            File file = new File("D:/" + jsonObject.getString("postId") +
                    jsonObject.getString("photo").substring(jsonObject.getString("photo").indexOf(".")));
            Post post = new Post(jsonObject.getString("title"), jsonObject.getString("postId"),
                    jsonObject.getString("category"), jsonObject.getString("description"),
                    Double.parseDouble(jsonObject.getString("price")), jsonObject.getString("sold"),
                    jsonObject.getString("owner"), file, jsonObject.getString("phoneNumber"),
                    jsonObject.getString("location"), convertingStringToDate(jsonObject.getString("date")));
            posts.add(post);
            receiveProfilePhoto(file);
        }

        return posts;
    }

    public static ArrayList<Post> getPostByCategory(String category)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            dataOutputStream.writeInt(4);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(category);
            dataOutputStream.flush();
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    public static ArrayList<Post> getPostByLocation(String location)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            dataOutputStream.writeInt(5);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(location);
            dataOutputStream.flush();
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    public static ArrayList<Post> getFilteredPricedPosts(int minPrice, int maxPrice)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try
        {
            dataOutputStream.writeInt(11);
            dataOutputStream.writeInt(minPrice);
            dataOutputStream.flush();
            dataOutputStream.writeInt(maxPrice);
            dataOutputStream.flush();
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    public static ArrayList<Post> getFilteredPricedAndLocationPosts(int minPrice, int maxPrice, String location)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try
        {
            dataOutputStream.writeInt(12);
            dataOutputStream.writeUTF(location);
            dataOutputStream.writeInt(minPrice);
            dataOutputStream.flush();
            dataOutputStream.writeInt(maxPrice);
            dataOutputStream.flush();
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    public static ArrayList<Post> getPostsByLocationAndCategory(String category, String location)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try
        {
            dataOutputStream.writeInt(9);
            dataOutputStream.writeUTF(category);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(location);
            dataOutputStream.flush();
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    public static ArrayList<Post> getPostsByLocationAndCategoryAndLimitedPrice(int minPrice,int maxPrice,
                                                                               String category, String location)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try
        {
            dataOutputStream.writeInt(14);
            dataOutputStream.writeUTF(category);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(location);
            dataOutputStream.flush();
            dataOutputStream.writeInt(minPrice);
            dataOutputStream.flush();
            dataOutputStream.writeInt(maxPrice);
            dataOutputStream.flush();
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;
    }

    public static ArrayList<Post> getPostByCategoryAndPrice(String category, int minPrice, int maxPrice)
    {
        ArrayList<Post> posts = new ArrayList<>();
        try
        {
            dataOutputStream.writeInt(13);
            dataOutputStream.writeUTF(category);
            dataOutputStream.flush();
            dataOutputStream.writeInt(minPrice);
            dataOutputStream.flush();
            dataOutputStream.writeInt(maxPrice);
            dataOutputStream.flush();
            posts = gettingPostsFromDataBase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return posts;

    }

    private static boolean expiration(Post post)
    {
        if(post.getEXP().compareTo(LocalDate.now()) >= 0)
        {
            sendingPostForDeleting(post.getPostId());
            return true;
        }
        return false;
    }

    private static void sendingPostForDeleting(String postID)
    {
        try {
            dataOutputStream.writeInt(16);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(postID);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void receiveProfilePhoto(File file)
    {
        int bytes = 0;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            long size = dataInputStream.readLong();
            byte[] buffer = new byte[4 * 1024];
            while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1)
            {
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;
            }
            fileOutputStream.close();
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public static LocalDate convertingStringToDate(String dateString)
    {
        String[] splitedDate = dateString.split("-");
        try
        {
            return LocalDate.of(Integer.parseInt(splitedDate[0]), Integer.parseInt(splitedDate[1]), Integer.parseInt(splitedDate[2]));
        }
        catch (DateTimeException | NumberFormatException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    private static void sendPostPhoto(String path)
    {
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

    public static boolean checkingStrengthOfPass(String password)
    {
        String strongPassRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        return password.matches(strongPassRegex);
    }

    public static String duration(LocalDate postTime)
    {
        int duration = postTime.compareTo(LocalDate.now());
        if (duration < 0 && postTime.getDayOfMonth() != LocalDate.now().getDayOfMonth())
        {
            return (Math.abs(duration) + "days ago");
        }
        else if(duration <= 0)
        {
            return "just now";
        }
        return null;
    }

    public static void logOut()
    {
        try
        {
            socket.close();
            dataOutputStream.close();
            dataInputStream.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
