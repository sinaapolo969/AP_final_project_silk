package Model;

import javafx.geometry.Pos;

import java.awt.*;

public class Post
{
    private String title;
    private String postId;
    private String category;
    private String description;
    private double price;
    private boolean saleStatus;
    private String owner;
    private Image photo;
    private String phoneNumber;
    private String location;


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

    public Double getPrice()
    {
        return price;
    }

    public boolean saleStatus()
    {
        return saleStatus;
    }

    public String getOwner()
    {
        return owner;
    }

    public Image getPhoto()
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

    public void setSaleStatus(boolean saleStatus)
    {
        this.saleStatus = saleStatus;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public void setPhoto(Image photo)
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
}
