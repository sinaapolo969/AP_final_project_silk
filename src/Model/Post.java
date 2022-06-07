package Model;

import javafx.geometry.Pos;

public class Post
{
    private String title;
    private String category;
    private String description;
    private double price;
    private boolean saleStatus;
    private String owner;
    private String photo;

//    public Post(String title, String category, String description, double price, boolean saleStatus, String owner, String photo)
//    {
//        this.title = title;
//        this.category = category;
//        this.description = description;
//        this.price = price;
//        this.saleStatus = saleStatus;
//        this.owner = owner;
//        this.photo = photo;
//    }




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

    public boolean isSaleStatus()
    {
        return saleStatus;
    }

    public String getOwner()
    {
        return owner;
    }

    public String getPhoto()
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

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }
}
