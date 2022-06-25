package Model;


import javafx.scene.image.Image;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Advertise
{
    private Image image;
    private String title;
    private String owner;
    private String price;
    private final LocalDate date;


    public Advertise(Image image, String title, String owner, String price)
    {
        this.image = image;
        this.title = title;
        this.owner = owner;
        this.price = price;
        date = LocalDate.now();
    }


    public Image getImage()
    {
        return image;
    }


    public String getTitle()
    {
        return title;
    }


    public String getOwner()
    {
        return owner;
    }


    public String getPrice()
    {
        return price;
    }


    public LocalDate getDate() {
        return date;
    }
}
