package Model.Post;
import Model.Chat.MessageStruct;

import java.io.File;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;



public class Post
{
    private String title;
    private String postId;
    private String category;
    private String description;
    private double price;
    private String saleStatus;
    private String owner;
    private File photo;
    private String phoneNumber;
    private String location;
    private final LocalDate date;
    private final LocalDate EXP;
    private ArrayList<MessageStruct> messages= new ArrayList<>();

    public Post(String title, String category, String description, double price, String saleStatus, String owner, File photo,
                String phoneNumber, String location) // this constructor is for making new post for the first time
    {
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.saleStatus = saleStatus;
        this.owner = owner;
        this.photo = photo;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.date = LocalDate.now();
        this.EXP = LocalDate.now().plusMonths(1);
    }

    public Post(String title, String postId, String category, String description,
                double price, String saleStatus, String owner, File photo, String phoneNumber,
                String location, LocalDate date)// this conjecture is for making obj but not for the fist time
    {
        this.title = title;
        this.postId = postId;
        this.category = category;
        this.description = description;
        this.price = price;
        this.saleStatus = saleStatus;
        this.owner = owner;
        this.photo = photo;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.date = date;
        this.EXP = date.plusMonths(1);
    }

    public String getTitle()
    {
        return title;
    }
    public String getCategory()
    {
        return category;
    }
    public String getDescription()
    {
        return description;
    }
    public String isSaleStatus() {
        return saleStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getEXP() {
        return EXP;
    }

    public Double getPrice()
    {
        return price;
    }

    //    //public boolean saleStatus()
//    {
//        return saleStatus;
//    }

    public String getOwner()
    {
        return owner;
    }

    public File getPhoto()
    {
        return photo;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setSaleStatus(String saleStatus)
    {
        this.saleStatus = saleStatus;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public void setPhoto(File photo)
    {
        this.photo = photo;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getPostId()
    {
        return postId;
    }

    public void setPostId(String postId)
    {
        this.postId = postId;
    }

    public ArrayList<MessageStruct> getMessages()
    {
        return messages;
    }

    public void setMessages(ArrayList<MessageStruct> messages)
    {
        this.messages = messages;
    }
}